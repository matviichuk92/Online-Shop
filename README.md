# **online grocery shop**
__________________
**Project purpose**
__________________
Create online project on base localhost with connection to DataBase 
with next functionality:
- register users and save password in hashing view in DB;
- access to pages according to roles(user and admin);
- users can: look through products and add it to own cart, edit cart, 
create orders and see all own orders;
- admin have access to all information about users, orders, products with ability to edit and delete them.
___________________________

**Project structure and details**
_________________
Project have N-tier architecture: 
- controller(request processing and response sending);
- service(business logic);
- DAO(CRUD operation with DB);

Also, four major entities: Product, User, ShoppingCart and Order.
 
Project correspond to SOLID, KISS and DRY principles. It's created with Java EE, MySQL, Maven, Apache Tomcat.
_______________ 
**Launch guide**
__________________
- Fork project;
- Add Maven;
- Download Tomcat from _tomcat.apache.org_, unzip, add it to _install package_, add the new configuration
(if you use IntelJ) TomCat Server(choose "Local" and point install package) in project, 
choose the artifact _war-exploded_, in _application context_ field just put slash symbol;
- Using MySQL Workbench 8.0 CE create a schema and tables as specified in init-db.sql;
- Register like regular user;
- Log in to site and push button "Inject data" and check user functionality;
- Logout and log in like admin(login - boss, password - 123) and check admin functionality;
____________________________________
**Roman Matviichuk**
**matviihuk.office@gmail.com**
