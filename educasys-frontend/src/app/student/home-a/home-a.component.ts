import { Component, OnInit } from '@angular/core';
import {Aluno, DisciplinaA} from "../student.model";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {StudentService} from "../student.service";


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
  aluno: Aluno = new Aluno(-1,"","","","","","","","","","","");
  id: string;
  materias: DisciplinaA[];

  constructor(private studentService: StudentService, private route: ActivatedRoute) {
    this.id = route.snapshot.params['id'];

    studentService.getAlunoByCode(this.id).subscribe(
        student => {
          this.aluno = student;
          studentService.getDisciplinas(this.aluno.id_aluno).subscribe(
            disciplinas => {
              this.materias = disciplinas;
              console.log(this.materias);
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
