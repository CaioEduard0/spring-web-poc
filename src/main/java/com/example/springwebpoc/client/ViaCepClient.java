package com.example.springwebpoc.client;

import com.example.springwebpoc.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ViaCepClient {

    private final String url;

    public ViaCepClient(@Value("${viacep.url}") final String url) {
        this.url = url;
    }

    public Address fetchAddress(final String cep) {

        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Address> addressResponse = restTemplate.getForEntity(String.format(url, cep), Address.class);

        if (!addressResponse.getStatusCode().equals(HttpStatus.OK)) log.error("Erro ao fazer a requisição para a api viacep.");
        return addressResponse.getBody();
    }
}