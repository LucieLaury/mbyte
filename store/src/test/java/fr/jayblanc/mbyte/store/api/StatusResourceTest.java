package fr.jayblanc.mbyte.store.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class StatusResourceTest {
    @Test
    void testStatusEndpoint() {
        given()
          .when().get("/api/status")
          .then()
             .statusCode(200)
             .body(containsString("Uploads"));
    }

}
