package com.example.springwebpoc.service;

import com.example.springwebpoc.client.ViaCepClient;
import com.example.springwebpoc.entity.Address;
import com.example.springwebpoc.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final ViaCepClient viaCepClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AddressRepository addressRepository;

    public Address getAddress(final String cep) {

        final Address address = viaCepClient.fetchAddress(cep);
        kafkaTemplate.send("address", address.toString());
        return addressRepository.save(address);
    }

    @KafkaListener(topics = "address", groupId = "group-1")
    public void receive(final String message) {
        log.info("Consumer received message: {}", message);
    }
}