package skate.skate.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import skate.skate.ContadorClient;
import skate.skate.model.Client;
import skate.skate.model.Reservation;
import skate.skate.repository.crud.interfaceReservation;

@Repository
public class RepositorioReservation {
    @Autowired
    private interfaceReservation crud;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) crud.findAll();
    }
    
    public Optional<Reservation> getReservation(int id){
        return crud.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return crud.save(reservation);
    }

    public void delete(Reservation reservation) {
        crud.delete(reservation);
    }
    
     public List<Reservation>ReservationStatus(String status){
        return crud.findAllByStatus(status);


    }

    public List<Reservation> ReservationTiempoRepositorio(Date a, Date b){
        return crud.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<ContadorClient> getClientesRepositorio(){
        List<ContadorClient> res =new ArrayList<>();
        List<Object[]> report = crud.countTotalReservationsByClient();
        for(int i=0; i<report.size(); i++){

            res.add(new ContadorClient((long)report.get(i)[1],(Client) report.get(i)[0]));
        }

        return res;
        
    }
    
}
