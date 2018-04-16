# Set the base image to java8
FROM openjdk:8-alpine

# Add bash 
RUN /bin/sh -c "apk add --no-cache bash"

# Define default environment variables
ENV ADVERTISE_INDEX_HOME=/opt/advertise-index
ENV ADVERTISE_INDEX_BINARIES=/opt/advertise-index/bin
ENV ADVERTISE_INDEX_LOGGING_CONFIG=/opt/advertise-index/logs

# Create directory
RUN mkdir -p $ADVERTISE_INDEX_BINARIES
RUN mkdir -p $ADVERTISE_INDEX_LOGGING_CONFIG

# Set default directory
WORKDIR $ADVERTISE_INDEX_HOME

# Copy advertise-index-service jar file
COPY target/*.jar $ADVERTISE_INDEX_HOME/advertise-index.jar
COPY logs/logback-spring.xml $ADVERTISE_INDEX_LOGGING_CONFIG/logback-spring.xml

# Add initialization script
ADD entrypoint.sh $ADVERTISE_INDEX_BINARIES/entrypoint.sh

# Give permissions
RUN chmod 755 $ADVERTISE_INDEX_BINARIES/entrypoint.sh

# Expose default servlet container port
EXPOSE 8080

# Main command
ENTRYPOINT ["/bin/sh", "/opt/advertise-index/bin/entrypoint.sh"]
