FROM openjdk:11.0.13-jre-slim

COPY Phonebook.jar /src/user/Phonebook/

WORKDIR /src/user/Phonebook

CMD ["java", "-jar", "Phonebook.jar"]