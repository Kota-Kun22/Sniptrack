#use a maven image with java 21 (which i have) to build the Spring boot app
FROM eclipse-temurin:21-jdk as build

#Set the working directory
WORKDIR /app

#copy the maven wrapper and pom.xml
COPY mvnw ./
COPY .mvn/ .mvn/

#Ensure the maven wrapper is executable
RUN chmod +x mvnw


#Copy the pom.xml and install dependencies
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

#copy the source code and build the application
COPY src ./src
RUN ./mvnw clean package -DskipTests

#use a java 21 runtime image to run the application
FROM eclipse-temurin:21-jre

#set the working directory
WORKDIR /app

#copy the build JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

#expose port 8080
EXPOSE 8080

#specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]