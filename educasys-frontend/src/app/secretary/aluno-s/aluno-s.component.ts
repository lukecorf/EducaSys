import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {SecretariaService} from "../secretaria.service";
import {Router} from "@angular/router";
import {AlunoList} from "./aluno-s.model";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'aluno-s-component',
  templateUrl: './aluno-s.component.html',
  styleUrls: ['./aluno-s.component.css']
})
export class AlunoSComponent implements OnInit {
  @BlockUI() blockUI: NgBlockUI;
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : number = -1;
  setClickedRow : Function;

  alunos : AlunoList[];

  constructor(private toastr: ToastrService,private router: Router, private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    }
    this.blockUI.start("Carregando dados...");
    secretariaService.getAlunos().subscribe(
      alunos => {
        this.blockUI.stop();
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
    this.blockUI.start("Buscando dados");
    if(search === ''){
      this.secretariaService.getAlunos().subscribe(
        alunos =>{
          this.blockUI.stop();
          this.alunos = alunos;
        }
      );
    }else{
      this.secretariaService.searchAlunos(search).subscribe(
        alunos =>{
          this.blockUI.stop();
          this.alunos = alunos;
        }
      );
    }
  }

  goDelete(){
    this.blockUI.start("Carregando dados...");
    this.secretariaService.deleteAlunoById(this.alunos[this.selectedRow].id_aluno).subscribe(id => {
      this.toastr.success("Aluno deletado","Sucesso!");
      this.secretariaService.getAlunos().subscribe(
        alunos => {
          this.blockUI.stop();
          this.alunos = alunos;
        }
      );
    },error => {
      this.toastr.error("Erro ao excluir","Erro!");
    });
    this.router.navigate(['aluno-s']);
  }
}
