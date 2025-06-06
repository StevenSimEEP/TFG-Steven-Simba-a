import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { error } from 'console';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  user:any = null;

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    //this.user = this.loginService.getUser();
   this.loginService.getCurrentUser().subscribe(
      (user:any) => {
        this.user = user;
      },
      (error) => {
        alert("error");
      }
    )
  }

}
