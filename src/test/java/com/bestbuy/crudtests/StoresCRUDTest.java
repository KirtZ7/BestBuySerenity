package com.bestbuy.crudtests;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
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
public class StoresCRUDTest extends TestBase {

    static String name = "Kirtan" + TestUtils.getRandomValue();
    static String type = "typesTest";
    static String address = "Test address";
    static String address2 = "Test address 2";
    static String city = "City Test";
    static String state = "State test";
    static String zip = "Tes t12";
    static Integer lat = 25415265;
    static Integer lng = 95845854;
    static String hours = "40 hours";
    static Object storesId;

    @Title("Create new store")
    @Test
    public void test001(){

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        SerenityRest.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(storePojo)
                .when()
                .post(EndPoints.GET_ALL_STORES)
                .then().log().all().statusCode(201);

    }

    @Title("Verify if store was created")
    @Test
    public void test002(){
        String part1 ="data.findAll{it.name='";
        String part2 ="'}.get(0)";

        HashMap<String,?> storeData = SerenityRest.given()
                .log().all()
                //.header("Content-Type", "application/json")
                //.pathParams("productsID",productID)
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200).extract().path(part1+ name +part2);
        Assert.assertThat(storeData,hasValue(name));
        storesId = storeData.get("id");
        System.out.println(storeData);
        System.out.println(storesId);
    }

    @Title("Update the stores and verify the updated information")
    @Test
    public void test003() {

        name = name + "update";
        type = type + "update";

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);


        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesID", storesId)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORES_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        HashMap<String, ?> storesData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(storesData,hasValue(name));

    }

    @Title("Delete stores Data")
    @Test
    public void test004() {
        SerenityRest.given()
                .pathParams("storesID",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then().log().all().statusCode(200);

        SerenityRest.given()
                .pathParams("storesID",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then().log().all().statusCode(404);

    }

}
