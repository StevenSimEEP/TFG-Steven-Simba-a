import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Subject } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubjec = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //generamos el token
  public generateToken(loginData:any) {
    return this.http.post(`${baseUrl}/auth/login`,loginData, {
      headers: { 'Content-Type': 'application/json' }
    });
  }

  //iniciamos sesión y establecesmo el token en el localStroge
  public loginUser(token:any) {
    localStorage.setItem('token',token);
  }

  public isLoggedIn(): boolean {
    let tokenStr = localStorage.getItem('token');
    return tokenStr !== undefined && tokenStr !== null && tokenStr !== '';
  }

  //cerramos sesión y eliminamos el token del localStorage
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //Obtenemos el token
  public getToken() {
    return localStorage.getItem('token');
  }

  public setUser(user:any) {
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser() {
    let userStr = localStorage.getItem('user');
    if(userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }

  public getCurrentUser() {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.getToken()}`
    });

    return this.http.get(`${baseUrl}/auth/actual-usuario`, { headers }).pipe(
      catchError(error => {
        console.error("Error al obtener usuario:", error);
        return throwError(() => new Error("No se pudo obtener el usuario."));
      })
    );
  }
}
