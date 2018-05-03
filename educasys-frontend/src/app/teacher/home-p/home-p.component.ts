import {TeacherService} from "../teacher.service";
import { Component, OnInit } from '@angular/core';

import {Disciplina} from "../../student/home-a/models/materia.model";
import {ActivatedRoute} from "@angular/router";
import { Observable, Subscription } from 'rxjs/Rx';

import {Usuario} from "../../models/usuario.model";
import {UserData} from "../../services/userdata.service";
import {Professor} from "../../secretary/professor-s/professor-s.model";

@Component({
  selector: 'app-home-p',
  templateUrl: './home-p.component.html',
  styleUrls: ['./home-p.component.css']
})
export class HomePComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  id: string;
  professor: Professor = new Professor(-1,"","","","","","","");

  materias: Disciplina[];

  constructor(private teacherService: TeacherService, userData: UserData, private route: ActivatedRoute) {
    this.id = route.snapshot.params['id'];

    this.teacherService.getProfessorByCode(this.id).subscribe(
      professor =>{
        this.professor = professor;
        this.teacherService.getDisciplinas(this.professor.id_professor).subscribe(
          disciplinas=>{
            this.materias = disciplinas;
          }
        );
      }
    );

  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    }else{
      this.opened = 'open';
      this.closed = 'content';
    }

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());

  }

  changeOpt(){
    this.open = !this.open;
  }
}
