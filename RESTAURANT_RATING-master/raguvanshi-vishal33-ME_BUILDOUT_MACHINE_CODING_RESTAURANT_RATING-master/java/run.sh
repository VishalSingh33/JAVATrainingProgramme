#!/bin/bash

# ./gradlew clean build -x test --no-daemon
# java -jar build/libs/example.jar INPUT_FILE=sample_input/sample_input_one.txt

# or
./gradlew run -q --args="INPUT_FILE=sample_input/sample_input_one.txt"