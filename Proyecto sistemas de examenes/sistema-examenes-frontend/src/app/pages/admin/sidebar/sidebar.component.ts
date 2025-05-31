import { Component, Input, OnInit } from '@angular/core';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-sidebar-admin',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  constructor(public login: LoginService) {}

  ngOnInit(): void {}

  @Input() collapsed: boolean = false;

  public logout() {
    this.login.logout();
    window.location.reload();
  }
}
