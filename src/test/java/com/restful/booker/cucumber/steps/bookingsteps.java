package com.restful.booker.cucumber.steps;


import com.restful.booker.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class bookingsteps {
    static ValidatableResponse response;
    String firstname = "vir"+ TestUtils.getRandomValue();
    String lastname = "Dev" + TestUtils.getRandomValue();
    int totalprice = 500;
    boolean depositpaid = true;

    String checkin = "2018-01-01";
    String checkout = "2019-01-01";
    String additionalneed = "super need";
    static int newbookingid;

    @Steps
    com.restful.booker.userinfo.BookingSteps bookingSteps;
    @When("^User send a GET request to booking endpoints$")
    public void userSendAGETRequestToBookingEndpoints() {
        response=bookingSteps.getAllBooking();

    }


    @Then("^User must get list of booking and valid status code (\\d+)$")
    public void userMustGetListOfBookingAndValidStatusCode(int arg0) {
        response.statusCode(200);
    }

    @When("^I create a new user by providing all the information$")
    public void iCreateANewUserByProvidingAllTheInformation() {
        response=bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneed);
        response.log().all().statusCode(200);
        newbookingid = response.extract().path("bookingid");
        System.out.println("NEWLY CREATED STORE ID IS:" +newbookingid);
    }

    @Then("^I verfiy that the new user is created$")
    public void iVerfiyThatTheNewUserIsCreated() {
        ValidatableResponse response=bookingSteps.VerifyBooking(newbookingid);
        response.log().all().statusCode(200);
    }

    @When("^I update a newly created user$")
    public void iUpdateANewlyCreatedUser() {
        firstname = "vir"+ TestUtils.getRandomValue();
        lastname = "Dev" + TestUtils.getRandomValue();
        ValidatableResponse response=bookingSteps.updateBooking(newbookingid,firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneed);
        response.log().all().statusCode(200);
    }

    @Then("^I verify new user information is updated$")
    public void iVerifyNewUserInformationIsUpdated() {
        ValidatableResponse response=bookingSteps.VerifyBooking(newbookingid);
        response.log().all().statusCode(200);
    }

    @When("^I delete a newly created user$")
    public void iDeleteANewlyCreatedUser() {
        ValidatableResponse response=bookingSteps.deleteBooking(newbookingid);
        response.log().all().statusCode(201);
    }

    @Then("^I verify user is deleted$")
    public void iVerifyUserIsDeleted() {
        bookingSteps.VerifyBooking(newbookingid).statusCode(404);
    }
}