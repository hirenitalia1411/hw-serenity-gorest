package com.gorest.userinfo;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCrudTest extends TestBase {

    static int userID;

    @Steps
    UserSteps steps;

    @Title("This will create new user")
    @Test
    public void test001CreateUser() {
        ValidatableResponse response = steps.createUser();
        response.statusCode(201);

        userID = response.extract().path("id");
        System.out.println("Created User ID: " + userID);
    }

    @Title("This will get user")
    @Test
    public void test002ReadUser() {
        ValidatableResponse response = steps.getUserById(userID);
        response.statusCode(200);
    }

    @Title("This will update user")
    @Test
    public void test003UpdateUser() {
        ValidatableResponse response = steps.updateUserById(userID);
        response.statusCode(200);
    }

    @Title("This will delete user and verify")
    @Test
    public void test004DeleteUSer() {
        ValidatableResponse response = steps.deleteUserById(userID);
        response.statusCode(204);

        ValidatableResponse response1 = steps.getUserById(userID);
        response1.statusCode(404);
    }
}