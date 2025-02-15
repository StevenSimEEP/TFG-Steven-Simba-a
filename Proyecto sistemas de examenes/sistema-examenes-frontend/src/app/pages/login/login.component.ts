import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginData = {
    "userName": '',
    "password": ''
  };

  constructor(
    private snack: MatSnackBar,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.loginService.isLoggedIn()) {
      this.loginService.getCurrentUser().subscribe({
        next: (userData) => {
          this.loginService.setUser(userData);
          console.log("Usuario cargado en navbar:", userData);
        },
        error: (error) => {
          console.error("Error obteniendo usuario:", error);
          this.loginService.logout();
        }
      });
    }
  }

  formSubmit() {
    console.log("Datos enviados al backend:", this.loginData);

    if (this.loginData.userName.trim() === '' || this.loginData.userName.trim() === null) {
      this.snack.open('El nombre de usuario es requerido !!', 'Aceptar', { duration: 3000 });
      return;
    }

    if (this.loginData.password.trim() === '' || this.loginData.password.trim() === null) {
      this.snack.open('La contraseña es requerida !!', 'Aceptar', { duration: 3000 });
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe({
      next: (data: any) => {
        console.log('✅ Token generado correctamente:', data);
        this.loginService.loginUser(data.token);

        // Obtener datos del usuario después de iniciar sesión
        this.loginService.getCurrentUser().subscribe({
          next: (user: any) => {
            if (!user || !user.username) {
              this.snack.open('No se pudo obtener la información del usuario.', 'Aceptar', { duration: 3000 });
              this.loginService.logout();
              return;
            }
            this.loginService.setUser(user);
            console.log("🔹 Usuario autenticado:", user);

            const userRole = this.loginService.getUserRole();
            console.log("🔹 Rol del usuario:", userRole);

            if (userRole === "ADMIN") {
              this.router.navigate(['admin']);
            } else if (userRole === "NORMAL") {
              this.router.navigate(['user-dashboard']);
            } else {
              this.loginService.logout();
              this.snack.open('Rol no reconocido. Inténtalo de nuevo.', 'Aceptar', { duration: 3000 });
            }
          },
          error: (error: any) => {
            console.error("❌ Error al obtener usuario:", error);
            this.snack.open('Error al obtener datos del usuario.', 'Aceptar', { duration: 3000 });
          }
        });
      },
      error: (error: any) => {
        console.error('❌ Error al iniciar sesión:', error);
        this.snack.open('Datos inválidos, vuelva a intentar!!', 'Aceptar', { duration: 3000 });
      }
    });
  }
}
