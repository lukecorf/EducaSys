import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Router} from "@angular/router";
import {SecretariaService} from "../secretaria.service";
import {Professor} from "./professor-s.model";

@Component({
  selector: 'professor-cadastro-component',
  templateUrl: './professor-cadastro.component.html',
  styleUrls: ['./professor-cadastro.component.css']
})
export class ProfessorCadastroComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  title: string;
  disabledEdit: boolean = false;
  id: number;
  professor: Professor;

  constructor(private router: Router, private route: ActivatedRoute, private secretariaService: SecretariaService) {
    this.professor = new Professor();

    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar"
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.id = route.snapshot.params['id'];
      this.getProfessor();
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.id = route.snapshot.params['id'];
      this.getProfessor();
    }
  }

  getProfessor(){
    this.secretariaService.getProfessorById(this.id).subscribe(
      professor => {
        this.professor = professor;
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

  goBack(){
    this.router.navigate(['professor-s']);
  }

  goSave(){
    this.professor.id_professor = 8;
    console.log(this.professor);
    this.secretariaService.setProfessor(this.professor);
  }
}
