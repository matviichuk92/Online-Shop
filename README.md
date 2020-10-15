# **online grocery shop**
__________________
**Project purpose**
__________________
Create online project on base localhost with connection to DataBase 
with next functionality:
- register users and save password in hashing view in DB;
- access to pages according to roles(user and admin);
- users can: look through products and add it to own cart, edited cart with products, 
create orders and see all own orders;
- admin have access to all information about users, orders, products with ability edit and delete them.
___________________________

**Project structure and details**
_________________
Project have N-tier architecture: 
- controller(request processing and response sending);
- service(business logic);
- DAO(CRUD operation with DB);

Also, four major entities: Product, User, ShoppingCart and Order.
 
Project correspond to SOLID, KISS and DRY principles. Created using Java EE, MySQL, Maven, Apache Tomcat.
_______________ 
**Launch guide**
__________________
- Fork project;
- Added Maven;
- Download Tomcat from _tomcat.apache.org_, unzip, added it to _install package_, add the new configuration
(if you use IntelJ) TomCat Server(choose "Local" and point install package) in project, 
choose the artifact _war-exploded_, in _application context_ field left just slash symbol;
- Using MySQL Workbench 8.0 CE create a schema and tables as specified in init-db.sql;
- Let's registration like regular user;
- Let's login to site and push button "Inject data" and check user functionality;
- Logout and login like admin(login - boss, password - 123) and check admin functionality;
____________________________________
**Roman Matviichuk**
**matviihuk.office@gmail.com**
