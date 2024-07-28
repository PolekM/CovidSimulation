import { Component, Input, Output } from '@angular/core';
import { SimulationListService } from '../../services/simulation-list.service';
import { SimulationReadDto } from '../models/SimulationReadDto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';
import { FormComponent } from "../form/form.component";
@Component({
  selector: 'app-simulation-list',
  standalone: true,
  imports: [TableModule, ButtonModule, ToolbarModule, TooltipModule, FormComponent],
  templateUrl: './simulation-list.component.html',
  styleUrl: './simulation-list.component.css'
})
export class SimulationListComponent {

  simulationList: SimulationReadDto[] =[] 
  message:String =''
  visible: boolean = false;
  constructor(private simulationListService: SimulationListService){

  }

  ngOnInit(): void {
    this.getSimulationDataList();
  }


  getSimulationDataList(){
    this.simulationListService.getSimulationDataList().subscribe(response=> {console.log(response);this.simulationList = response})
  }
  deleteSimulation(id:number){
   this.simulationListService.deleteSimulation(id).subscribe(response => {this.message=response; this.getSimulationDataList()});


   
  }
  showDialog() {
      this.visible = true;
  }
  afterSave(){
    this.getSimulationDataList()
  }
}
