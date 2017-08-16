package com.hanbit.there.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanbit.there.api.domain.Airline;
import com.hanbit.there.api.repo.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	public Airline addAirline(int id) {
		String key = "airline_" + id;

		Airline airline = airlineRepository.findOne(key);

		if (airline == null) {
			airline = new Airline();
			airline.setKey(key);
			airline.setId(id);
		}

		airline.setType("airline");
		airline.setName("Asiana");
		airline.setCountry("Korea");

		return airlineRepository.save(airline);
	}

	public Airline getAirline(String id) {
		return airlineRepository.findOne(id);
	}

	public List<Airline> getAirlines() {
		List<Airline> all = new ArrayList<>();

		airlineRepository.findAll().forEach(all::add);

		return all;
	}

}