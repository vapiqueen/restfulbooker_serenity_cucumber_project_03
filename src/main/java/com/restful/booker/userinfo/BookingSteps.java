package com.restful.booker.userinfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.model.Bookingdates;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BookingSteps {
    static GetToken getToken = new GetToken();
    static String token = getToken.getToken();

    @Step("Get all bookings information")
    public ValidatableResponse getAllBooking() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_BOOKING)
                .then();


    }
    @Step("Create Booking")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin,
                                             String chekout, String additionalneed) {


        BookingPojo bookingPojoFinal = new BookingPojo();
        Bookingdates bookingdates = new Bookingdates();

        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(chekout);

        bookingPojoFinal.setFirstname(firstname);
        bookingPojoFinal.setLastname(lastname);
        bookingPojoFinal.setTotalprice(totalprice);
        bookingPojoFinal.setDepositpaid(depositpaid);
        bookingPojoFinal.setBookingdates(bookingdates);
        bookingPojoFinal.setAdditionalneeds(additionalneed);

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .body(bookingPojoFinal)
                .post(EndPoints.CREATE_BOOKING)
                .then();


    }

    @Step("Verify Newly Create Booking")
    public ValidatableResponse VerifyBooking(int bookingid){
        System.out.println(bookingid);
        return  SerenityRest.given().log().all()
                //.header("Authorization","Bearer f4a966cfb8b1f8762386c84c483edfe3e4d73f9b92bd3e88426ca701e310525b")
                .pathParam("bookingID",bookingid)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();

    }

    @Step("Update Booking")
    public ValidatableResponse updateBooking(int bookingid, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin,
                                             String chekout, String additionalneed){



        BookingPojo bookingPojoFinal = new BookingPojo();
        Bookingdates bookingdates = new Bookingdates();

        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(chekout);

        bookingPojoFinal.setFirstname(firstname);
        bookingPojoFinal.setLastname(lastname);
        bookingPojoFinal.setTotalprice(totalprice);
        bookingPojoFinal.setDepositpaid(depositpaid);
        bookingPojoFinal.setBookingdates(bookingdates);
        bookingPojoFinal.setAdditionalneeds(additionalneed);

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID",bookingid)
                .when()
                .body(bookingPojoFinal)
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }
    @Step("Delete Booking")
    public ValidatableResponse deleteBooking(int bookingid){

        return  SerenityRest.given().log().all()
                .header("Content-Type","application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingID",bookingid)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
}
