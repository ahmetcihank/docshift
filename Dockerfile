From openjdk:17
copy ./target/usermanagement-1.0.jar usermanagement-1.0.jar
CMD ["java","-jar","usermanagement-1.0.jar"]
