# Dropwizard Example

A simple example of creating Dropwizard Application. This project is tested with dropwizard 2.x and Java 8.

## Features
- REST Endpoint
- Authentication and Role Based Authorization with JWT. In this example we use identityserver4 demo server. It can be change to any oauth2/jwt server.
- Swagger page to test the API
- Unit Test
- Validation of POJO request with annotation
- Custom Appender for logging
- Accesing HttpServletRequest variable
- Dockerized


## Build
- Use mvn package to build the module into jar file
```shell
mvn clean package
```
- Create configuration file ( see config example in config.yml in the project repo)
- Run the resulting jar:
```shell
java -jar dropwizard-example.jar server <config_file_path>
```
## Docker
- Build the package as above in [Build]
- Go to the root directory of the project and run:
```shell
docker build -t dropwizard-sample .
```
- And then run the docker image:
```shell
docker run -p 8080:8080 dropwizard-sample
```
- Go to the browser and try to open swagger in http://localhost:8080/swagger

  

## Configuration

- Please check the config.yml example in the repo
- The configuration example is based on use-case of application authenticating using identityserver4 demo url.

## Feedback
For feedback, please raise issues in the issue section of the repository. Periodically, I will update the example with more real-life use case example. Enjoy!!.

