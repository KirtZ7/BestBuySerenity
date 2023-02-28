package com.bestbuy.crudtests;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
    static String name = "NameTest " + TestUtils.getRandomValue();
    static String type = "TypeTest " + TestUtils.getRandomValue();
    static Integer price = 69;
    static Integer shipping = 5;
    static String upc = "upcTest " + TestUtils.getRandomValue();
    static String description= "DescriptionTest " +  TestUtils.getRandomValue() ;
    static String manufacturer = "manufacturertest " + TestUtils.getRandomValue();
    static String model = "modeltest " + TestUtils.getRandomValue();
    static String url = "https://www.bestbuy.com/site/trust-no-one-lp-vinyl/35474875.p?skuId=35474875" ;
    static String image = "https://bigthink.com/wp-content/uploads/2022/08/AdobeStock_81049143.jpeg";

    static Object productsId;


    @Title("Create new product")
    @Test
    public void test001(){
        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);


        SerenityRest.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(EndPoints.GET_ALL_PRODUCTS)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if product was created")
    @Test
    public void test002(){
        String part1 ="data.findAll{it.name='";
        String part2 ="'}.get(0)";

        HashMap<String,?> productData = SerenityRest.given()
                .log().all()
                //.header("Content-Type", "application/json")
                //.pathParams("productsID",productID)
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().statusCode(200).extract().path(part1+ name +part2);
        Assert.assertThat(productData,hasValue(name));
        productsId = productData.get("id");
        System.out.println(productData);
        System.out.println(productsId);
    }

    @Title("Update the product and verify the updated information")
    @Test
    public void test003() {

        name = name + "update";

        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);


        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("productsID", productsId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        HashMap<String, ?> productData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(productData,hasValue(name));

    }

    @Title("Delete product Data")
    @Test
    public void test004() {
        SerenityRest.given()
                .pathParams("productsID",productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);

        SerenityRest.given()
                .pathParams("productsID",productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all().statusCode(404);

    }
}
