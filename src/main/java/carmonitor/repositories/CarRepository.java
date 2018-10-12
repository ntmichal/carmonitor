package carmonitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import carmonitor.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	

}
