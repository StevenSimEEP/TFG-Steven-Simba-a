import { Component, OnInit } from '@angular/core';
import { ExamenService } from '../../../services/examen.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-examenes',
  templateUrl: './view-examenes.component.html',
  styleUrl: './view-examenes.component.css'
})
export class ViewExamenesComponent implements OnInit{

  examenes : any = [

  ]

  constructor(private examenService:ExamenService) {}

  ngOnInit(): void {
    this.examenService.listarCuertionarios().subscribe(
      (dato:any) => {
        this.examenes = dato;
        console.log(this.examenes);
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'Error al cargar los exámenes', 'error');
      }
    );
  }

  eliminarExamen(examId:any) {
    Swal.fire({
      title:'Eliminar examen',
      text:'¿Estás seguro/a de eliminar el exámen?',
      icon: 'warning',
      showCancelButton:true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if(result.isConfirmed) {
        this.examenService.eliminarExamen(examId).subscribe(
          (data) => {
            this.examenes = this.examenes.filter((exam:any) => exam.examId != examId);
            Swal.fire('Examen eliminado','El examen ha sido eliminado de la base de datos','success');
          },
          (error) => {
            Swal.fire('Error','Error al eliminar el examen','error');
          }
        )
      }
    })
  }
}
