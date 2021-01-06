# Request Challenge

On this repository I created 2 microservices using JAVA and SPRING.One microservices will take care of the customer
request and the other will gather statistics about the valid and invalid request that arrive to the first one.
The communication is done through API calls, the request microservice will send a request to the statistics 
microservice, without caring if the request was succesful or not. Some of the choices I made during the process:

 * Created two microservices as the statistics functionality seemed less critical, as opposed that having a service
 that handles both functionality.
 * I decided to handle the dates in epoch seconds, as I feel it is easier to use ( as a programmer ) and avoids dealing
 with different date formats. On addition the statistics API will be mainly used by another service, so we can avoid 
 human formatting. However some support to handle human dates could be further added, as I did with the get statistics
 end point that supports both epoch seconds and "d/MM/yyyy" date format.
 * I decided that the validation of the request fields, (
 * ...


## Local Usage

The project uses java 15 and spring After you cloned the git repository, you need to start both microservices separately.
First go into the request folder and run:go inside the main project folder and execute the next command to run and build
the application: 

```bash
mvnw spring-boot:run
```
That will start the requedt microservice on port 8080. To start the statistics microservice go into the statistics folder
and run the same command: 
```bash
mvnw spring-boot:run
```

That will start the statistics microservice on port 8081.

## Local Usage of Request Microservice
The request microservice will accept post request with complete and valid data for the creation of a user . If a field is missing
or not valid the request won't be processed, the request will be checked for customer not allowed or blacklisted IPs. Wether the
request is valid or invalid, the data will be sent to the statistics microservice to store the request attempt.
The application is running on localhost:8080, so you will need to append your request to this path. 

## Post user
#Request
`POST /user/create`

# Blaclisted IP's
The request will count as invalid request if contains any of these IP's: "111.111.111.111", "555.555.555"


# Banned customers
The request will count as invalid request if the customer if if any of these: 42, 288, 13

# Valid sample request
```bash
curl --location --request POST 'localhost:8080/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId":"aaaaaaaa-bbbb-cccc-1111-222222222222",
    "customerId":11,
    "tagId":2,
    "remoteIP": "132.456.732.2"
}'
```
# Invalid sample request
```bash
curl --location --request POST 'localhost:8080/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId":"aaaaaaaa-bbbb-cccc-1111-222222222222",
    "customerId":42,
    "tagId":2,
    "remoteIP": "132.456.732.2"
}'
```


