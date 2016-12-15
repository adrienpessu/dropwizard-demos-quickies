# Simple example of a rest api for the JUG quickies


## Getting started

First clen install the project
````
mvn clean install
````
Then you must start a mongodb server on your own or using the docker-compose file 
```docker-compose up -d```
If you want to use a database on a different server or a different port, please change settings in the config.yml file.
 

````
java -jar target/dropwizard-jug-nantes-1.0.0-SNAPSHOT.jar server config.yml
````
 
## Usage

You could import the Postman collection in quicky-jug-nantes.postman_collection.json for testing purpose. 