import { Component, OnInit } from '@angular/core';

import {Materia} from "./models/materia.model";
import {Aluno} from "./models/aluno.model";
import {UserDataService} from "./services/user-data.service";
import {ActivatedRoute} from "@angular/router";
import {UserData} from "../../services/userdata.service";
import {Usuario} from "../../models/usuario.model";
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";


@Component({
  selector: 'app-home-a',
  templateUrl: './home-a.component.html',
  styleUrls: ['./home-a.component.css']
})
export class HomeAComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  aluno: Usuario = new Usuario("","","","",false,"","","","",-1);

  materias: Materia[];

  constructor(private userData: UserDataService, private route: ActivatedRoute) {
    console.log(route.snapshot.params['id'])

    userData.getDisciplinas().subscribe(
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
