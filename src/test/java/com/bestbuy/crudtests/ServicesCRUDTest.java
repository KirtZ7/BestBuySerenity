package com.bestbuy.crudtests;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ServicesCRUDTest extends TestBase {

    static String name = "Kirtan" + TestUtils.getRandomValue();

    static Object servicesId;

    @Title("This will create a new SERVICE")
    @Test
    public void test001(){

        ServicesPojo servicesPojo = new ServicesPojo();

        servicesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post(EndPoints.GET_ALL_SERVICES)
                .then().log().all().statusCode(201);

    }

    @Title("verify if service was created")
    @Test
    public void test002(){
        String part1 ="data.findAll{it.name='";
        String part2 ="'}.get(0)";

        HashMap<String,?> serviceData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().statusCode(200).extract().path(part1+name+part2);
        Assert.assertThat(serviceData,hasValue(name));
        servicesId = serviceData.get("id");
        System.out.println(servicesId);

    }

    @Title("Update the services and verify the updated information")
    @Test
    public void test003() {

        name = name + "update";

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("servicesID", servicesId)
                .body(servicesPojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICES_BY_ID)
                .then().log().all().statusCode(200);

        String part1 ="data.findAll{it.name='";
        String part2 ="'}.get(0)";

        HashMap<String,?> serviceData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().statusCode(200).extract().path(part1+name+part2);
        Assert.assertThat(serviceData,hasValue(name));

    }

    @Title("Delete services Data")
    @Test
    public void test004() {
        SerenityRest.given()
                .pathParams("servicesID",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then().log().all().statusCode(200);

        SerenityRest.given()
                .pathParams("servicesID",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then().log().all().statusCode(404);

    }

}
