import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../../services/login.service';
import { error } from 'console';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginData ={
    "username" : '',
    "password" : ''
  }

  constructor(private snack:MatSnackBar, private loginService:LoginService, private router:Router) {}


  formSubmit() {
    if(this.loginData.username.trim() == '' || this.loginData.username.trim() == null) {
      this.snack.open('El nombre de usuario es requerido !!','Aceptar',{
        duration:3000
      })
    }

    if(this.loginData.password.trim() == '' || this.loginData.password.trim() == null) {
      this.snack.open('La contraseña es requerida !!','Aceptar',{
        duration:3000
      })
    }

    this.loginService.generateToken(this.loginData).subscribe({
      next: (data: any) => {
        console.log('Token generado correctamente:', data);

        this.loginService.loginUser(data.token);
        this.loginService.getCurrentUser().subscribe((user:any) => {
          this.loginService.setUser(user);
          console.log(user);

          if(this.loginService.getUserRole() == "ADMIN") {
            //admin dashboard
            this.router.navigate(['admin']);
            this.loginService.loginStatusSubjec.next(true);
          } else if(this.loginService.getUserRole() == "NORMAL") {
            //user dashboard
            this.router.navigate(['user-dashboard']);
            this.loginService.loginStatusSubjec.next(true);
          } else {
            this.loginService.logout();
          }
        })
      },
      error: (error: any) => {
        console.error('Error al generar el token:', error);
        this.snack.open('Datos inválidos, vuelva a intentar!!','Aceptar',{
          duration:3000
        })
      }
    });
  }
}
