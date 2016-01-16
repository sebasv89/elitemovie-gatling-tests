package elitemovie.simulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EliteMovieSimulation extends Simulation {

  val baseUrl = "http://localhost:8080/rest";

  val globalHttpConf = http
    .baseURL(baseUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

  object ListMovies {

    val scenario = exec(http("listmovies")
      .get("/movie/"))
      .pause(1);
  }

  object GetMovieDetails {

    val scenario = exec(http("getmoviedetails")
      .get("/movie/1"))
      .pause(1)
  }

  object GetCompleteCinemaSeats {

    val scenario = exec(http("getcompletecinemaseats")
      .get("/showtime/2"))
      .pause(1)
  }

  object RecommendSeats {

    val scenario = exec(http("recommendseats")
      .get("/seatrecommendation/2/2"))
      .pause(1)
  }

  val completeFlowWithRecommendations = scenario ("With Recommendations").exec(ListMovies.scenario, GetMovieDetails.scenario, GetCompleteCinemaSeats.scenario, RecommendSeats.scenario);
  
  val flowWithoutRecommendations = scenario ("Without Recommendations").exec(ListMovies.scenario, GetMovieDetails.scenario, GetCompleteCinemaSeats.scenario);
  
  
  
  setUp(completeFlowWithRecommendations.inject(rampUsers(300) over (10 seconds)).protocols(globalHttpConf),
      flowWithoutRecommendations.inject(rampUsers(300) over (10 seconds)).protocols(globalHttpConf))
      
      
      .assertions(global.responseTime.max.lessThan(1000))
  
}