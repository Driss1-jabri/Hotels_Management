package com.example.hotel_management.Service;

import com.example.hotel_management.Entity.Client;
import com.example.hotel_management.Repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public List<Client> getAllClients(){
        return clientRepo.findAll();
    }
    public Optional<Client> getClientById(Long id){

        return clientRepo.findById(id);
    }
    public Client saveClient(Client client){
        return clientRepo.save(client);
    }
    public void deleteClient(Client client){
        clientRepo.delete(client);
    }
}
