package gatling.simulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources()

	val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_2 = Map("Accept" -> "*/*")

	val headers_6 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")

    val uri1 = "http://localhost:8080"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + "/css/styles.css")
			.headers(headers_1)
			.check(status.is(304)),
            http("request_2")
			.get(uri1 + "/bower_components/angular/angular.js")
			.headers(headers_2)
			.check(status.is(304)),
            http("request_3")
			.get(uri1 + "/bower_components/angular-route/angular-route.js")
			.headers(headers_2)
			.check(status.is(304)),
            http("request_4")
			.get(uri1 + "/js/controllers.js")
			.headers(headers_2)
			.check(status.is(304)),
            http("request_5")
			.get(uri1 + "/js/app.js")
			.headers(headers_2)
			.check(status.is(304)),
            http("request_6")
			.get(uri1 + "/img/logo.png")
			.headers(headers_6)
			.check(status.is(304)),
            http("request_7")
			.get(uri1 + "/img/bg-header.png")
			.headers(headers_6)
			.check(status.is(304)),
            http("request_8")
			.get(uri1 + "/img/rotabanner.png")
			.headers(headers_6)
			.check(status.is(304)),
            http("request_9")
			.get(uri1 + "/partials/seat-selection.html")
			.check(status.is(304)),
            http("request_10")
			.get(uri1 + "/img/chairs-movie.png")
			.headers(headers_6)
			.check(status.is(304)),
            http("request_11")
			.get(uri1 + "/rest/showtime/3"),
            http("request_12")
			.get(uri1 + "/rest/seatrecommendation/3/3"))
			.check(status.is(304)))
		.pause(5)
		.exec(http("request_13")
			.get("/rest/movie/"))
		.pause(1)
		.exec(http("request_14")
			.get("/rest/movie/1")
			.resources(http("request_15")
			.get(uri1 + "/img/movies/.jpg")
			.headers(headers_6)
			.check(status.is(404))))
		.pause(2)
		.exec(http("request_16")
			.get("/rest/showtime/available/4")
			.resources(http("request_17")
			.get(uri1 + "/rest/showtime/4"),
            http("request_18")
			.get(uri1 + "/rest/seatrecommendation/4/3")))
		.pause(4)
		.exec(http("request_19")
			.post("/rest/transaction/4")
			.body(RawFileBody("RecordedSimulation_0019_request.txt"))
			.resources(http("request_20")
			.get(uri1 + "/rest/transaction/2"),
            http("request_21")
			.get(uri1 + "/img/movies/.jpg")
			.headers(headers_6)
			.check(status.is(404)),
            http("request_22")
			.get(uri1 + "/rest/movie/1"),
            http("request_23")
			.get(uri1 + "/rest/movie/")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}