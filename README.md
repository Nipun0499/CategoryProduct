# CategoryProduct
Simple CRUD operations REST API Spring Boot application with Actuators and Swagger Implementation
Includes Concept of Bidirectional Mapping

Spring Boot REST API Documentation Using Swagger 2 With Springfox

To open the swagger you can go to http://localhost:8080/swagger-ui.html

# Screenshot
![swagger](https://user-images.githubusercontent.com/59878125/191770715-a7e2f749-3363-46a9-ab1f-8e1d83f96f46.png)



# SAMPLE PAYLOADS


# For Category


{
	"category_id" : 1,
    "products":[],
	"category_name" : "Healthcare",
	"category_desc" : "Improves Health",
	"createDate" : "2022-01-09",
  "updateDate" : "2022-08-22",
	"active" : true,
	"deleted" : false 
}


# For Product


{
    "category_id": 1,
    "c":
    {
        "category_name" : "Body care",
        "category_desc" : "For basic body needs",
        "createDate" : "2022-01-09",
        "updateDate" : "2022-08-22",
        "active" : true,
        "deleted" : false 
    },
    "product_name": "Shampoo",
    "product_brand": "Dove",
    "product_desc": "Good for hairs",
    "price": 50,
    "createDate" : "2022-01-09",
    "updateDate" : "2022-08-22",
    "active": true,
    "deleted": false
}




