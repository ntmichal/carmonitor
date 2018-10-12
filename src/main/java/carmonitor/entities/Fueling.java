package carmonitor.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Fueling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double literPrice;
	private double numberOfLiters;
	private double kilometersTraveled;
	private LocalDate fuelDate;
	
	@ManyToOne
	@JoinColumn(name="car_id", nullable = false)
	@JsonBackReference
	private Car car;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getLiterPrice() {
		return literPrice;
	}
	public void setLiterPrice(double literPrice) {
		this.literPrice = literPrice;
	}
	public double getNumberOfLiters() {
		return numberOfLiters;
	}
	public void setNumberOfLiters(double numberOfLiters) {
		this.numberOfLiters = numberOfLiters;
	}
	public double getKilometersTraveled() {
		return kilometersTraveled;
	}
	public void setKilometersTraveled(double kilometersTraveled) {
		this.kilometersTraveled = kilometersTraveled;
	}
	
	public LocalDate getFuelDate() {
		return fuelDate;
	}
	public void setFuelDate(LocalDate fuelDate) {
		this.fuelDate = fuelDate;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
