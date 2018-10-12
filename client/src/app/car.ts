export class Car {
    public id:Number;
    public fuelingList = [];
    constructor(
        
        public  mark:String,
        public  fuelType:String,
        public  engineType:String,
        public  km:number,
        public  kW:number,
        public  model:String,
        public  productionYear:String
        
    ){}
    public statistic = {
        avarageFuelUsage: 0,
        totalCost : 0,
        fuelUsed : 0,
        kmTraveled: 0
    }
}
