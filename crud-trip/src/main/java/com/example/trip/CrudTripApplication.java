package com.example.trip;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudTripApplication {
	private static final Logger log = LoggerFactory.getLogger(CrudTripApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudTripApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(TripRepository repository) {
		return (args) -> {
			// save a couple of trips
			repository.save(new Trip("124", "DONE", 1L, 1L, getPastDateForDays(9), new Date(), "OUTSTATION"));
			repository.save(new Trip("234", "CANCELLED", 1L, 2L, getPastDateForDays(7), new Date(), "OUTSTATION"));
			repository.save(new Trip("444", "INPROGRESS", 3L, 1L, getPastDateForDays(1), null, "INSTATION"));
			repository.save(new Trip("555", "DONE", 4L, 2L, new Date(), new Date(), "INSTATION"));
			repository.save(new Trip("666", "INPROGRESS", 5L, 5L, getPastDateForDays(2), null, "INSTATION"));
			repository.save(new Trip("100", "INPROGRESS", 4L, 4L, getPastDateForDays(2), new Date(), "INSTATION"));
			repository.save(new Trip("50", "INPROGRESS", 4L, 4L, getFutureDateForDays(2), new Date(), "OUTSTATION"));
			repository.save(new Trip("250", "INPROGRESS", 4L, 4L, getFutureDateForDays(1), new Date(), "OUTSTATION"));
			repository.save(new Trip("600", "INPROGRESS", 4L, 4L, getFutureDateForDays(7), new Date(), "OUTSTATION"));
			repository.save(new Trip("700", "INPROGRESS", 4L, 4L, getFutureDateForDays(6), new Date(), "INSTATION"));

			// fetch all trips
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Trip trip : repository.findAll()) {
				log.info(trip.toString());
			}
			log.info("");
		};
	}

	private Date getPastDateForDays(int beforeDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -beforeDays);
		return cal.getTime();
	}

	private Date getFutureDateForDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +days);
		return cal.getTime();
	}
}
