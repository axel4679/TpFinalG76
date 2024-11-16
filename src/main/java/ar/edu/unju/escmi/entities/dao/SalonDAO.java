package ar.edu.unju.escmi.entities.dao;


import java.util.List;

import ar.edu.unju.escmi.entities.Salon;


public interface SalonDAO {
	public void guardarSalon(Salon salon);
	public  List<Salon> consultarSalon();
	
}
