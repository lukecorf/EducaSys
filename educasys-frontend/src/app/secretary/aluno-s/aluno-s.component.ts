import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {SecretariaService} from "../secretaria.service";
import {Router} from "@angular/router";
import {AlunoList} from "./aluno-s.model";

@Component({
  selector: 'aluno-s-component',
  templateUrl: './aluno-s.component.html',
  styleUrls: ['./aluno-s.component.css']
})
export class AlunoSComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : number = -1;
  setClickedRow : Function;

  alunos : AlunoList[];

  constructor(private router: Router, private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    }

    secretariaService.getAlunos().subscribe(
      alunos => {
        this.alunos = alunos;
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
    this.router.navigate(['aluno-s-cadastro',1,0]);
  }

  goView(){
    this.router.navigate(['aluno-s-cadastro',2,this.alunos[this.selectedRow].id_aluno]);
  }

  goEdit(){
    this.router.navigate(['aluno-s-cadastro',3,this.alunos[this.selectedRow].id_aluno]);
  }

  goSearch(search: string){
    console.log('Buscando por:');
    console.log(search);
    if(search === ''){
      this.secretariaService.getAlunos().subscribe(
        alunos =>{
          this.alunos = alunos;
        }
      );
    }else{
      console.log('fui no certo');
      this.secretariaService.searchAlunos(search).subscribe(
        alunos =>{
          this.alunos = alunos;
        }
      );
    }
  }

  goDelete(){
    this.secretariaService.deleteAlunoById(this.alunos[this.selectedRow].id_aluno).subscribe(id => {
      console.log('This is my ID: '+id);
      this.secretariaService.getAlunos().subscribe(
        alunos => {
          this.alunos = alunos;
        }
      );
      console.log("Excluido com sucesso");

    },error => {
      console.log("Erro ao excluir");
    });
    this.router.navigate(['aluno-s']);
  }
}
