# PDPro Application

## Requirements

- [Docker](https://www.docker.com/)

## Test on Docker

### Build docker image

```
setup-docker.sh
```

### Start docker 
```
./hazelcast-start.sh
./httpsession-start.sh
```

Now you can access http://localhost:9080/httpsession/sesssion


## Test on OpenShift

*** WORK AREA ***

Now working on update setup-openshift.sh

After you setup CRC described at [icp4a-helloworld](https://github.com/pdprof/icp4a-helloworld)

You can use following script. 
```
setup-openshift.sh
```

Now you can access to http://.../

Other test steps are same with docker.
