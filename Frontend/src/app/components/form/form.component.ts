import { Component, EventEmitter, input, Input, Output } from '@angular/core';
import { FormBuilder, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { SimulationListService } from '../../services/simulation-list.service';
import { SimulationSaveDataDto } from '../models/SimulationSaveDataDto';
import { ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-form',
  standalone: true,
  imports: [ButtonModule,DialogModule, ReactiveFormsModule,InputTextModule,InputNumberModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {


  simulationForm = this.formBuilder.group({
    n: ['', Validators.required],
    p: [1,Validators.required],
    i: [1,Validators.required],
    r: [1,Validators.required],
    m: [0.2,[Validators.required, Validators.min(0),Validators.max(1)]],
    ti: [1,Validators.required],
    tm: [1,Validators.required],
    ts: [1,Validators.required],
    
  });
  @Input() visible = false
  @Input() buttonType: String = "Add"
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() afterSave: EventEmitter<void> = new EventEmitter<void>();
  simulationId: number = {} as number

  constructor(private formBuilder: FormBuilder,private simulationService: SimulationListService,private route: ActivatedRoute){}

  createSimulation(){
    if(this.simulationForm.invalid){
      return
    }
      const simulationData = this.simulationForm.value as SimulationSaveDataDto;
      this.simulationService.createSimulation(simulationData).subscribe(response => {this.hidebox(), this.afterSave.emit(); })
 
  }
  updateSimulation(){
    console.log("update")
    if(this.simulationForm.invalid){
      return
    }
    const simulationData = this.simulationForm.value as SimulationSaveDataDto;
    this.route.params.subscribe(params =>{
      this.simulationId = params['id']
    })
    this.simulationService.updateSimulation(this.simulationId,simulationData).subscribe(response => {this.hidebox(), this.afterSave.emit(); })
  }
  
  hidebox(){
    this.visible = false;
    this.visibleChange.emit(this.visible)
  }

}
