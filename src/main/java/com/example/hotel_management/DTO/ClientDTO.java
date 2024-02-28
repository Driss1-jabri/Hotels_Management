package com.example.hotel_management.DTO;

import com.example.hotel_management.Entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.nom = client.getNom();
        this.prenom = client.getPrenom();
        this.email = client.getEmail();
        this.telephone = client.getTelephone();
    }

    public static List<ClientDTO> convertToDTOList(List<Client> clients) {
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }
}
