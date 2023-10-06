package org.example.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dataModels.ProductDM;
import org.example.utils.Log;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class API_Test {

    String baseURI = "http://localhost:3030";
    String product_id ="";


    @Test( enabled = true, priority = 0)
    public void testRetrieveProductInformation() {

        Log.startTestCase("Verify the successful retrieval of product information");
        // Set base URI for the API
        Log.info("Set base URI for the API : "+RestAssured.baseURI);
        RestAssured.baseURI = baseURI;

        Log.info("Send a GET request to retrieve product information");
        Response response = given()
                .pathParam("id", "43900")
                .when()
                .get("/products/{id}");

        Log.info("Response: " + response.asString());
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");
        assertEquals(response.jsonPath().getString("name"), "Duracell - AAA Batteries (4-Pack)", "Incorrect product name");
        assertEquals(response.jsonPath().getDouble("price"), 5.49, "Incorrect product price");
        assertEquals(response.jsonPath().getString("description"), "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack", "Incorrect product description");


        Log.endTestCase("Verify the successful retrieval of product information");

    }

    @Test( enabled = true, priority = 1)
    public void testProductCreation() {

        Log.startTestCase("Verify the creation of a new product");
        // Set base URI for the API
        Log.info("Set base URI for the API : "+RestAssured.baseURI);
        RestAssured.baseURI = baseURI;

        ProductDM productDM = new ProductDM("NewTestProduct", "Product sample description"
                , 5.49, "HardGood", "041333424019", 0, "Duracell", "MN2400B4Z"
                , "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC"
                , "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg");

        // Send a POST request to add a new product
        Response response = given()
                .contentType(ContentType.JSON)
                .body(productDM)
                .when()
                .post("/products");

        Log.info("Response: " + response.getStatusLine());
        Log.info("Response: " + response.getBody().asString());
        product_id = response.jsonPath().getString("id");
        // Assertions
        assertEquals(response.getStatusCode(), 201, "Unexpected status code");
        assertNotEquals(response.jsonPath().getString("createdAt"), "");


        Log.endTestCase("Verify the successful retrieval of product information");

    }

    @Test(dependsOnMethods = {"testProductCreation"}, enabled = true, priority = 2)
    public void testProductUpdate() {

        Log.startTestCase("Verify the updating of product information");
        // Set base URI for the API
        Log.info("Set base URI for the API : "+RestAssured.baseURI);
        RestAssured.baseURI = baseURI;

        ProductDM productDM = new ProductDM("updateTestProduct", "Update product sample description"
                , 5.49, "HardGood", "041333424019", 0, "Duracell", "MN2400B4Z"
                , "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC"
                , "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg");


        // Send a PUT request to update product
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", product_id)
                .body(productDM)
                .when()
                .put("/products/{id}");

        Log.info("Response: " + response.getStatusLine());
        Log.info("Response: " + response.getBody().asString());
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        Log.endTestCase("Verify the updating of product information");

    }

    @Test(dependsOnMethods = {"testProductUpdate"}, enabled = true, priority = 3)
    public void testDeleteProduct() {

        Log.startTestCase("Verify the deletion of a product");
        // Set base URI for the API
        Log.info("Set base URI for the API : "+RestAssured.baseURI);
        RestAssured.baseURI = baseURI;

        // Send a PUT request to update product
        Response response = given()
                .pathParam("id", product_id)
                .when()
                .delete("/products/{id}");

        Log.info("Response: " + response.getStatusLine());
        Log.info("Response: " + response.getBody().asString());
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");

        Log.endTestCase("Verify the deletion of a product");

    }



}
