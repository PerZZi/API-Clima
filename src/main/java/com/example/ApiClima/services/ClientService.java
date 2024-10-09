package com.example.ApiClima.services;

import com.example.ApiClima.dtos.ClientDTO;
import com.example.ApiClima.models.Client;
import org.springframework.http.ResponseEntity;

public interface ClientService {

    boolean existByEmail(String email);
    void saveClient(Client client);
    ResponseEntity<String> registerClient(ClientDTO clientDTO);
}
