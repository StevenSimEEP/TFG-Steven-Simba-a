import { Component, OnInit } from '@angular/core';
import { ExamenService } from '../../../services/examen.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instrucciones',
  templateUrl: './instrucciones.component.html',
  styleUrl: './instrucciones.component.css'
})
export class InstruccionesComponent implements OnInit{

  examId:any;
  exam:any;

  constructor(
    private exameneService:ExamenService,
    private route:ActivatedRoute,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.examId = this.route.snapshot.params['examId'];
    this.exameneService.obtenerExamen(this.examId).subscribe(
      (data:any) => {
        console.log(data);
        this.exam = data;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  empezarExamen() {
    Swal.fire({
      title:'¿Quiéres comenzar el examen?',
      showCancelButton:true,
      cancelButtonText:'Cancelar',
      confirmButtonText:'Empezar',
      icon:'info'
    }).then((result:any) => {
      if(result.isConfirmed) {
        this.router.navigate(['/start/'+this.examId]);
      }
    })
  }
}
