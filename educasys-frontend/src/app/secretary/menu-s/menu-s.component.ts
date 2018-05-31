import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserData} from "../../services/userdata.service";
import {SecretariaService} from "../secretaria.service";
import {Secretaria} from "../secretaria.model";
import {AuthService} from "../../auth.service";

@Component({
  selector: 'menu-s-component',
  templateUrl: './menu-s.component.html',
  styleUrls: ['./menu-s.component.css']
})
export class MenuSComponent implements OnInit {

  code: string;
  secretaria: Secretaria = new Secretaria(0,"","","");
  cssType: string;
  constructor(private router: Router, private secretariaService: SecretariaService, private authService: AuthService) {
    this.code = UserData.getUserCode();
  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.cssType = 'sidebar-mobile fixed-sidebar';
    }else{
      this.cssType = 'sidebar fixed-sidebar';
    }

    this.secretariaService.getSecretariaByCode(this.code).subscribe(
      secretaria =>{
        this.secretaria = secretaria;
      }
    );

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

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
