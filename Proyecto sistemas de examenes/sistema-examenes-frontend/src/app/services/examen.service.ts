import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class ExamenService {

  constructor(private http: HttpClient) { }

  public listarCuertionarios() {
    return this.http.get(`${baseUrl}/exam/`);
  }

  public agregarExamen(examen:any) {
    return this.http.post(`${baseUrl}/exam/`, examen);
  }

  public eliminarExamen(examId:any) {
    return this.http.delete(`${baseUrl}/exam/${examId}`);
  }

  public obtenerExamen(examId:any) {
    return this.http.get(`${baseUrl}/exam/${examId}`);
  }

  public actualizarExamen(examen:any) {
    return this.http.put(`${baseUrl}/exam/`, examen);
  }
}
