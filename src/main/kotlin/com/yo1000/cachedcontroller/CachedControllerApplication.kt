package com.yo1000.cachedcontroller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.reflect.Method
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootApplication
@EnableCaching
class CachedControllerApplication

fun main(args: Array<String>) {
	runApplication<CachedControllerApplication>(*args)
}

@RestController
class CacheableRestController {
	@Cacheable("dateTime", keyGenerator = "timeBasedKeyGenerator")
	@GetMapping
	fun getTime(
		@RequestParam("prefix", required = false, defaultValue = "") prefix: String,
		@RequestParam("suffix", required = false, defaultValue = "") suffix: String
	): String {
		return DateTimeFormatter.ofPattern("${
			prefix.takeIf { it.isNotEmpty() }?.let { "'${it}'" } ?: ""
		}uuuu-MM-dd' 'HH:mm:ss.SSS${
			suffix.takeIf { it.isNotEmpty() }?.let { "'${it}'" } ?: ""
		}").format(LocalDateTime.now())
	}
}

@Component
class TimeBasedKeyGenerator : KeyGenerator {
	override fun generate(target: Any, method: Method, vararg params: Any?): Any {
		return "${target::class.qualifiedName}/${method.name}/${params.joinToString(",").takeIf { it.isNotEmpty() } ?: ""}".also {
			println("Key: $it")
		}
	}
}
