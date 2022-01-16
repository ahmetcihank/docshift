# Tech Stack

Java 17 , Postgre Database, Spring Boot, Junit 5 , LomBok Library

For starting app:
 * Clone the project
 * docker network create docshiftnetwork
 * create package:  mvn clean install -Dmaven.test.skip=true  -->Skip test because app.prop file is empty i provided credentials in docker-compose file
 * then simply: docker-compose up

# Api Documentation 

* Profile Create <br />
  Http Method -> POST <br />
  http://localhost:8080/profile/create
  <br />Request Body : 
  {
  "gender":"MALE",
  "phoneNumber":"123123",
  "address":"address",
  "username":"cihan"
  }
  <br /> Response Body:
  {
  "message": "userPP: cihan has been created",
  "timestamp": 1642340948836
  }

  <br />
  <br />

* User  Create <br />
  Http Method -> POST <br />
  http://localhost:8080/user/create
  <br />Request Body :
  {
  "name":"denem112e",
  "lastName":"denemee12",
  "username":"cihan"
  }
  <br /> Response Body:
  {
  "message": "user: denem112e has been created",
  "timestamp": 1642357178553
  }

  <br />
  <br />

* All User With Profile <br />
  Http Method -> GET <br />
  http://localhost:8080/user/allusersWithProfiles
 
  <br /> Response Body:
    [
        {
        "id": 1,
        "username": "cihan",
        "name": "jack",
        "lastname": "joe",
        "createdDate": 1642340931998,
        "phoneNumber": "123123",
        "address": "address",
        "gender": "MALE",
        "dateOfBirth": null
        }
    ]

  <br />
  <br />

* All Users<br />
  Http Method -> GET <br />
  http://localhost:8080/user/allusers

  <br /> Response Body:
  [
    {
        "id": 1,
        "username": "cihan",
        "name": "jack",
        "lastname": "joe",
        "createdDate": 1642340931998
    }]

  <br />
  <br />

* User by userName <br />
  Http Method -> GET <br />
  http://localhost:8080/user/{username}
  <br /> Response Body:
  [
    {
        "id": 1,
        "username": "cihan",
        "name": "jack",
        "lastname": "joe",
        "createdDate": 1642340931998
    }
]


* DELETE by userName <br />
  Http Method -> DELETE <br />
  http://localhost:8080/user/delete/{username}
  <br /> Response Body:
  {
  "message": "user: cihan has been deleted",
  "timestamp": 1642341007581
  }
  <br />
  <br />

* UPDATE user <br />
  Http Method -> PUT <br />
  http://localhost:8787/user/update
  <br />Request Body :
  {
  "id":2,
  "name":"jack",
  "lastName":"joe",
  "username":"cihan",
  "gender":"MALE",
  "phoneNumber":"123123",
  "address":"addressupdated",
  "dateOfBirth":"2012-12-01"
  }
  <br /> Response Body:
  "User has been updated"
