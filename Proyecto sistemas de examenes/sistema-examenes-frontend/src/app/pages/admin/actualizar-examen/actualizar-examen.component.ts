import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamenService } from '../../../services/examen.service';
import { CategoriaService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-examen',
  templateUrl: './actualizar-examen.component.html',
  styleUrl: './actualizar-examen.component.css'
})
export class ActualizarExamenComponent implements OnInit{

  constructor(
    private route:ActivatedRoute,
    private examenService:ExamenService,
    private categoriaService:CategoriaService,
    private router:Router) { }

  examId = 0;
  exam:any;
  categories:any;

  ngOnInit(): void {
    this.examId = this.route.snapshot.params['examId'];
    this.examenService.obtenerExamen(this.examId).subscribe(
      (data) => {
        this.exam = data;
        console.log(this.exam);
      },
      (error) => {
        console.log(error);
      }
    )

    this.categoriaService.listarCategorias().subscribe(
      (data:any) => {
        this.categories = data;
      },
      (error) => {
        alert('Error al cargar las categorías')
      }
    )
  }

  public actualizarDatos() {
    this.examenService.actualizarExamen(this.exam).subscribe(
      (data) => {
        Swal.fire('Examen actualizado', 'El examen ha sido actualizado con éxito','success').then(
          (e) => {
            this.router.navigate(['/admin/examenes']);
          },
        );
      },
      (error) => {
        Swal.fire('Erro en el sistema','No se ha podido actualizar el examen','error');
        console.log('error');
      }
    )
  }
}
