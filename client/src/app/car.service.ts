import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http:HttpClient) { }

  getAllCars():Observable<any>{
    return this.http.get('http://localhost:8080/api/car');
  }

  insertCar(obj):Observable<any>{
    const uri = 'http://localhost:8080/api/car';
    return this.http.post(uri,obj);
  }
  deleteCar(id:number):Observable<any>{
    const uri = 'http://localhost:8080/api/car/'+id;
    console.log(uri);
    return this.http.delete(uri);
  }

  deleteFuel(id:number):Observable<any>{
    const uri = 'http://localhost:8080/api/fuel/'+id;
    console.log(uri);
    return this.http.delete(uri);
  }
  addFuel(id:number, obj:any):Observable<any>{
    const uri = 'http://localhost:8080/api/fuel/'+id;
    return this.http.post(uri, obj);
  }
  loadStats(id:number):Observable<any>{
    const uri = 'http://localhost:8080/api/stats/'+id;
    return this.http.get(uri);
  }
}
