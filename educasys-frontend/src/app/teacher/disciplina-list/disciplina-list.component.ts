import { Component, OnInit } from '@angular/core';
import {TeacherService} from "../teacher.service";
import {ActivatedRoute} from "@angular/router";
import {Disciplina} from "../../student/home-a/models/materia.model";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-disciplina-list',
  templateUrl: './disciplina-list.component.html',
  styleUrls: ['./disciplina-list.component.css']
})
export class DisciplinaListComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  menu: string = "";
  id: string;
  materias: Disciplina[];

  constructor(private teacherService: TeacherService, private route: ActivatedRoute) {
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

    this.teacherService.getProfessorByCode(this.id).subscribe(
      professor =>{
        this.teacherService.getDisciplinas(professor.id_professor).subscribe(
          disciplinas=>{
            this.materias = disciplinas;
          }
        );
      }
    );
    
    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());

  }

  changeOpt() {
    this.open = !this.open;
  }

}
