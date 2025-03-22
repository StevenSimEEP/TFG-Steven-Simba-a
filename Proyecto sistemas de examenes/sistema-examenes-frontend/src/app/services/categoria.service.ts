import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private http: HttpClient) { }

  public listarCategorias() {
    return this.http.get(`${baseUrl}/category/`);
  }

  public agregarCategoria(categoria: any) {
    return this.http.post(`${baseUrl}/category/`, categoria);
  }

  public eliminarCategoria(id: number) {
    return this.http.delete(`${baseUrl}/category/${id}`);
  }

  public actualizarCategoria(categoria: any) {
    return this.http.put(`${baseUrl}/category/`, categoria);
  }
}
