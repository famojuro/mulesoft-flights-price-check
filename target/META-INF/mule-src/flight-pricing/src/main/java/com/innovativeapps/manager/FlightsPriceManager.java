package com.innovativeapps.manager;

import java.util.Date;
import java.util.List;

import com.amadeus.Amadeus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.ItineraryPriceMetric;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FlightsPriceManager {
	
	public static String FlightPriceAnalysis() throws Exception {
		
		Date localDate = new Date();
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Amadeus amadeus = Amadeus.builder("z4iJyd4SGeE27VfcoK82ylTuuILO3bG8", "GVnaY2Vwz54Q9CRd")
				.build();
		
		ItineraryPriceMetric[] metrics = amadeus.analytics.itineraryPriceMetrics.get(Params
				.with("originIataCode","MAD")
				.and("destinationIataCode", "CDG")
				.and("departureDate", formattedDate.format(localDate)));
		
//		if (metrics[0].getResponse().getStatusCode() != 200) {
//			throw new Exception("Wrong status code: " + metrics[0].getResponse().getStatusCode());
//		}
	
		return metrics[0].getResponse().getBody();
		
	}
	
	public static JsonObject getFlightsOffer() throws Exception {
		
		Date now = new Date();
		LocalDate today = LocalDate.parse((CharSequence) now);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		Amadeus amadeus = Amadeus.builder("z4iJyd4SGeE27VfcoK82ylTuuILO3bG8", "GVnaY2Vwz54Q9CRd")
				.build();
		
		FlightOfferSearch[] flightOfferSearches = amadeus.shopping.flightOffersSearch.get(
				Params.with("originLocationCode", "SYD")
				 		.and("destinationLocationCode", "BKK")
				 		.and("departureDate", formatter.format(today))
				 		.and("adults", 1));		
		
		JsonObject result = flightOfferSearches[0].getResponse().getResult();
		
		return result;	
	}
	
	/*
	public static HttpResponse<JsonNode> getListOfPlaces() throws UnirestException {
		
		HttpResponse<JsonNode> response = Unirest.post("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/pricing/v1.0")
				.header("X-RapidAPI-Host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.header("X-RapidAPI-Key", "31044ae08emsh15ed158d705983fp1c5fadjsn26b827be3e6d")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.field("inboundDate", "2019-09-10")
				.field("cabinClass", "business")
				.field("children", 0)
				.field("infants", 0)
				.field("country", "US")
				.field("currency", "USD")
				.field("locale", "en-US")
				.field("originPlace", "SFO-sky")
				.field("destinationPlace", "LHR-sky")
				.field("outboundDate", "2019-09-01")
				.field("adults", 1)
				.asJson();
		
		return response;
			
	}*/
	
	/*	
	public static HttpResponse<String> getFlightPrice() throws UnirestException {
		HttpResponse<String> response = Unirest.get("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/referral/v1.0/Nigeria/NGR/en-NGR/ABV/LOS/2021-03-16/%7Binboundpartialdate%7D?shortapikey=ra66933236979928&apiKey=%7Bshortapikey%7D")
				.header("x-rapidapi-key", "31044ae08emsh15ed158d705983fp1c5fadjsn26b827be3e6d")
				.header("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
				.asString();
		
		return response;
	}
	*/
}
