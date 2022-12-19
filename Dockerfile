FROM openjdk:11

COPY target/stock-management.jar stock-management.jar

ENTRYPOINT ["java","-jar" ,"/stock-management.jar"]