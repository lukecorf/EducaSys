import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserData} from "../../services/userdata.service";

@Component({
  selector: 'menu-s-component',
  templateUrl: './menu-s.component.html',
  styleUrls: ['./menu-s.component.css']
})
export class MenuSComponent implements OnInit {

  name: string;
  code: string;

  cssType: string;
  constructor(private router: Router) {
    this.name = UserData.getUsuario().nome;
    this.code = UserData.getUsuario().matricula;
  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.cssType = 'sidebar-mobile fixed-sidebar';
    }else{
      this.cssType = 'sidebar fixed-sidebar';
    }

  }

  goDisciplinas(){
    this.router.navigate(['disciplina-s']);
  }

  goHome(){
    this.router.navigate(['home-secretaria/'+this.code]);
  }

  goProfessor(){
    this.router.navigate(['professor-s']);
  }

  goAluno(){
    this.router.navigate(['aluno-s']);
  }
}
