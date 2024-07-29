import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-error',
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {

  constructor(private router: Router){}
  
  navigateToSimulation(){
    this.router.navigate(['/simulation'])

  }
}
