package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class AppTest {
        @BeforeMethod
        public void setUP() {
            RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        }

        @Test
        public void getTest(){
            given().log().all()
                    .when().get("/posts")

                    .then().log().all().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
        }
        @Test
        public void getTestUserId1(){
            given().log().all()
                .when().get("/posts/1")
                .then().log().all().statusCode(200);
            }
        @Test
        public void createUser(){
            File file = new File("src/test/resources/createUser.json");
            given().log().all().contentType(ContentType.JSON).body(file)
                    .when().post("/posts")
                    .then().log().body().statusCode(201);
        }
        @Test
        public void updateUser() {
            File file = new File("src/test/resources/updateUser.json");
            given().log().all().contentType(ContentType.JSON).body(file)
                .when().put("/posts/1")
                .then().log().body().statusCode(200);
        }
        @Test
        public void deleteUser() {
            given().log().uri()
                .when().delete("/posts/1")
                .then().statusCode(200);
        }
    }


