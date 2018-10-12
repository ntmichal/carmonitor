package carmonitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import carmonitor.entities.Fueling;

public interface FuelRepository extends JpaRepository<Fueling, Long>{

}
