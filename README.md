# Test task
## _Monitoring service_

## Requirements:
- ~~Use Java 8/11 and your favourite set of frameworks/libraries~~
- ~~Publish the code in a Git repository~~
- Make the following items configurable via environmental variables:
    - ~~URL to be monitored, e.g.: https://jnetworks.by or http://localhost:8080~~
    - ~~Schedule (cron expression), e.g.: 0 0/1 * 1/1 * ? *~~
    - ~~Email service configuration (SMTP): host, port, username, password~~
    - ~~Email address, e.g. monitoring.service@abc.net~~
- Implement the following actions via RESTful API methods:
    - ~~Start monitoring~~
    - ~~Stop monitoring~~
    - ~~Get the latest monitoring result~~
- ~~Check URL availability at the socket level~~
- ~~Design a template for the email message with monitoring result~~
- ~~Log important application events to a file using any logging mechanism~~
- Deploy service in a Docker container +- (I can't set up the network for docker daemon correctly on my computer. So on my device application run, but doesn't connect to smtp server. Maybe on other devices this will be work correctly.)

#### Building

- git clone https://github.com/MechAdept/EnableUTestTask.
- configure enviroment variables
 ```sh
SMTP_HOST=smtp.gmail.com
SMTP_PORT=8080
SMTP_USERNAME=enableUTestAddress@gmail.com
SMTP_PASSWORD=mzmicstejlhblzkt
ENV_URL=localhost
MESSAGE_DESTINATION=enableUTestAddress@gmail.com
SCHEDULER_AVAILABILITY=*/30 * * * * *
```
- mvn clean install
- mvn spring-boot:run

## Docker
Download docker image https://hub.docker.com/repository/docker/docker2171/my-test-repository
or
To build image and run container execute next commands:
- mvn install
- mvn clean package spring-boot:repackage
- docker build -t test-app:v1 .
# run docker container
    - docker run --env-file ./env.list -p 8080:8080 -p 465:465 "docker image hash"
     or
    - docker run -p 8080:8080 -p 465:465 -e SMTP_HOST='smtp.gmail.com' -e SMTP_PORT='8080' -e SMTP_USERNAME='enableUTestAddress@gmail.com' -e SMTP_PASSWORD='mzmicstejlhblzkt' -e ENV_URL='localhost' -e MESSAGE_DESTINATION='enableUTestAddress@gmail.com' -e SCHEDULER_AVAILABILITY="*/30 * * * * *"" "docker image hash"
