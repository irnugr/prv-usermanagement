# prv-usermanagement

Service to maintain user credentials, user access, and user menu

## Specification to run this service
- Java 11
- Maven 3.8.1

## Command to start the service using maven
mvn spring-boot:run -Dspring-boot.run.profiles={dev|sit|uat|prd}

## Command to start the service using java
java -Dspring.profiles.active={dev|sit|uat|prd} -jar {jarfile}
