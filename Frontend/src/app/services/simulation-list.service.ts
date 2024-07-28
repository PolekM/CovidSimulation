import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SimulationReadDto } from '../components/models/SimulationReadDto';
import { SimulationSaveDataDto } from '../components/models/SimulationSaveDataDto';

@Injectable({
  providedIn: 'root'
})
export class SimulationListService {

  baseUrl: String ='http://localhost:8080/simulation'

  constructor(private http: HttpClient) { }


  getSimulationDataList(): Observable<SimulationReadDto[]>{
    return this.http.get<SimulationReadDto[]>(`${this.baseUrl}`)
  }

  deleteSimulation(id:number):Observable<String>{
    return this.http.delete(`${this.baseUrl}/remove/${id}`, {responseType: 'text'})
  }
  createSimulation(simulationData: SimulationSaveDataDto):Observable<String>{
    return this.http.post(`${this.baseUrl}/create`,simulationData,{responseType:'text'})
  }
}
