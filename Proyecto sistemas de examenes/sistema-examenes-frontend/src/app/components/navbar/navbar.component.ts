import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user: any = null; // Variable para almacenar los datos del usuario

  constructor(public login: LoginService) {}

  ngOnInit(): void {
    if (this.login.isLoggedIn()) {
      this.user = this.login.getUser(); // Obtener el usuario si ya está logueado
    }
  }

  public logout() {
    this.login.logout();
    this.user = null; // Limpiar usuario al cerrar sesión
    window.location.reload();
  }
}
