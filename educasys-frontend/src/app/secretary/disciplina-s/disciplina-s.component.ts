import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {Router} from "@angular/router";
import {DisciplinaList} from "./disciplina-s.model";
import {SecretariaService} from "../secretaria.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'disciplina-s-component',
  templateUrl: './disciplina-s.component.html',
  styleUrls: ['./disciplina-s.component.css']
})
export class DisciplinaSComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : number = -1;
  setClickedRow : Function;

  disciplinas: DisciplinaList[];

  constructor(private toastr: ToastrService,private router: Router, private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    };
    this.blockUI.start("Carregando dados...");
    secretariaService.getDisciplinas().subscribe(
      disciplinas => {
        this.disciplinas = disciplinas;
        this.blockUI.stop();
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

  goSearch(search: string){
    this.blockUI.start("Carregando dados...");
    if(search === ''){
      this.secretariaService.getDisciplinas().subscribe(
        disciplinas =>{
          this.blockUI.stop();
          this.disciplinas = disciplinas;
        }
      );
    }else{
      this.secretariaService.searchDisciplinas(search).subscribe(
        disciplinas =>{
          this.blockUI.stop();
          this.disciplinas = disciplinas;
        }
      );
    }
  }

  goDelete(){
    this.blockUI.start("Deletando disciplina...");
    this.secretariaService.deleteDisciplinaById(this.disciplinas[this.selectedRow].nuId).subscribe(id => {
      this.selectedRow = -1;
      this.secretariaService.getDisciplinas().subscribe(
        disciplinas => {
          this.blockUI.stop();
          this.toastr.success("Disciplina deletada","Sucesso!");
          this.disciplinas = disciplinas;
        }
      );
    },error => {
      this.toastr.error("Erro ao deletar disciplina","Erro!");
    });
    this.router.navigate(['disciplina-s']);
  }

}
