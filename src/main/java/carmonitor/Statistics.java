package carmonitor;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import carmonitor.entities.Fueling;


public class Statistics {
	public static Hashtable<String,Object> carInfo(List<Fueling> fuelingList){
		Hashtable<String, Object> hstable = new Hashtable<String, Object>();

		if(fuelingList.isEmpty()) {
			hstable.put("kmTraveled", 0);
			hstable.put("fuelUsed", 0);
			hstable.put("totalCost", 0);
			hstable.put("avarageFuelUsage", 0);

			
			return hstable;
		}else {
		    
			Optional<Double> kmTraveled = fuelingList.stream().map(e ->
				e.getKilometersTraveled()
				).reduce( (x,y) -> x+y );
			Optional<Double> fuelUsed = fuelingList.stream().map( e -> e.getNumberOfLiters())
														.reduce((x,y)->x+y);
			Optional<Double> totalCost = fuelingList.stream().map(e -> e.getLiterPrice()*e.getNumberOfLiters())
					.reduce((x,y)->x+y);
			Optional<Double> avarageFuelUsage = fuelingList
													.stream()
													.map(e -> (e.getNumberOfLiters()/e.getKilometersTraveled())*100)
													.reduce((x,y)->x+y);
			hstable.put("kmTraveled", kmTraveled.get());
			hstable.put("fuelUsed", fuelUsed.get());
			hstable.put("totalCost", totalCost.get());
			hstable.put("avarageFuelUsage", avarageFuelUsage.get()/fuelingList.size());
			return hstable;
		}

		

		
		
	}
}
