package com.example.hotel_management.Controller;

import com.example.hotel_management.DTO.ClientDTO;
import com.example.hotel_management.Entity.Client;
import com.example.hotel_management.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin("http://localhost:3000")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = ClientDTO.convertToDTOList(clients);
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        Client savedclient = clientService.saveClient(client);
        return new ResponseEntity<>(savedclient, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
