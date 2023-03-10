# Spring-app

A simple login application using React + spring boot, JWT tokens and embedded H2 database

## Project set up

### Spring boot

1. Configure token secret in application.properties
2. Configure user credentials in DemoApplication.java

![image](https://user-images.githubusercontent.com/95296449/224205553-4a3a1a5a-c1f5-4765-86d1-07f3e7021426.png)

3. Open a new terminal and navigate to /demo. Run the application using :
```
mvn spring-boot:run
```
Backend will be served from http://localhost:8080

### React
4. Open a new terminal and navigate to /demo/react-app. Run the application using :
```
npm install
npm start
```
Backend will be served from http://localhost:8080

5. Login with the credentials you created in step 2
