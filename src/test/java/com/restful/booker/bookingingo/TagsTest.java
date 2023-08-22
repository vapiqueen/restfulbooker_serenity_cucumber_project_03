package com.restful.booker.bookingingo;

import com.restful.booker.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;

public class TagsTest extends TestBase {
    @WithTag("bookingfeature:NEGATIVE")
    @Title("Provide a 405 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post("/booking")
                .then()
                .statusCode(405)
                .log().all();
    }

    @WithTags({
            @WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:POSITIVE")
    })
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().all();
    }
    @WithTags({
            @WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:NEGATIVE")
    })
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/booking1")
                .then()
                .statusCode(400)
                .log().all();
    }
}
