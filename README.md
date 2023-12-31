# compass-primeiro-desafio

Access and install MySql through the link: 

> https://dev.mysql.com/downloads/mysql/.

With MySql installed, run the query: 

```
CREATE DATABASE ecommerce
```
 
Update and select the created database.

In "Server", go to "Data Import" and import the file "ecommerce_product.sql" found in the "Main" branch of this repository.

Open the program package found in the "Master" branch in an IDE of your choice.

Extract the folder from the "mysql-connector-j-8.0.33.zip" file found in the "Main" branch of this repository and add it as an external library.
```This folder is the necessary connector for JDBC to work with MySql```

Now access db.properties.

In db.properties you will see the following screen:
```
user=****         /// Here you will enter your username
password=***      /// Here you will enter your password
url=jdbc:mysql://localhost:3306/ecommerce        /// Here you will enter your localhost, your port and keep "/ecommerce" as your database
useSSL=false
```

Now in the "application" module, run the program from the "Program" class.

# DESAFIO 1

A very important client asked us to create an E-commerce system for his company and that an MVP was initially shown so that they could follow the project in other steps.
For this he asked us for a specific structure and that we could act on top of it and bring good results.

Develop a shopping cart system in Java using MongoDB or MySQL as a database. The system must have the following features:

**Product Management:**

Each product must have a name, price and quantity available in stock.
Products must be stored in the MongoDB database.
It must be possible to add, remove and update products.

- `id`: a unique identifier for the product (it can be a `String` or an `int`).
- `name`: the name of the product (a `String`).
- `price`: the price of the product (a `double`).
- `quantity`: the available quantity of the product (an `int`).

**Shopping cart:**

A customer can add products to the shopping cart.
The shopping cart must store the products selected by the customer, along with the quantity of each product.
The total value of the shopping cart must be calculated based on the selected products and quantities.

**Sales Confirmation:**

Before confirming the sale, the system must request a confirmation from the customer.
The customer must confirm the purchase before the sale is completed.
After confirmation, the inventory of sold products must be updated.

***Technical requirements:***

The project must be developed in Java.
OBS*- Use the official MongoDB API to interact with the database or use an SQL database, such as MySQL, to store the data.
Use the JDBC library to connect and interact with the database.
Organize code into classes and use good programming practices.
Implement methods to add, remove, and update products .
Implement methods to add products to the shopping cart and calculate the total cart value.
Implement a sales confirmation flow before completing the sale.

*START-*

To start the project , create a private repository on github and add your instructors and SM as collaborators .

The project will be evaluated on the following requirements:

1- Does your application meet the basic requirements?

2- Code organization.

3- Structure of the created components.

4- Operation of the MVP (place order, appear in the cart, final value of the cart based on the quantity of products and sales confirmation).
5- Did you document how to configure the environment and run your application?
