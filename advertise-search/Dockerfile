# Set the base image to java8
FROM openjdk:8-alpine

# Add bash 
RUN /bin/sh -c "apk add --no-cache bash"

# Define default environment variables
ENV ADVERTISE_SEARCH_HOME=/opt/advertise-search
ENV ADVERTISE_SEARCH_BINARIES=/opt/advertise-search/bin
ENV ADVERTISE_SEARCH_LOGGING_CONFIG=/opt/advertise-search/logs

# Create directory
RUN mkdir -p $ADVERTISE_SEARCH_BINARIES
RUN mkdir -p $ADVERTISE_SEARCH_LOGGING_CONFIG

# Set default directory
WORKDIR $ADVERTISE_SEARCH_HOME

# Copy advertise-search-service jar file
COPY target/*.jar $ADVERTISE_SEARCH_HOME/advertise-search.jar
COPY logs/logback-spring.xml $ADVERTISE_SEARCH_LOGGING_CONFIG/logback-spring.xml

# Add initialization script
ADD entrypoint.sh $ADVERTISE_SEARCH_BINARIES/entrypoint.sh

# Give permissions
RUN chmod 755 $ADVERTISE_SEARCH_BINARIES/entrypoint.sh

# Expose default servlet container port
EXPOSE 8080

# Main command
ENTRYPOINT ["/bin/sh", "/opt/advertise-search/bin/entrypoint.sh"]
