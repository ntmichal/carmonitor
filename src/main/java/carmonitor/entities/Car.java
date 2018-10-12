package carmonitor.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import carmonitor.Statistics;


@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mark;
	private String fuelType;
	private String engineType;
	private double KM;
	private double kW;
	private String model;
	private String productionYear;
	
	
	@OneToMany(mappedBy="car")
	private List<Fueling> fuelingList;
	
	@Transient
	@JsonSerialize
	private Object statistic;

	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public double getKM() {
		return KM;
	}
	public void setKM(double kM) {
		KM = kM;
	}
	public double getkW() {
		return kW;
	}
	public void setkW(double kW) {
		this.kW = kW;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}
	public List<Fueling> getFuelingList() {
		return fuelingList;
	}
	public void setFuelingList(List<Fueling> fuelingList) {
		this.fuelingList = fuelingList;
	}
	public Object getStatistic() {
		return statistic;
	}
	public void setStatistic(Object statistic) {
		this.statistic = statistic;
	}
	
	
}
