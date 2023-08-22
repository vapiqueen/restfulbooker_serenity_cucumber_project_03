package com.restful.booker.cucumber.steps;

import com.restful.booker.userinfo.BookingSteps;
import com.restful.booker.userinfo.GetToken;
import com.restful.booker.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class bookingCRUDSteps {
    static ValidatableResponse response;
    static String token;
    static int newbookingid;
    @Steps
    BookingSteps bookingSteps;
    static GetToken getToken = new GetToken();


    @Given("^I am in restfull booker application$")
    public void iAmInRestfullBookerApplication() {
    }

    @When("^I create a new post request by entering username and password for token$")
    public void iCreateANewPostRequestByEnteringUsernameAndPasswordForToken() {


    }


    @Then("^I get a list of booking ID before creating a new booking ID$")
    public void iGetAListOfBookingIDBeforeCreatingANewBookingID() {
        response=bookingSteps.getAllBooking();
    }


    @And("^I create new booking with firstname\"([^\"]*)\" lastname \"([^\"]*)\" totalprice\"([^\"]*)\"depositepaid\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\" \"([^\"]*)\"$")
    public void iCreateNewBookingWithFirstnameLastnameTotalpriceDepositepaid(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin,String checkout,String additionalneed)  {
        response=bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneed);
        response.log().all().statusCode(200);
        newbookingid = response.extract().path("bookingid");
        System.out.println("NEWLY CREATED STORE ID IS:" +newbookingid);
    }

    @And("^I verfiy that the new booking has been created$")
    public void iVerfiyThatTheNewBookingHasBeenCreated() {
        ValidatableResponse response=bookingSteps.VerifyBooking(newbookingid);
        response.log().all().statusCode(200);
    }
    @And("^I update a newly created booking with firstname\"([^\"]*)\" lastname \"([^\"]*)\" totalprice\"([^\"]*)\"depositepaid\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\" \"([^\"]*)\"$")
    public void iUpdateANewlyCreatedBookingWithFirstnameLastnameTotalpriceDepositepaid(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneed) {
        firstname = "vir"+ TestUtils.getRandomValue();
        lastname = "Dev" + TestUtils.getRandomValue();
        ValidatableResponse response=bookingSteps.updateBooking(newbookingid, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneed);
        response.log().all().statusCode(200);
    }

    @And("^I verify booking has been updated$")
    public void iVerifyBookingHasBeenUpdated() {
        ValidatableResponse response=bookingSteps.VerifyBooking(newbookingid);
        response.log().all().statusCode(200);
    }

    @And("^I delete a newly created booking$")
    public void iDeleteANewlyCreatedBooking() {
        ValidatableResponse response=bookingSteps.deleteBooking(newbookingid);
        response.log().all().statusCode(201);
    }

    @Then("^i verify newly created booking has been deleted$")
    public void iVerifyNewlyCreatedBookingHasBeenDeleted() {
        bookingSteps.VerifyBooking(newbookingid).statusCode(404);
    }

}
