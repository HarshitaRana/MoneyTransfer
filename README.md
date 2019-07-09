# RESTful API for Money Transfer

A RESTful API based on following farmeworks & technologies used for money transfer between accounts

### Stack
- Java
- Jersey
- JAX-RS
- Maven
- JUnit
- H2 database (in-memory)
- Log4j
- Jetty Server

### Building and Running the application

To build the application and execute in built tests, execute:

##### mvn clean install

To run the application, execute:

##### mvn exec:java

### Application End points:

##### GET - http://localhost:8080/account/getAll

Will fetch all accounts

##### GET - http://localhost:8080/account/{accountId}

Will fetch given account's information

##### PUT - http://localhost:8080/account/transfer

Transfer funds between accounts. Use below Json as input:

{  
   "payeeCurrency":"USD",
   "amount":100.00,
   "fromAccountId":1,
   "toAccountId":2
}

