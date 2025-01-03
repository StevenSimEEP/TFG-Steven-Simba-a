import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubjec = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //generamos el token
  public generateToken(loginData:any) {
    return this.http.post(`${baseUrl}/generate-token`,loginData);
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
    return this.http.get(`${baseUrl}/actual-usuario`);
  }
}
