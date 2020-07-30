																/*
 -------------------------------------------------------------------
|
| CRUDyLeaf	- A Domain Specific Language for generating Spring Boot 
|			REST resources from entity CRUD operations.
| Author: Omar S. G�mez (2020)
| File Date: Sat Jul 25 17:23:00 COT 2020
| 
 -------------------------------------------------------------------
																*/
package net.t6.olmedorest.services;

import net.t6.olmedorest.RecordNotFoundException;
import net.t6.olmedorest.entities.Pago;
import net.t6.olmedorest.repositories.PagoRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

	@Autowired
	PagoRepository repo;

	public List<Pago> getAll(){
		List<Pago> pagoList = repo.findAll();
		if(pagoList.size() > 0) {
			return pagoList;
		} else {
			return new ArrayList<Pago>();
		}
	}
     		
	public Pago findById(Long id) throws RecordNotFoundException{
		Optional<Pago> pago = repo.findById(id);
		if(pago.isPresent()) {
			return pago.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public List<Pago> findByEstadoContaining(String estado){
		List<Pago> pagoList = repo.findByEstadoContaining(estado);
		if(pagoList.size() > 0) {
			return pagoList;
		} else {
			return new ArrayList<Pago>();
		}
	}
	
	public Pago createPago(Pago pago){
		return repo.save(pago);
	}

	public Pago updatePago(Pago pago) throws RecordNotFoundException {
		Optional<Pago> pagoTemp = repo.findById(pago.getId());
	
		if(pagoTemp.isPresent()){
			return repo.save(pago);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deletePagoById(Long id) throws RecordNotFoundException{
		Optional<Pago> pago = repo.findById(id);
		if(pago.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
