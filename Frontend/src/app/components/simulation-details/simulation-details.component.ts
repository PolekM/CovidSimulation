import { Component, OnInit } from '@angular/core';
import { SimulationDetailsService } from '../../services/simulation-details.service';
import { PopulationReadDto } from '../models/PopulationReadDto';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { ChartModule } from 'primeng/chart';
import { TooltipModule } from 'primeng/tooltip';
import { FormComponent } from "../form/form.component";
import { SimulationReadDto } from '../models/SimulationReadDto';
import { SimulationListService } from '../../services/simulation-list.service';

@Component({
  selector: 'app-simulation-details',
  standalone: true,
  imports: [TableModule, ButtonModule, ChartModule, TooltipModule, RouterModule, FormComponent,FormComponent],
  templateUrl: './simulation-details.component.html',
  styleUrl: './simulation-details.component.css'
})
export class SimulationDetailsComponent implements OnInit{

  population: PopulationReadDto[] = []
  infection: any
  recovery: any
  dead: any 
  allData: any
  options: any
  simulationId: number = {} as number
  visible: boolean = false;
  simulation: SimulationReadDto = {} as SimulationReadDto
  

  constructor(private simulationDetailsService: SimulationDetailsService,private simulationListService: SimulationListService, private route: ActivatedRoute,private router: Router){}

  ngOnInit(): void {
    this.route.params.subscribe(params =>{
      this.simulationId = params['id']
      this.getPopulationBySimulationId(this.simulationId)
      this.getSimulationById(this.simulationId)
    
    })
    
  }

  getPopulationBySimulationId(id: number){
    this.simulationDetailsService.getPopulationBySimulationId(id).subscribe(response => {this.population = response; this.infectionChartData();this.RecoveryChartData(),this.DeadChartData(),this.allCharts()})
  }
  getSimulationById(id: number){
    this.simulationListService.getSimulationById(id).subscribe(resposne =>{this.simulation = resposne})
  }

  showDialog() {
    this.visible = true;
}
    afterChange(){
        this.ngOnInit()
  }

  infectionChartData(){
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const infectionList: number[] = this.population.map(i => i.pi)
    this.infection = {
      labels: infectionList.map((_, index) => `day ${index+1}`),
      datasets: [
          {
              label: 'Infected',
              fill: false,
              borderColor: documentStyle.getPropertyValue('--red-500'),
              yAxisID: 'y',
              tension: 0.4,
              data: infectionList
              
          },
      ]
  };
  this.options = {
      maintainAspectRatio: false,
      aspectRatio: 0.6,
      plugins: {
          legend: {
              labels: {
                  color: textColor
              }
          }
      },    
  };
}
RecoveryChartData(){
  const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--text-color');
  const recoveryList: number[] = this.population.map(i => i.pr)
  this.recovery = {
    labels: recoveryList.map((_, index) => `day ${index+1}`),
    datasets: [
        {
            label: 'Recovery',
            fill: false,
            borderColor: documentStyle.getPropertyValue('--yellow-500'),
            yAxisID: 'y',
            tension: 0.4,
            data: recoveryList
            
        },
    ]
};
this.options = {
    maintainAspectRatio: false,
    aspectRatio: 0.6,
    plugins: {
        legend: {
            labels: {
                color: textColor
            }
        }
    },    
};
}
DeadChartData(){
  const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--text-color');
  const dead: number[] = this.population.map(i => i.pm)
  this.dead = {
    labels: dead.map((_, index) => `day ${index+1}`),
    datasets: [
        {
            label: 'Dead',
            fill: false,
            borderColor: documentStyle.getPropertyValue('--bluegray-500'),
            yAxisID: 'y',
            tension: 0.4,
            data: dead
            
        },
    ]
};
this.options = {
    maintainAspectRatio: false,
    aspectRatio: 0.6,
    plugins: {
        legend: {
            labels: {
                color: textColor
            }
        }
    },    
};
}
allCharts(){
  const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--text-color');
  const deadList: number[] = this.population.map(i => i.pm)
  const recoveryList: number[] = this.population.map(i => i.pr)
  const infectionList: number[] = this.population.map(i => i.pi)
  const susceptibleList: number[] = this.population.map(i => i.pv)
  this.allData = {
    labels: deadList.map((_, index) => `day ${index+1}`),
    datasets: [
        {
            label: 'Dead',
            fill: false,
            borderColor: documentStyle.getPropertyValue('--bluegray-500'),
            yAxisID: 'y',
            tension: 0.4,
            data: deadList
            
        },
        {
          label: 'Recovery',
          fill: false,
          borderColor: documentStyle.getPropertyValue('--yellow-500'),
          yAxisID: 'y',
          tension: 0.4,
          data: recoveryList
          
      },
      {
        label: 'infected',
        fill: false,
        borderColor: documentStyle.getPropertyValue('--red-500'),
        yAxisID: 'y',
        tension: 0.4,
        data:  infectionList
        
    },
    {
      label: 'susceptible',
      fill: false,
      borderColor: documentStyle.getPropertyValue('--green-500'),
      yAxisID: 'y',
      tension: 0.4,
      data:  susceptibleList
      
  },
    ],
};
this.options = {
    maintainAspectRatio: false,
    aspectRatio: 0.6,
    plugins: {
        legend: {
            labels: {
                color: textColor
            }
        }
    },    
};
}
}
  
  
  

 
  


