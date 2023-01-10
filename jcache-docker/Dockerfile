FROM openliberty/open-liberty:full-java11-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Your Name" \
  org.opencontainers.image.vendor="IBM" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/OpenLiberty/guide-docker" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="Open Liberty" \
  name="system" \
  version="$VERSION-$REVISION" \
  summary="The system microservice from the Docker Guide" \
  description="This image contains the system microservice running with the Open Liberty runtime."

USER root
# make is for snapit
# porcps-ng is for vmstat, ps, top (in linperf.sh)
# net-tools is for netstat (in inperf.sh)
# gdb is for gcore
ADD --chown=1001:0 https://github.com/hazelcast/hazelcast/releases/download/v5.2.1/hazelcast-5.2.1.zip /output/resources
RUN dnf install -y make procps-ng net-tools gdb unzip

RUN unzip -d /output/resources/ /output/resources/hazelcast-5.2.1.zip && mkdir /output/resources/hazelcast \
    && mv /output/resources/hazelcast-5.2.1/lib/hazelcast-5.2.1.jar /output/resources/hazelcast/hazelcast.jar \
    && rm -fr /output/resources/hazelcast-5.2.1* && chown -R 1001:0 /output/resources/
# COPY --chown=1001:0 trapit /config/
COPY --chown=1001:0 config/server.xml /config/
COPY --chown=1001:0 config/jvm.options /config/
COPY --chown=1001:0 hazelcast.xml.custom /output/resources/hazelcast/hazelcast.xml
COPY --chown=1001:0 apps/httpsession.war /config/apps/

USER 1001
