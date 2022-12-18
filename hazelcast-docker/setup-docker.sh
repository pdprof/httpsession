#!/bin/bash
#curl -O https://www.ibm.com/support/pages/system/files/support/swg/swgtech.nsf/0/d83af3cb5f0490d1852579d600618374/$FILE/trapit.002/trapit
#if [ ! -f trapit ]; then
#     echo "===================="
#     echo "Please download trapit from https://www.ibm.com/support/pages/node/709009 and put it on the same directory."
#     echo "====================" 
#     exit 1
#fi
#chmod 755 trapit
if [ -z $ACCESS_HOST ]; then
   echo 'Please set the "ACCESS_HOST" environment variable and try again.'
   exit 2
fi

# Setup for derby
sed s/localhost:5701/$ACCESS_HOST:5701/g config/hazelcast.xml > hazelcast.xml.custom
docker build -t httpsession-base -f Dockerfile.base .
docker build -t httpsession .
rm hazelcast.xml.custom
