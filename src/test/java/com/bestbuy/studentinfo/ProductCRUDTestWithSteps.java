package com.bestbuy.studentinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends TestBase {
    static int id;
    static String name = "Energizer - MAX Batteries AA (4-Pack)";
    static String type = "HardGood";
    static double price = 4.99;
    static String upc = "039800011329";
    static int shipping = 0;
    static String description = "4-pack AA alkaline batteries; battery tester included";
    static String manufacturer = "Energizer";
    static String model = "E91BP-4";
    static String url = "http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/1501/150115_sa.jpg";
    static String createdAt = "2016-11-17T17:58:03.298Z";


    @Steps
    ProductSteps productSteps;

    @Test
    public void test001() {
        productSteps.getAllRecords();
    }


    @Test
    public void test002() {
        ValidatableResponse response = productSteps.createAProduct(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");
    }

    @Test
    public void test003() {
        ValidatableResponse response = productSteps.getSingleRecord(id).statusCode(200);
        response.log().all().body("id", equalTo(id));
    }

    @Test
    public void test004() {
        name = "Energizer - MAX Batteries AA (4-Pack)";
        ValidatableResponse response = productSteps.updateARecord(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
    }

    @Test
    public void test005() {
        productSteps.deleteAProduct(id);
        productSteps.getSingleRecord(id).statusCode(404);
    }


}
