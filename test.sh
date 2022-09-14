#!/bin/bash

echo
echo '----- Make Cache (TTL: 10s <= docker-compose.yml) -----'
date +%Y-%m-%d' '%H:%M:%S
echo 'no param: '$(curl -s 'localhost:8080')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'xx param: '$(curl -s 'localhost:8080?prefix=xx&suffix=xx')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'oo param: '$(curl -s 'localhost:8080?prefix=oo&suffix=oo')
echo
echo '(sleep 5 => Keeping cache)'
echo
sleep 5

echo '----- Hit Cache -----'
date +%Y-%m-%d' '%H:%M:%S
echo 'no param: '$(curl -s 'localhost:8080')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'xx param: '$(curl -s 'localhost:8080?prefix=xx&suffix=xx')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'oo param: '$(curl -s 'localhost:8080?prefix=oo&suffix=oo')
echo
echo '(sleep 5 => Expired cache)'
echo
sleep 5

echo '----- Miss & ReMake Cache -----'
date +%Y-%m-%d' '%H:%M:%S
echo 'no param: '$(curl -s 'localhost:8080')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'xx param: '$(curl -s 'localhost:8080?prefix=xx&suffix=xx')
echo
sleep 1
date +%Y-%m-%d' '%H:%M:%S
echo 'oo param: '$(curl -s 'localhost:8080?prefix=oo&suffix=oo')
