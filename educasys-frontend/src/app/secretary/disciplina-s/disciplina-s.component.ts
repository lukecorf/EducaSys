import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {Router} from "@angular/router";
import {DisciplinaList} from "./disciplina-s.model";
import {SecretariaService} from "../secretaria.service";

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
  selectedRow : number = -1;
  setClickedRow : Function;

  disciplinas: DisciplinaList[];

  constructor(private router: Router, private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    };

    secretariaService.getDisciplinas().subscribe(
      disciplinas => {
        this.disciplinas = disciplinas;
      }
    );
  }

  ngOnInit() {
    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt(){
    this.open = !this.open;
  }

  goNew(){
    this.router.navigate(['disciplina-s-cadastro',1,0]);
  }

  goView(){
    this.router.navigate(['disciplina-s-cadastro',2,this.disciplinas[this.selectedRow].nuId]);
  }

  goEdit(){
    this.router.navigate(['disciplina-s-cadastro',3,this.disciplinas[this.selectedRow].nuId]);
  }

  goDelete(){
    this.secretariaService.deleteDisciplinaById(this.disciplinas[this.selectedRow].nuId).subscribe(id => {
      console.log('This is my ID: '+id);
      this.secretariaService.getDisciplinas().subscribe(
        disciplinas => {
          this.disciplinas = disciplinas;
        }
      );
      console.log("Excluido com sucesso");

    },error => {
      console.log("Erro ao excluir");
    });
    this.router.navigate(['disciplina-s']);
  }

}
