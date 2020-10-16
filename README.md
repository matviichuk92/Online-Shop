# **online grocery shop**
__________________
**Project purpose**
__________________
An online project in the form of an internet shop prototype with a connection to a database (DB).
The shop presented in this project has the following functionality:
- registering users and saving hashed passwords in the DB;
- role-based access to pages for users and admins;
- users can: look through the products in the store and add them to their cart, edit the cart, 
place orders, and see all their previous orders;
- admins have access to all information about the users, their orders, and products in the store,
which they can edit and delete.
___________________________

**Project structure and details**
_________________
The project has an N-tier architecture: 
- controller(request processing and response sending);
- service(business logic);
- DAO(CRUD operation with DB);

The project also features four major entities: Product, User, ShoppingCart and Order.
 
The project follows to SOLID, KISS, and DRY principles. It was created with Java EE, MySQL, Maven, and Apache Tomcat.
_______________ 
**Launch guide**
__________________
- Download the project to your machine;
- Add Maven;
- Download Tomcat from _tomcat.apache.org_, unzip, add it to _install package_, add the new configuration
(if you use IntelJ) TomCat Server(choose "Local" and point install package) in project, 
choose the artifact _war-exploded_, in _application context_ field just put slash symbol;
- Using MySQL Workbench 8.0 CE create a schema and tables as specified in init-db.sql;
- Launch the project using the Tomcat Server;
- Log in to the website, click on the button "Inject data" and check the user functionality;
- Logout and log in as an admin using login "boss" and password "123" and check the admin functionality;
____________________________________

[my gitHub]: https://github.com/matviichuk92