import { Component, Input, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  user: any = null;
  isAdmin = false;
  isUser = false;
  isMenuOpen = true;

  constructor(
    public login: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();

    console.log('Usuario cargado:', this.user);

    if (this.user) {
      this.isAdmin = this.login.getUserRole() === 'ADMIN';
      this.isUser = this.login.getUserRole() === 'NORMAL';
    }

    // Escuchar cambios en la sesión
    this.login.loginStatusSubjec.asObservable().subscribe(() => {
      this.isLoggedIn = this.login.isLoggedIn();
      this.user = this.login.getUser();
      if (this.user) {
        this.isAdmin = this.login.getUserRole() === 'ADMIN';
        this.isUser = this.login.getUserRole() === 'ADMIN';
      }
    });
  }

  public logout() {
    this.login.logout();
    this.user = null;
    this.isAdmin = false;
    this.isUser = false;
    window.location.reload();
  }

  isHiddenRoute(): boolean {
    const hiddenRoutes = ['/', '/login', '/signup'];
    return hiddenRoutes.includes(this.router.url);
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
