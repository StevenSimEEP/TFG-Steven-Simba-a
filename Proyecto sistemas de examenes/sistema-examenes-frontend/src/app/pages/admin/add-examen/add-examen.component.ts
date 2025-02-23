import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ExamenService } from '../../../services/examen.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-examen',
  templateUrl: './add-examen.component.html',
  styleUrl: './add-examen.component.css'
})
export class AddExamenComponent implements OnInit{

  categorias:any = [];

  examenData = {
    title:'',
    description:'',
    pointsMax:'',
    numberQuestions:'',
    active:true,
    category:{
      categoryId:''
    }
  }

  constructor(
    private categoriaService: CategoriaService,
    private snack:MatSnackBar,
    private examenService:ExamenService,
    private router:Router) { }

  ngOnInit(): void {
    this.categoriaService.listarCategorias().subscribe(
      (dato:any) => {
        this.categorias = dato;
        console.log(this.categorias);
      }, (error) => {
        console.log(error);
        Swal.fire('Error !!', 'Error al cargar los datos', 'error');
      }
    )
  }

  guardarCuestionario() {
    console.log(this.examenData);
    if(this.examenData.title.trim() == '' || this.examenData.title == null) {
      this.snack.open('El título es requerido','',{
        duration:3000
      });
      return ;
    }

    this.examenService.agregarExamen(this.examenData).subscribe(
      (data) => {
        console.log(data);
        Swal.fire('Examen guardado', 'El examen ha sido guardado con éxito', 'success');
        this.examenData = {
          title : '',
          description: '',
          pointsMax: '',
          numberQuestions: '',
          active:true,
          category:{
            categoryId:''
          }
        }
        this.router.navigate(['/admin/examenes']);
      },
      (error) => {
        Swal.fire('Error','Error al guardar el examen', 'error')
      }
    )
  }
}
