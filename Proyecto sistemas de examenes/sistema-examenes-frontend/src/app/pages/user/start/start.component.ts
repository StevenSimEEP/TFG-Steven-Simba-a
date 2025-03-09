import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PreguntaService } from '../../../services/pregunta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css'
})
export class StartComponent implements OnInit{

  examId:any;
  questions:any;
  pointsEarned = 0;
  correctResponses = 0;
  attempts = 0;

  isSent = false;
  timer:any;

  constructor(
    private locationSt:LocationStrategy,
    private route:ActivatedRoute,
    private preguntaService:PreguntaService
  ) { }

  ngOnInit(): void {
    this.prevenirElBotonDeRetroceso();
    this.examId = this.route.snapshot.params['examId'];
    console.log(this.examId);
    this.cargarPreguntas();
  }

  cargarPreguntas() {
    this.preguntaService.listarPreguntasDelExamenParaPrueba(this.examId).subscribe(
      (data:any) => {
        console.log(data);
        this.questions = data;

        this.timer = this.questions.length *2 *60;

        this.questions.forEach((q:any) => {
          q['responseGiven'] = '';
        });
        console.log(this.questions);
        this.startTimer();
      },
      (error) => {
        console.log(error);
        Swal.fire('Error','Error al cargar las pregunats de la prueba','error');
      }
    )
  }

  startTimer() {
    let t = window.setInterval(() => {
      if(this.timer <= 0) {
        this.evaluarExamen();
        clearInterval(t);
      }else{
        this.timer --;
      }
    },1000)
  }

  prevenirElBotonDeRetroceso() {
    history.pushState(null,null!,location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null,null!,location.href);
    })
  }

  enviarCuestionario() {
    Swal.fire({
      title:'Quiéres enviar el examen',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Enviar',
      icon: 'info'
    }).then((e) => {
      if(e.isConfirmed) {
        this.evaluarExamen();
      }
    })
  }

  evaluarExamen() {
    if (!this.questions || this.questions.length === 0) {
      console.error("No hay preguntas para evaluar");
      return;
    }
    this.preguntaService.evaluarExamen(this.questions).subscribe(
      (data: any) => {
        console.log(data);
        this.pointsEarned = data.pointsMax;
        this.correctResponses = data.correctResponses;
        this.attempts = data.attempts;
        this.isSent = true;
      },
      (error) => {
        console.log(error);
      }
    );
   /* this.isSent = true;
    this.questions.forEach((q:any) => {
      if(q.responseGiven == q.response) {
        this.correctResponses ++;
        let score = this.questions[0].exam.pointsMax/this.questions.length;
        this.pointsEarned += score;
      }

      if(q.responseGiven.trim() != '') {
        this.attempts ++;
      }
    });

    console.log('Respuestas correctas:' + this.correctResponses);
    console.log('Puntos conseguidos: ' + this.pointsEarned);
    console.log('Intentos: ' + this.attempts);
    console.log(this.questions); */
  }

  obtenerHoraFormateada () {
    let mm = Math.floor(this.timer/60);
    let ss = this.timer - mm*60;
    return `${mm} : min : ${ss} seg`;
  }

  imprimirPagina() {
    window.print();
  }
}
