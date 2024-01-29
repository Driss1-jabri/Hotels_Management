package com.example.hotel_management.Controller;

import com.example.hotel_management.Entity.Client;
import com.example.hotel_management.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin("http://localhost:3000")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client)
    {
        Client savedclient = clientService.saveClient(client);
        return new ResponseEntity<>(savedclient, HttpStatus.CREATED);
    }
}
