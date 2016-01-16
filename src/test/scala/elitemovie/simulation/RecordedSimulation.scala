package elitemovie.simulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  val baseUrl = "http://localhost:8080";

  val httpConf = http
    .baseURL(baseUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

  val scn = scenario("EliteMovieHomePageSimulation")
    .exec(http("elitemoviehomepage")
      .get("/"))
    .pause(5)

  setUp(scn.inject(rampUsers(20) over (10 seconds)).protocols(httpConf))
}