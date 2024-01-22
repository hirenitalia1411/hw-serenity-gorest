package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.constants.Path;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static com.gorest.utils.TestUtils.getRandomValue;

public class UserSteps {

    @Step("Creating new user")
    public ValidatableResponse createUser() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("ABC XYZ");
        userPojo.setEmail("abc" + getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");

        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .body(userPojo)
                .when()
                .post(Path.USER)
                .then().log().all();
    }

    @Step("Getting user details")
    public ValidatableResponse getUserById(int userID) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .get(Path.USER + EndPoints.GET_USER_BY_ID)
                .then().log().all();
    }

    @Step("Updating user details")
    public ValidatableResponse updateUserById(int userID) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("ABC XYZ MNOP");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .body(userPojo)
                .put(Path.USER + EndPoints.UPDATE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting user details")
    public ValidatableResponse deleteUserById(int userID) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .delete(Path.USER + EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }
}
