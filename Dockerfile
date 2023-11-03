FROM openjdk:11

# Set environment variables
ENV APP_HOME=/app
ENV APP_JAR=shopfast-23.26.0.jar

# Create the application directory
WORKDIR $APP_HOME

# Copy the compiled JAR file into the container
COPY build/libs/$APP_JAR $APP_HOME/$APP_JAR

# Expose the port that your application will run on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "shopfast-23.26.0.jar"]