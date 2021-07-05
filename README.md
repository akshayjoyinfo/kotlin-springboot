# Getting Started

### Requirements
* Springboot : 2.5.2
* Java: 11
* Gradle

### Clean & Build the Project
Clean and Build the Project

    gradlew clean build

### Run the Project
Run the below command from the root of the repo
Location where gradlew.bat file exists

    gradlew bootRun

everything is good, then you can see the below messages

    ` 2021-07-05 15:15:53.655  INFO 13324 --- [           main] c.w.r.RestaurantListingApplicationKt     : Started RestaurantListingApplicationKt in 2.509 seconds (JVM running for 2.864)`

launch the app using swagger and test, if the port 9000 used by some other apps
modify the application.properties file property 
    
    server.port=${PORT:9000}
    
Launch the swagger URL from the below URL
    
    http://localhost:9000/swagger

### Using the App

There are two ways you can access/operate the App using Swagger / Postman

#### 1. Swagger

Launch swagger url by launching the below url

    http://localhost:9000/swagger

### Tests

    gradlew clean test

### Deployment

I have used Heroku to deploy the code through heroku pipeline,
any commits to main can deploy code automatically to heroku

Deployed version of current code

    https://shielded-woodland-78603.herokuapp.com/swagger
