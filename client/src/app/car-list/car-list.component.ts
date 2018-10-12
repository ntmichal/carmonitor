import { Component, OnInit } from '@angular/core';
import { CarService } from '../car.service';
import { Fueling } from '../fueling';
import { Car } from '../car';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  carsList: Array<any>;
  showList:Array<boolean>;
  currentCarId:number = -1;
  form:boolean;
//model = new Hero(18, 'Dr IQ', this.powers[0], 'Chuck Overstreet');
fueling:Fueling = new Fueling(0,0,0,'');
car:Car = new Car('','','',null,null,'','');
submitted = false;

onSubmit() { this.submitted = true; }


  constructor(private carService:CarService) {
    this.showList = [];
    this.form = true;
   }

  ngOnInit() {
    this.carService.getAllCars().subscribe(data => {
      this.carsList = data;   

      this.showList.length = this.carsList.length;
      this.showList.fill(true,0,this.showList.length);

    });


  }
  
  showCar(id){

    this.form = true;
    this.showList.fill(true,0,this.showList.length);
    var carid = this.carsList.map(element =>{
      return element.id;
    }).indexOf(id);
    this.currentCarId = carid;
    this.showList[carid] = !this.showList[carid];


 
  }

  addCarForm(){
    this.form = !this.form;
    this.showList[this.currentCarId] = !this.showList[this.currentCarId];
  }
  
  deleteCar(item){

      this.carService.deleteCar(item.id).subscribe(res=>{
        console.log("deleted id = " + item.id);
        var index = this.carsList.indexOf(item);
        this.carsList.splice(index,1);
      });

      this.showList.length = this.carsList.length;
      this.showList.fill(true,0,this.showList.length);
      this.showList[item.id] = !this.showList[item.id];
  }
  deleteFueling(item, carid){

    const obiect = this.carsList.map(e =>{
      return e.fuelingList.map(e =>{
        return e.id;
      });
    });
    const caridx = this.carsList.map(element =>{
      return element.id;
    }).indexOf(carid);

    const index = obiect[caridx].indexOf(item.id);




    this.carsList[caridx].fuelingList.splice(index,1);


    this.carService.deleteFuel(item.id).subscribe(res=>{
      console.log(res);
    });

    this.recalStats(caridx);
  }
  addFueling(carid, f11, f12, f13, f14){

    var obj = new Fueling(Number(f11),Number(f12),Number(f13),f14);
    
    this.carService.addFuel(carid, obj).subscribe(res =>{
      obj.id = Number(res);
    });

    const caridx = this.carsList.map(element =>{
      return element.id;
    }).indexOf(carid);



    console.log(this.carsList[caridx]);
     this.carsList[caridx].fuelingList.push(obj);
     this.recalStats(caridx);
  } 
  loadCars(){
    this.carService.getAllCars().subscribe(data => {
      this.carsList = data;
    });
  }

  addCar(){

    this.carService.insertCar(this.car).subscribe( (res:Response)  =>{
        this.car.id = Number(res);
  
    });
    this.carsList.push(this.car);
    this.showList.length = this.carsList.length;
    this.showList.fill(true,0,this.showList.length);

    this.form = !this.form;

  }
  recalStats(carid){
    
    this.carsList[carid].statistic.kmTraveled = 
      this.carsList[carid].fuelingList
      .map(e =>{ return e.kilometersTraveled})
      .reduce( (a,b) =>{return a+b; },0);

    this.carsList[carid].statistic.totalCost = 
      this.carsList[carid].fuelingList
      .map(e =>{ return e.literPrice*e.numberOfLiters})
      .reduce( (a,b) =>{return a+b; },0);

    this.carsList[carid].statistic.avarageFuelUsage = 
       this.carsList[carid].fuelingList
       .map(e =>{ return e.numberOfLiters/e.kilometersTraveled*100})
       .reduce( (a,b) =>{return a+b; },0);

    this.carsList[carid].statistic.fuelUsed = 
      this.carsList[carid].fuelingList
      .map(e =>{ e.numberOfLiters})
      .reduce( (a,b) =>{return a+b; },0);

  }
}

