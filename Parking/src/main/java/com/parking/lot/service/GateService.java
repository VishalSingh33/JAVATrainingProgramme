package com.parking.lot.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parking.lot.entity.Gate;
import com.parking.lot.repository.GateRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GateService {

    private final GateRepository gateRepository;

    public Optional<Gate> getGate(String gateId) {

        return gateRepository.findById(gateId);
    }
}
