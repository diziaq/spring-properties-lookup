This sample application demonstrate runtime inspection of Spring properties

Application starts on port 18765 by default.
Most of the properties are dummy samples.

###Run options

#####Standard:
  > mvn clean spring-boot:run

#####With debug mode
  > mvn clean spring-boot:run -P debug
  - (if needed attach a debugger to localhost:5005)

#####With verbose logging:
  > mvn clean spring-boot:run -D spring-boot.run.profiles=verbose

##Available endpoints

##### GET internal/properties/origin
Show properties per each origin (files, environment, etc.) considered by Spring 
 
##### GET internal/properties/config
Show effective properties applied by Spring from `application-***` files

##### GET internal/properties/all
Show all properties considered by Spring (same as `GET internal/properties/origin` without grouping)

## Printing to logs on startup

Class `EnvPropertiesLogOnStartup` demonstrates how to log properties on application startup (event ApplicationReadyEvent).
