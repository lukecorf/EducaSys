import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'disciplina-s-component',
  templateUrl: './disciplina-s.component.html',
  styleUrls: ['./disciplina-s.component.css']
})
export class DisciplinaSComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : Number;
  setClickedRow : Function;

  disciplinas = [{
    nome : "Web Avançado",
    professor: "Bruno Ferreira",
    cargaH : "60",
    periodo: "2018/01"
  },
  {
    nome : "Redes",
    professor: "Everthon Valadão",
    cargaH : "60",
    periodo: "2018/01"
  }];

  constructor() {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    }
  }

  ngOnInit() {
    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt(){
    this.open = !this.open;
  }

}
