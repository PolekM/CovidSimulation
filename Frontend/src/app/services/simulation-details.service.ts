import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PopulationReadDto } from '../models/PopulationReadDto';

@Injectable({
  providedIn: 'root'
})
export class SimulationDetailsService {
  getSimulationById(id: any) {
      throw new Error('Method not implemented.');
  }

  baseUrl: String ='http://localhost:8080/population'

  constructor(private http: HttpClient) { }


  getPopulationBySimulationId(id: number):Observable<PopulationReadDto[]>{
    return this.http.get<PopulationReadDto[]>(`${this.baseUrl}/${id}`)
  }


}
