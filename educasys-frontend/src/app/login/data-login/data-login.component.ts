import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import { LoginService } from './data-login.service';
import { Login } from './models/login.model';
import {NgForm} from "@angular/forms";
import {AuthService} from "../../auth.service";
import {UserData} from "../../services/userdata.service";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'data-login-component',
  templateUrl: './data-login.component.html',
  styleUrls: ['./data-login.component.css']
})
export class DataLoginComponent implements OnInit {
  cssClass: string;
  login: Boolean = false;

  constructor(private loginService: LoginService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    if(window.screen.width < 992 && window.screen.width >= 768){
      this.cssClass='col-md-12 data-login-tablet';
    }else if(window.screen.width < 768){
      this.cssClass='col-md-12 data-login-mobile';
    }else{
      this.cssClass='col-md-12 data-login';
    }
  }

  onResize(event) {
    if(event.target.innerWidth < 992 && event.target.innerWidth >= 768){
      this.cssClass='col-md-12 data-login-tablet';
    }else if(event.target.innerWidth < 768){
      this.cssClass='col-md-12 data-login-mobile';
    }else{
      this.cssClass='col-md-12 data-login';
    }
  }

  verifyLogin(user: Usuario){

    if(user.nome !== 'ERROR-LOGIN'){
      this.authService.login();
      UserData.setUsuario(user);
      if(user.tipo === 0) {
        this.router.navigate(['home-aluno/' + user.matricula]);
      }else if(user.tipo === 1){
        this.router.navigate(['home-professor/' + user.matricula]);
      }else if(user.tipo === 2){
        this.router.navigate(['home-secretaria/' + user.matricula]);
      }else{

      }
    }else{
      console.log('ERROR');
    }


  }

  onSingin(form: NgForm){

    if( (form.value.code !== '') && (form.value.password !== '')){
      this.loginService.loginQuery(new Login(form.value.code, form.value.password)).
      subscribe(
        result => this.verifyLogin(result)
       );
    }
  }

}
