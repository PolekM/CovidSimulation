import { Component, OnInit } from '@angular/core';
import { SimulationDetailsService } from '../../services/simulation-details.service';
import { PopulationReadDto } from '../models/PopulationReadDto';
import { ActivatedRoute } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-simulation-details',
  standalone: true,
  imports: [TableModule, ButtonModule],
  templateUrl: './simulation-details.component.html',
  styleUrl: './simulation-details.component.css'
})
export class SimulationDetailsComponent implements OnInit{

  population: PopulationReadDto[] = []
  simulationId: number = {} as number
  visible: boolean = false;

  constructor(private simulationDetailsService: SimulationDetailsService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.params.subscribe(params =>{this.simulationId = params['id']})
    this.getPopulationBySimulationId(this.simulationId)
  }

  getPopulationBySimulationId(id: number){
    this.simulationDetailsService.getPopulationBySimulationId(id).subscribe(response => this.population = response)
  }

  showDialog() {
    this.visible = true;
}
  
  

 
  


}
