import { Component, OnInit } from '@angular/core';
import {Aluno} from "../../student/home-a/models/aluno.model";
import {Materia} from "../../student/home-a/models/materia.model";
import {ActivatedRoute} from "@angular/router";
import { Observable, Subscription } from 'rxjs/Rx';
import {TeacherDataService} from "./services/teacher-data.service";
import {Usuario} from "../../models/usuario.model";
import {UserDataService} from "../../student/home-a/services/user-data.service";
import {UserData} from "../../services/userdata.service";

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
  aluno: Usuario = new Usuario("","","","",false,"","","","",-1);

  materias: Materia[];

  constructor(private teacherData: TeacherDataService, userData: UserData, private route: ActivatedRoute) {
    console.log(route.snapshot.params['id']);

    teacherData.getDisciplinas().subscribe(
      disciplinas => {
        this.materias = disciplinas;
      }
    );

    this.aluno = UserData.getUsuario();

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
