
FROM frolvlad/alpine-oraclejdk8
#指定维护者信息
MAINTAINER zzf
VOLUME /tmp
ADD target/RiverInspection-0.0.1-SNAPSHOT.jar bootDemo.jar
RUN sh -c 'touch /bootDemo.jar'
ENV JAVA_OPTS=""
EXPOSE 8089
#指定执行启动spring boot小项目     ENTRYPOINT 为容器启动后执行的命令

ENTRYPOINT ["java","-jar","/bootDemo.jar"]

