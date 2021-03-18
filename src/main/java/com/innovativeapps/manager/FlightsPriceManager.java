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
	
	public static String getflightPriceAnalysis(String source, String destination) throws Exception {
		
		Date localDate = new Date();
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Amadeus amadeus = Amadeus.builder("z4iJyd4SGeE27VfcoK82ylTuuILO3bG8", "GVnaY2Vwz54Q9CRd")
				.build();
		
		ItineraryPriceMetric[] metrics = amadeus.analytics.itineraryPriceMetrics.get(Params
				.with("originIataCode", source)
				.and("destinationIataCode", destination)
				.and("departureDate", formattedDate.format(localDate)));
			
		return metrics[0].getResponse().getBody();
		
	}
	
	public static JsonObject getFlightsOffer(String source, String destination) throws Exception {
		
		Date localDate = new Date();
		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
		
		Amadeus amadeus = Amadeus.builder("z4iJyd4SGeE27VfcoK82ylTuuILO3bG8", "GVnaY2Vwz54Q9CRd")
				.build();
		
		FlightOfferSearch[] flightOfferSearches = amadeus.shopping.flightOffersSearch.get(
				Params.with("originLocationCode", source)
				 		.and("destinationLocationCode", destination)
				 		.and("departureDate", formattedDate.format(localDate))
				 		.and("adults", 1));		
		
		JsonObject result = flightOfferSearches[0].getResponse().getResult();
		
		return result;	
	}
		
}
