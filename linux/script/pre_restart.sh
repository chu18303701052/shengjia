#!/bin/bash
#判断项目是否启动
pid=`netstat -tunlp | grep '8080' | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
if [[ $pid != "" ]] ; then
    echo "project running..."
    echo "project pid=" + $pid
    kill -9 $pid
    pid=`netstat -tunlp | grep '8080' | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
else
    echo "project not running!!!"
fi

while [[ $pid != "" ]]; do
    echo "project shutdown..."
    sleep 1
    kill -9 $pid
    pid=`netstat -tunlp | grep '8080' | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
done

nohup java -Xmx512m -Xms128m -jar digov_api.jar --spring.profiles.active=pre &

pid=`netstat -tunlp | grep '8080' | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
while [[ $pid = "" ]]; do
    echo "project running..."
    sleep 1
    pid=`netstat -tunlp | grep '8080' | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
done

echo "project run success ,pid $pid"