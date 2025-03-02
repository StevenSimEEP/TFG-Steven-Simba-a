import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css'
})
export class StartComponent implements OnInit{

  constructor(
    private locationSt:LocationStrategy
  ) { }

  ngOnInit(): void {
    this.prevenirElBotonDeRetroceso();
  }

  prevenirElBotonDeRetroceso() {
    history.pushState(null,null!,location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null,null!,location.href);
    })
  }
}
