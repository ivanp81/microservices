#!/usr/bin/env bash
if test -z "$MONGODB_ADVERTISE_PASSWORD"; then
    echo "MONGODB_ADVERTISE_PASSWORD not defined"
    exit 1
fi

echo "Creating mongo users..."
mongo advertise --eval "db.createUser({user: 'advertise', pwd: '$MONGODB_ADVERTISE_PASSWORD', roles: [{role: 'readWrite', db: 'advertise'}]});"
echo "Mongo users created."


