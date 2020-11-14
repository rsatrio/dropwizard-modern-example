# Dropwizard Example

A simple example of creating Dropwizard Application. This project is tested with dropwizard 2.x and Java 8.

## Features
- REST Endpoint
- Authentication and Role Based Authorization with JWT. In this example we use identityserver4 demo server. It can be change to any oauth2/jwt server.
- Swagger page to test the API
- Unit Test


## Build
- Use mvn package to build the module into jar file
> mvn clean package

- Create configuration file ( see config example in config.yml in the project repo)
- Run the resulting jar:

> java -jar dropwizard-example.jar server <config_file_path>


## Configuration
- Please check the config.yml example in the repo
- The configuration example is based on use-case of application authenticating using identityserver4 demo url.

## Feedback
For feedback, please raise issues in the issue section of the repository. Periodically, I will update the example with more real-life use case example. Enjoy!!.

