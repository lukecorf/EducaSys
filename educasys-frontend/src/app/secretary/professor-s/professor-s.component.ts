import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {Router} from "@angular/router";
import {ProfessorList} from "./professor-s.model";
import {SecretariaService} from "../secretaria.service";

@Component({
  selector: 'professor-s-component',
  templateUrl: './professor-s.component.html',
  styleUrls: ['./professor-s.component.css']
})
export class ProfessorSComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : number = -1;
  setClickedRow : Function;

  professores: ProfessorList[];

  constructor(private router: Router,private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    }

    secretariaService.getProfessores().subscribe(
      professores => {
        this.professores = professores;
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
    this.router.navigate(['professor-s-cadastro',1,0]);
  }

  goView(){
    this.router.navigate(['professor-s-cadastro',2,this.professores[this.selectedRow].id_professor]);
  }

  goEdit(){
    this.router.navigate(['professor-s-cadastro',3,this.professores[this.selectedRow].id_professor]);
  }

  goSearch(search: string){
    if(search === ''){
      this.secretariaService.getProfessores().subscribe(
        professores =>{
          this.professores= professores;
        }
      );
    }else{
      this.secretariaService.searchProfessores(search).subscribe(
        professores =>{
          this.professores= professores;
        }
      );
    }
  }

  goDelete(){
    this.secretariaService.deleteProfessorById(this.professores[this.selectedRow].id_professor).subscribe(id => {
      console.log('This is my ID: '+id);
      this.secretariaService.getProfessores().subscribe(
        professores => {
          this.professores = professores;
        }
      );
      console.log("Excluido com sucesso");

    },error => {
      console.log("Erro ao excluir");
    });
    this.router.navigate(['professor-s']);
  }

}
