import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PreguntaService } from '../../../services/pregunta.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-pregunta',
  templateUrl: './add-pregunta.component.html',
  styleUrl: './add-pregunta.component.css'
})
export class AddPreguntaComponent implements OnInit{

  examId:any;
  title:any;
  question:any = {
    exam : {},
    content : '',
    option1 : '',
    option2 : '',
    option3 : '',
    option4 : '',
    response: ''
  }

  constructor(
    private route:ActivatedRoute,
    private preguntaService:PreguntaService,
    private router:Router) {  }

  ngOnInit(): void {
    this.examId = this.route.snapshot.params['examId'];
    this.title = this.route.snapshot.params['title']
    this.question.exam['examId'] = this.examId;
  }

  formSubmit() {
    if(this.question.content.trim() == '' || this.question.content == null) {
      return;
    }
    if(this.question.option1.trim() == '' || this.question.option1 == null) {
      return;
    }
    if(this.question.option2.trim() == '' || this.question.option2 == null) {
      return;
    }
    if(this.question.option3.trim() == '' || this.question.option3 == null) {
      return;
    }
    if(this.question.option4.trim() == '' || this.question.option4   == null) {
      return;
    }
    if(this.question.response.trim() == '' || this.question.response == null) {
      return;
    }

    this.preguntaService.guardarPregunta(this.question).subscribe(
      (data) => {
        Swal.fire('Pregunta guardada','La pregunta ha sido agregada con éxito','success');
        this.router.navigate(['/admin/examenes/'])
      },
      (error) => {
        Swal.fire('Error en el sistema','Error al guardar la pregunta','error');
        console.log(error);
      }
    )
  }
}
