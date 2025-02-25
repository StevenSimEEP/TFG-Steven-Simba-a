import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PreguntaService } from '../../../services/pregunta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-pregunta',
  templateUrl: './actualizar-pregunta.component.html',
  styleUrl: './actualizar-pregunta.component.css'
})
export class ActualizarPreguntaComponent implements OnInit{

    questionId:any = 0;
    question:any;
    exam:any;

  constructor(
    private route:ActivatedRoute,
    private preguntaService:PreguntaService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.questionId=this.route.snapshot.params['questionId'];
    this.preguntaService.obtenerPregunta(this.questionId).subscribe(
      (data:any) => {
        this.question = data;
        console.log('this.quesion');
      },
      (error) => {
        console.log(error);
      }
    )
  }

  public actualizarDatosDePregunta() {
    this.preguntaService.actualizarPregunta(this.question).subscribe(
      (data) => {
        Swal.fire('Pregunta actualizada','La pregunta ha sido actualizada con éxito','success').then((e) => {
          this.router.navigate(['/admin/ver-preguntas/'+this.question.exam.examId+'/'+this.question.exam.title]);
        })
      }
    )
  }
}
