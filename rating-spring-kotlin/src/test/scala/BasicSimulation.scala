package computerdatabase // 1

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation { // 3

  val httpProtocol = http // 4
    .baseUrl("http://localhost:8080") // 5
    .acceptHeader("application/json") // 6
    .doNotTrackHeader("1")
    .acceptEncodingHeader("gzip, deflate")

  val scn = scenario("BasicSimulation") // 7
    .exec(http("request_1") // 8
      .get("/items/123/ratings")) // 9
    .pause(5) // 10

  setUp( // 11
    scn.inject(atOnceUsers(1)) // 12
  ).protocols(httpProtocol) // 13
}