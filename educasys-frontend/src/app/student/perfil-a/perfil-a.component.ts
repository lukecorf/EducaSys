import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'perfil-a',
  templateUrl: './perfil-a.component.html',
  styleUrls: ['./perfil-a.component.css']
})
export class PerfilAComponent implements OnInit {

  open: boolean = true;
  private timer;
  private sub: Subscription;
  opened: string;
  closed: string;

  constructor() { }

  ngOnInit() {
    if (window.screen.width < 768) {
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    } else {
      this.opened = 'open';
      this.closed = 'content';
    }

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt() {
    this.open = !this.open;
  }
}
