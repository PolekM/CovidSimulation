import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { catchError, throwError } from 'rxjs';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  // const messageService = inject(MessageService)
  return next(req).pipe(catchError((error: HttpErrorResponse)=>{
  
    if(error.status== 404){
      router.navigate(['/error'])
      console.log("Not Found " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    if(error.status== 400){
      // messageService.add({severity:'error',summary: 'XD',detail: error.error.message})
      console.log("Not Found " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    if(error.status== 500){
      router.navigate(['/error'])
      console.log("Serwer Error " + error.error.code +" " + error.error.message + " " + error.error.errorTime);
    }
    return throwError(() => new Error(error.message));
  }))
};