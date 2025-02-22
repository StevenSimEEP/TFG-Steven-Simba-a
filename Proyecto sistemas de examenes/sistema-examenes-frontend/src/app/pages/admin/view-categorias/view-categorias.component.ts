import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categorias',
  templateUrl: './view-categorias.component.html',
  styleUrl: './view-categorias.component.css'
})
export class ViewCategoriasComponent implements OnInit{

  categorias:any = [

  ]

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.categoriaService.listarCategorias().subscribe(
      (dato:any) => {
        console.log('Respuesta de la API:', dato);

        if (Array.isArray(dato)) {
          this.categorias = dato;
          console.log(this.categorias);
        } else {
          console.error('La API no devolvió un array:', dato);
          this.categorias = []; // Evita errores si la respuesta no es un array
        }
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !!','Error al cargar las categorías','error');
      }
    );
  }
}
