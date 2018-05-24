import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {Router} from "@angular/router";
import {ProfessorList} from "./professor-s.model";
import {SecretariaService} from "../secretaria.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'professor-s-component',
  templateUrl: './professor-s.component.html',
  styleUrls: ['./professor-s.component.css']
})
export class ProfessorSComponent implements OnInit {
  @BlockUI() blockUI: NgBlockUI;
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow : number = -1;
  setClickedRow : Function;

  professores: ProfessorList[];

  constructor(private toastr: ToastrService,private router: Router,private secretariaService: SecretariaService) {
    this.setClickedRow = function(index){
      this.selectedRow = index;
    }
    this.blockUI.start("Carregando dados...");
    secretariaService.getProfessores().subscribe(
      professores => {
        this.professores = professores;
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
    this.router.navigate(['professor-s-cadastro',1,0]);
  }

  goView(){
    this.router.navigate(['professor-s-cadastro',2,this.professores[this.selectedRow].id_professor]);
  }

  goEdit(){
    this.router.navigate(['professor-s-cadastro',3,this.professores[this.selectedRow].id_professor]);
  }

  goSearch(search: string){
    this.blockUI.start("Buscando dados...");
    if(search === ''){
      this.secretariaService.getProfessores().subscribe(
        professores =>{
          this.blockUI.stop();
          this.professores= professores;
        }
      );
    }else{
      this.secretariaService.searchProfessores(search).subscribe(
        professores =>{
          this.blockUI.stop();
          this.professores= professores;
        }
      );
    }
  }

  goDelete(){
    this.blockUI.start("Carregando dados...");
    this.secretariaService.deleteProfessorById(this.professores[this.selectedRow].id_professor).subscribe(id => {
      this.toastr.success("Professor deletado","Sucesso!");
      this.secretariaService.getProfessores().subscribe(
        professores => {
          this.blockUI.stop();
          this.professores = professores;
        }
      );
    },error => {
      this.toastr.error("Erro ao deletar professor","Erro!");
    });
    this.router.navigate(['professor-s']);
  }

}
