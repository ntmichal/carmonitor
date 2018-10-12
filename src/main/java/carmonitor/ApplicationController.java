package carmonitor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import carmonitor.entities.Car;
import carmonitor.entities.Fueling;
import carmonitor.repositories.CarRepository;
import carmonitor.repositories.FuelRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ApplicationController {
	

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private FuelRepository fuelRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/car", method = RequestMethod.GET)
	public List<Car> carList(){
		List<Car> carList = (List<Car>)carRepository.findAll();
		
		carList.forEach(car -> {
				car.setStatistic(Statistics.carInfo(car.getFuelingList()));
		
		});
		return carList;
	}
	
	@RequestMapping(value="/api/car/{id}", method = RequestMethod.GET)
	public Optional<Car> car(@PathVariable Long id) {
		return carRepository.findById(id) != null ? carRepository.findById(id)  : null;
	}
	
	
	@RequestMapping(value = "/api/car", method = RequestMethod.POST)
	public ResponseEntity<Long> insertCar(@RequestBody Car car){
		Car carTemp = carRepository.save(car);
		
		
		return ResponseEntity
				.created(URI.create("http://localhost:8080/api/car/"+carTemp.getId()))
				.header("id", carTemp.getId().toString()).body(carTemp.getId());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/car/{id}", method = RequestMethod.DELETE)
	public void deleteCar(@PathVariable Long id) {
		Optional<Car> car = carRepository.findById(id);
		
		String HQL = "From Fueling WHERE car_id = " + id;
		
		@SuppressWarnings("unchecked")
		List<Fueling> listFuel = entityManager.createQuery(HQL).getResultList();
		listFuel.forEach((element) ->{
			fuelRepository.delete(element);
		});
		
		carRepository.deleteById(id);
	}
	@RequestMapping(value = "/api/car/{id}", method = RequestMethod.PUT)
	public void updateCar(@PathVariable Long id, @RequestBody Car car) {
		
		
		Optional<Car> findCar = carRepository.findById(id);
		
		if(findCar.isPresent()) {
			car.setId(findCar.get().getId());
			carRepository.save(car);
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/api/fuel/{carid}", method = RequestMethod.POST)
	public ResponseEntity<Object> saveFuel(@PathVariable Long carid, @RequestBody Fueling fuel) {
		Optional<Car> car = carRepository.findById(carid);
		
		Fueling fuels = null;
	
		if(car.isPresent()) {
			fuel.setCar(car.get());
			fuels = fuelRepository.save(fuel);

		}else {
			return ResponseEntity
					.created(URI.create("http://localhost:8080/api/car/"+carid))
					.header("fueling","object not found").body(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity
				.created(URI.create("http://localhost:8080/api/car/"+ fuels.getCar().getId()))
				.header("fueling","success").body(fuels.getId());

		
		
	}
	
	@RequestMapping(value = "/api/fuel/{id}", method = RequestMethod.DELETE)
	public void deleteFuel(@PathVariable Long id) {
		
		fuelRepository.deleteById(id);

				
	}

	
}
