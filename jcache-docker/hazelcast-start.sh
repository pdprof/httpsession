#!/bin/bash
if [ -z $ACCESS_HOST ]; then
   echo 'Please set the "ACCESS_HOST" environment variable and try again.'
   exit 2
fi

docker run --name hazelcast -d -e HZ_CLUSTERNAME=hello-world -p 5701:5701 hazelcast/hazelcast:5.2.1
