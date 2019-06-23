package com.senacor.vienna.qk.rating

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class RatingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/C:/Progs/git-2.17.0-64/rating")
          .then()
             .statusCode(200)
             .body(`is`("hello"))
    }

}