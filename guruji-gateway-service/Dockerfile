FROM maven:3-jdk-8-alpine AS build

RUN apk -v --update add \
        python \
        py-pip \
        groff \
        less \
        && \
    pip install --upgrade awscli

#RUN unset AWS_DEFAULT_REGION && printenv AWS_DEFAULT_REGION || echo "does not exist"
#RUN unset AWS_ACCESS_KEY_ID && printenv AWS_ACCESS_KEY_ID || echo "does not exist"
#RUN unset AWS_SECRET_ACCESS_KEY && printenv AWS_SECRET_ACCESS_KEY || echo "does not exist"

#ARG AWS_DEFAULT_REGION
#ENV AWS_DEFAULT_REGION=$AWS_DEFAULT_REGION
#ARG AWS_ACCESS_KEY_ID
#ENV AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
#ARG AWS_SECRET_ACCESS_KEY
#ENV AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY

WORKDIR /usr/app/
COPY pom.xml /usr/app
COPY src /usr/app/src
COPY settings.xml /root/.m2/
COPY .aws /root/.aws
RUN mvn install

#RUN echo $AWS_ACCESS_KEY_ID

CMD [ "sh", "-c", "java -jar /usr/app/target/guruji-gateway-service-1.0.0-SNAPSHOT.jar" ]