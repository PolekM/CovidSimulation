import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { SimulationListService } from '../../services/simulation-list.service';
import { SimulationSaveDataDto } from '../models/SimulationSaveDataDto';


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
    p: [0,Validators.required],
    i: [0,Validators.required],
    r: [0,Validators.required],
    m: [0,[Validators.required, Validators.min(0),Validators.max(1)]],
    ti: [0,Validators.required],
    tm: [0,Validators.required],
    ts: [0,Validators.required],
    
  });
  @Input() visible = false
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() afterSave: EventEmitter<void> = new EventEmitter<void>();

  constructor(private formBuilder: FormBuilder,private simulationService: SimulationListService){}

  createSimulation(){
    if(this.simulationForm.invalid){
      return
    }
      const simulationData = this.simulationForm.value as SimulationSaveDataDto;
      this.simulationService.createSimulation(simulationData).subscribe(response => {this.hidebox(), this.afterSave.emit(); })
 
  }
  
  hidebox(){
    this.visible = false;
    this.visibleChange.emit(this.visible)
  }
}
