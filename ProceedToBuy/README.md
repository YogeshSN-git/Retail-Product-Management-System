# ProceedToBuy Microservice
An authorized customer can add product to cart and view the product details(Product Price, Delivery Date, Vendor details) in the checkout/cart page.If Product is not available, the customer can add the product to wishlist.

## Requirements
For building and running the application you need,
  * JDK 1.8
  * Maven
  
## Steps to run
* Build the project using mvn clean install
* Run using mvn spring-boot:run
* The web application is accessible via localhost:8081/buy

## Steps to execute DB scripts
* No Physical database required. We use an In-memory database(H2) here.
