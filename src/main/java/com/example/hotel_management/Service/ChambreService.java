package com.example.hotel_management.Service;

import com.example.hotel_management.Entity.Chambre;
import com.example.hotel_management.Entity.Client;
import com.example.hotel_management.Repository.ChambreRepo;
import com.example.hotel_management.Repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreService {
    @Autowired
    private ChambreRepo chambreRepo;

    public List<Chambre> getAllChambres(){
        return chambreRepo.findAll();
    }
    public Optional<Chambre> getChambreById(Long id){
        return chambreRepo.findById(id);
    }
    public Chambre saveChambre(Chambre chambre){
        return chambreRepo.save(chambre);
    }
    public void deleteChambre(Chambre chambre){
        chambreRepo.delete(chambre);
    }
    public void deleteChambreById(Long id) {
        chambreRepo.deleteById(id);
    }
}
