# Set the base image to java8
FROM openjdk:8-alpine

# Define default environment variables
ENV ADVERTISE_HOME=/opt/advertise
ENV ADVERTISE_BINARIES=/opt/advertise/bin
ENV ADVERTISE_LOGGING_CONFIG=/opt/advertise/logs

# Create directory
RUN mkdir -p $ADVERTISE_BINARIES
RUN mkdir -p $ADVERTISE_LOGGING_CONFIG

# Set default directory
WORKDIR $ADVERTISE_HOME

# Copy advertise-service jar file
COPY target/*.jar $ADVERTISE_HOME/advertise.jar
COPY logs/logback-spring.xml $ADVERTISE_LOGGING_CONFIG/logback-spring.xml

# Add initialization script
ADD entrypoint.sh $ADVERTISE_BINARIES/entrypoint.sh

# Give permissions
RUN chmod 755 $ADVERTISE_BINARIES/entrypoint.sh

# Expose default servlet container port
EXPOSE 8081

# Main command
ENTRYPOINT ["/bin/sh", "/opt/advertise/bin/entrypoint.sh"]
