package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                    .when().get("https://jsonplaceholder.typicode.com/posts")
                    .then().log().all().statusCode(200);
        }
        @Test
        public void getTestUserId1(){
            given().log().all()
                .when().get("https://jsonplaceholder.typicode.com/posts/1")
                .then().log().all().statusCode(200);
        }
        @Test
        public void createUser(){
            File file = new File("src/test/java/JSON/createUser.json");
            given().log().all().contentType(ContentType.JSON).body(file)
                    .when().post("/posts")
                    .then().log().body().statusCode(201);
        }
        @Test
        public void updateUser() {
            File file = new File("src/test/java/JSON/updateUser.json");
            given().log().all().contentType(ContentType.JSON).body(file)
                .when().put("/posts/1")
                .then().log().body().statusCode(200);
        }
        @Test
        public void deleteUser() {
            given().log().uri()
                .when().delete("https://jsonplaceholder.typicode.com/posts/1")
                .then().statusCode(200);
        }
    }


