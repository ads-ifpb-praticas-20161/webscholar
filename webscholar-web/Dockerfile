# Glassfish 4.1 with JDK 8 Docker image
# Based on: https://github.com/aws/aws-eb-glassfish-dockerfiles/blob/aafcfc5e812dfb9b998105d3ca9da1b7f10664e1/4.0-jdk7/Dockerfile

FROM        java:8-jdk

MAINTAINER  Almir Dzinovic <almirdzin@gmail.com>

ENV         JAVA_HOME         /usr/lib/jvm/java-8-openjdk-amd64
ENV         GLASSFISH_HOME    /usr/glassfish4
ENV         PATH              $PATH:$JAVA_HOME/bin:$GLASSFISH_HOME/bin

RUN         apt-get update && \
            apt-get install -y curl unzip zip inotify-tools && \
            rm -rf /var/lib/apt/lists/*

RUN         curl -L -o /tmp/glassfish4.zip http://download.java.net/glassfish/4.1.1/release/glassfish-4.1.1.zip && \
            unzip /tmp/glassfish4.zip -d /usr && \
            rm -f /tmp/glassfish4.zip && \
            find /usr/glassfish4/glassfish/ -name "*.bat" -exec rm -f {} \;



EXPOSE      8080 1098 1099 3873 3700

WORKDIR     /usr/glassfish4

VOLUME /usr/glassfish4/glassfish/domains/domain1/logs

RUN mkdir /usr/app
VOLUME /usr/app

ADD fastDeploy.sh $WORKDIR

RUN tr -d $'\r' < fastDeploy.sh > redeploy.sh && chmod +x redeploy.sh

ADD target/webscholar-web.war $WORKDIR
ADD target/webscholar-web/WEB-INF/lib/ /usr/glassfish4/glassfish/lib/

CMD asadmin start-domain && \
    asadmin create-jms-resource --restype javax.jms.ConnectionFactory jms/QueueConnectionFactory && \
    asadmin create-jms-resource --restype javax.jms.Queue  jms/teacherQueue && \
    asadmin create-jms-resource --restype javax.jms.Queue jms/emailQueue && \
    asadmin deploy webscholar-web.war  && tail -f /dev/null
