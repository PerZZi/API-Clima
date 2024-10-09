package com.example.ApiClima.controller;

import com.example.ApiClima.dtos.ClientDTO;
import com.example.ApiClima.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody ClientDTO clientDTO){
        return clientService.registerClient(clientDTO);
    }
}
