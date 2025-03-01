import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExamenService } from '../../../services/examen.service';

@Component({
  selector: 'app-load-examen',
  templateUrl: './load-examen.component.html',
  styleUrl: './load-examen.component.css'
})
export class LoadExamenComponent implements OnInit{

  catId:any;
  exams:any;

  constructor(
    private route:ActivatedRoute,
    private examenService:ExamenService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.catId = params['catId'];

      if(this.catId == 0) {
        console.log('Cargando todos los exámenes');
        this.examenService.obtenerExamenesActivos().subscribe(
          (data) => {
            this.exams = data;
            console.log(this.exams);
          },
          (error) => {
            console.log(error);
          }
        )
      } else {
        console.log('Cargando un exámen en específico');
        this.examenService.obtenerExamenesActivosDeUnaCategoria(this.catId).subscribe(
          (data:any) => {
            this.exams = data;
            console.log(this.exams);
          },
          (error) => {
            console.log(error);
          }
        )
      }
    })
  }
}
