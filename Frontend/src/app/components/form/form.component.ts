import { Component, EventEmitter, input, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { SimulationListService } from '../../services/simulation-list.service';
import { SimulationSaveDataDto } from '../models/SimulationSaveDataDto';
import { ActivatedRoute } from '@angular/router';
import { SimulationReadDto } from '../models/SimulationReadDto';



@Component({
  selector: 'app-form',
  standalone: true,
  imports: [ButtonModule,DialogModule, ReactiveFormsModule,InputTextModule,InputNumberModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnChanges{
  @Input() visible = false
  @Input() buttonType: String = "Add"
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() afterSave: EventEmitter<void> = new EventEmitter<void>();
  @Input() simulation: SimulationReadDto = {} as SimulationReadDto
  simulationId: number = {} as number

  simulationForm = this.formBuilder.group({
    n: ['', Validators.required],
    p: [1,Validators.required],
    i: [1,Validators.required],
    r: [1,Validators.required],
    m: [0.2,[Validators.required, Validators.min(0),Validators.max(1)]],
    ti: [1,Validators.required],
    tm: [1,Validators.required],
    ts: [ 1,Validators.required],
    
  });
 

  constructor(private formBuilder: FormBuilder,private simulationService: SimulationListService,private route: ActivatedRoute){}

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['simulation'] ){
      this.simulationForm.patchValue({
        n: this.simulation.n || '',
        p: this.simulation.p || 1,
        i: this.simulation.i || 1,
        r: this.simulation.r || 1,
        m: this.simulation.m || 0.2,
        ti: this.simulation.ti || 1,
        tm: this.simulation.tm || 1,
        ts: this.simulation.ts || 1,
      });
    }
  }

  createSimulation(){
    if(this.simulationForm.invalid){
      return
    }
      const simulationData = this.simulationForm.value as SimulationSaveDataDto;
      this.simulationService.createSimulation(simulationData).subscribe(response => {this.hidebox(), this.afterSave.emit(); })
 
  }
  updateSimulation(){
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
