import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categorias',
  templateUrl: './view-categorias.component.html',
  styleUrl: './view-categorias.component.css'
})
export class ViewCategoriasComponent implements OnInit {

  categorias: any = [];

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  // 🔄 Método reutilizable para cargar categorías
  cargarCategorias(): void {
    this.categoriaService.listarCategorias().subscribe(
      (dato: any) => {
        console.log('Respuesta de la API:', dato);
        if (Array.isArray(dato)) {
          this.categorias = dato;
        } else {
          console.error('La API no devolvió un array:', dato);
          this.categorias = [];
        }
      },
      (error) => {
        console.log(error);
        Swal.fire('Error !!', 'Error al cargar las categorías', 'error');
      }
    );
  }

  eliminarCategoria(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'No podrás revertir esto',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.categoriaService.eliminarCategoria(id).subscribe(
          (response) => {
            this.cargarCategorias(); // 🔁 recarga desde la API para reflejar cambios
            Swal.fire('Eliminado!', 'La categoría ha sido eliminada.', 'success');
          },
          (error) => {
            Swal.fire('Error!', 'No se pudo eliminar la categoría.', 'error');
          }
        );
      }
    });
  }

  editarCategoria(categoria: any): void {
    console.log("Categoría recibida para editar:", categoria);

    if (!categoria || (!categoria.title && !categoria.nombre)) {
      Swal.fire('Error', 'La categoría no es válida', 'error');
      return;
    }

    let nombreActual = categoria.title || categoria.nombre;

    Swal.fire({
      title: 'Editar Categoría',
      input: 'text',
      inputValue: nombreActual,
      showCancelButton: true,
      confirmButtonText: 'Actualizar',
      preConfirm: (nuevoNombre) => {
        if (!nuevoNombre) {
          Swal.showValidationMessage('El nombre no puede estar vacío');
        }
        return nuevoNombre;
      }
    }).then((result) => {
      if (result.isConfirmed) {
        let categoriaActualizada = { ...categoria, title: result.value || categoria.nombre };
        this.categoriaService.actualizarCategoria(categoriaActualizada).subscribe(
          (data) => {
            this.cargarCategorias(); // recarga también después de editar
            Swal.fire('Actualizado', 'Categoría actualizada correctamente', 'success');
          },
          (error) => {
            Swal.fire('Error', 'No se pudo actualizar la categoría', 'error');
          }
        );
      }
    });
  }
}
