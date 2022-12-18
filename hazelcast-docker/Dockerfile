FROM httpsession-base

RUN unzip -d /output/resources/ /output/resources/hazelcast-5.2.1.zip && mkdir /output/resources/hazelcast \
    && mv /output/resources/hazelcast-5.2.1/lib/hazelcast-5.2.1.jar /output/resources/hazelcast/hazelcast.jar \
    && rm -fr /output/resources/hazelcast-5.2.1* && chown -R 1001:0 /output/resources/
# COPY --chown=1001:0 trapit /config/
COPY --chown=1001:0 config/server.xml /config/
COPY --chown=1001:0 config/jvm.options /config/
COPY --chown=1001:0 hazelcast.xml.custom /output/resources/hazelcast/hazelcast.xml
COPY --chown=1001:0 apps/httpsession.war /config/apps/

USER 1001
