#!/bin/bash
. /etc/profile
. ~/.bash_profile
pull_log=`git pull`
echo "$pull_log"
if [[ $pull_log == "Already up-to-date." ]] ; then
    echo "project not update"
    exit
else
    echo "project updateing..."
fi
echo "project restart..."
mvn clean && mvn package -Dmaven.test.skip=true
echo "maven project package success"
#获取端口号
port=8401
jar_path=/digov/project/zlb_login_api/target/zlb_login_api-0.0.1-SNAPSHOT.jar
active=pre
function getPid() {
    echo `netstat -tunlp | grep $port | grep 'java' | head -2 | tail -1 | awk '{print $7}' | awk -F / '{print $1}'`
}
pid=`getPid`
if [[ $pid != "" ]] ; then
    echo "project running..."
    echo "project pid=" + $pid
    kill -9 $pid
    pid=`getPid`
else
    echo "project not running!!!"
fi

while [[ $pid != "" ]]; do
    echo "project shutdown..."
    sleep 1
    pid=`getPid`
done

nohup java -server -Xmx500m -Xms500m -XX:MaxDirectMemorySize=100m -jar $jar_path --spring.profiles.active=$active &

pid=`getPid`
while [[ $pid == "" ]]; do
    echo "project starting..."
    sleep 1
    pid=`getPid`
done
echo "project run success ,pid $pid"
