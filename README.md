# Organic_Vegetables (Online Vegetable Sales Application)
# BackEnd(REST API)for an Online Vegetable Sales Application
<br>
<br>

![img-20230328-wa0017_360](https://user-images.githubusercontent.com/113039160/229365892-1338489d-272d-4614-a4b6-0881daa4ed57.jpg)


<br>
<br>


- An online vegetable sales application allows customers to easily purchase fresh, locally sourced produce from their phone or computer.
- The application features a user-friendly interface, allowing customers to browse and select from a wide variety of fruits and vegetables.
- Customers can customize their order and choose a delivery or pickup option that works for them.
- The backend of the application is built using Java and Spring Framework.
- The backend handles all the business logic and communicates with the database (MySQL).
- The backend exposes RESTful endpoints for the frontend to consume.
- The code follows best practices for maintainability and scalability.
- The application is continuously integrated and deployed.
- This repository is open-source and contributions are welcome.
- The application is built using modern technologies such as Spring Boot.
- It includes a swagger documentation for the endpoints.
- The application follows best practices for security in terms of input validation and access control.

<br>
</br>

#E-R Diagram


![ER](https://user-images.githubusercontent.com/105914736/229365750-e2f68b2d-fc3a-464f-ae5d-e46fd0cad280.png)

<br>
<br>


#WorkFlow
<br>

![WorkFlow](https://user-images.githubusercontent.com/107461052/212683750-11c64541-9c01-4137-9e1f-539b31994b85.png)


<br>

* This project is developed by team of 5 Aspiring Developers . 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* Mysql

------------------------------------------------------------------------------
## Modules
------------------------------------------------------------------------------
* Login, Logout Module
* Customer Module
* Admin Module
* AddToCart Module
* Order Module
* Vegetable Stock Module

---------------------------------------------------------------------------------
## Features

---------------------------------------------------------------------------------
* Customer and Admin authentication & validation with usersession's sessionid.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid sessionid can add/update/delete Vegetable in the database.
    * Admin can access the details of orders, bill details, customer list.
* Customer Features:
    * Registering themselves with application, and logging in to get the valid sessionid.
    * Customer can add vegetables into the cart
    * Only logged in user can access their all order history.

--------------------------------------------------------------------------------
## Contributors
--------------------------------------------------------------------------------
*<a href="https://github.com/Sadanand012">Sadanand Mare</a>
<br>
*<a href="https://github.com/Akash-376">Akash Chauhan</a>
<br>
*<a href="https://github.com/Patu18122000">Prathamesh Chavan</a>
<br>
*<a href="https://github.com/MrVivek30">Vivek Chowdhary</a>
<br>
*<a href="">Arun Kumar</a>


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](GrennBasket\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8080
    spring.datasource.url=jdbc:mysql://localhost:3306/organic;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

## API Root Endpoint

`https://localhost:8080/`

`http://localhost:8080/swagger-ui.html`


## API Module Endpoints

### Customer Module


* `POST /customer` : Register customer by providing valid details
