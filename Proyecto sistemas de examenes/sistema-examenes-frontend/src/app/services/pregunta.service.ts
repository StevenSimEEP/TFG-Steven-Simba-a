import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  constructor(private http:HttpClient) { }

  public listarPreguntasDelExamen(examId:any) {
    return this.http.get(`${baseUrl}/question/exam/all/${examId}`);
  }

  public guardarPregunta(question:any) {
    return this.http.post(`${baseUrl}/question/`, question);
  }

  public eliminarPregunta(questionId:any) {
    return this.http.delete(`${baseUrl}/question/${questionId}`);
  }

  public actualizarPregunta(question:any) {
    return this.http.put(`${baseUrl}/question/`, question);
  }

  public obtenerPregunta(questionId:any) {
    return this.http.get(`${baseUrl}/question/${questionId}`);
  }

  public listarPreguntasDelExamenParaPrueba(examId:any) {
    return this.http.get(`${baseUrl}/question/exam/all/${examId}`);
  }

  public evaluarExamen(questions:any) {
    return this.http.post(`${baseUrl}/question/evaluate-exam`, questions);
  }
}
