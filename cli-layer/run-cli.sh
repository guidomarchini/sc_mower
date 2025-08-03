#!/bin/bash

# Always run from the script's directory
cd "$(dirname "$0")" || { echo "Failed to cd to script directory"; exit 1; }

# Build the project from the root
mvn clean install || { echo "Build failed"; exit 1; }

# Run the CLI application
java -jar target/cli-layer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
