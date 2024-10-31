package com.example.solvetesttask.service.impl;

import com.example.solvetesttask.exception.ServiceUnavailableException;
import com.example.solvetesttask.model.Holiday;
import com.example.solvetesttask.repository.HolidayRepository;
import com.example.solvetesttask.service.HolidayService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class HolidayServiceImpl implements HolidayService {
    private static final String COUNTRY_CODE = "KZ";

    private final RestTemplate restTemplate;
    private final HolidayRepository holidayRepository;

    public HolidayServiceImpl(RestTemplate restTemplate, HolidayRepository holidayRepository) {
        this.restTemplate = restTemplate;
        this.holidayRepository = holidayRepository;
    }

    @Override
    public boolean isHolidayToday() {
        boolean isHolidayToday = holidayRepository.existsByDateAndIsHolidayIsTrue(LocalDate.now());
        if (isHolidayToday) {
            return true;
        }
        ResponseEntity<Void> response = restTemplate.exchange(COUNTRY_CODE, HttpMethod.GET, null, Void.class);
        Holiday holiday = new Holiday();
        if (response.getStatusCode() == HttpStatus.OK) {
            holiday.setDate(LocalDate.now());
            holiday.setHoliday(true);
            holidayRepository.save(holiday);
            return true;
        } else if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            holiday.setDate(LocalDate.now());
            holiday.setHoliday(false);
            holidayRepository.save(holiday);
            return false;
        } else {
            throw new ServiceUnavailableException("Service unavailable");
        }
    }

}
