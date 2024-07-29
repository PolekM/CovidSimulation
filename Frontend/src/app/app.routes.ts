import { Routes } from '@angular/router';
import { SimulationListComponent } from './components/simulation-list/simulation-list.component';
import { SimulationDetailsComponent } from './components/simulation-details/simulation-details.component';
import { ErrorComponent } from './components/error/error.component';


export const routes: Routes = [
    {path:'',component:SimulationListComponent},
    {path:'simulation',component:SimulationListComponent},
    {path:'population/:id',component:SimulationDetailsComponent},
    {path:'error',component:ErrorComponent},
    {path:"**",redirectTo:'/simulation'}
];
