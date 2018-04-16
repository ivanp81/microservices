#!/bin/bash
set -e

SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-dev}

# Start advertise-index application with specific JVM_ARGS and SPRING_PROFILE
java ${JVM_ARGS} -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} ${ADVERTISE_INDEX_HOME}/advertise-index.jar
