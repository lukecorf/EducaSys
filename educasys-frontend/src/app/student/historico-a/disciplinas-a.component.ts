import { Component, OnInit } from '@angular/core';

import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {StudentService} from "../student.service";
import {ActivatedRoute} from "@angular/router";
import {DisciplinaA} from "../student.model";

@Component({
  selector: 'historico-a',
  templateUrl: './disciplinas-a.component.html',
  styleUrls: ['./disciplinas-a.component.css']
})
export class DisciplinasAComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  menu: string = "";
  id: number;
  materias: DisciplinaA[];

  constructor(private studentService: StudentService, private route: ActivatedRoute) {
    this.id = route.snapshot.params['id'];
  }

  ngOnInit() {
    if (window.screen.width < 768) {
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    } else {
      this.opened = 'open';
      this.closed = 'content';
    }

    this.studentService.getDisciplinas(this.id).subscribe(
      disciplinas => {
        this.materias = disciplinas;
        console.log(this.materias);
      }
    );

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());

  }

  changeOpt() {
    this.open = !this.open;
  }

}
