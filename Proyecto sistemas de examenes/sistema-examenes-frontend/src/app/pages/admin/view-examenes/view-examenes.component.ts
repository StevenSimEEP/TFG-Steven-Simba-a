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

}
