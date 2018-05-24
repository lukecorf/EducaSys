import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import { LoginService } from './data-login.service';
import {Login, LoginObject} from './models/login.model';
import {NgForm} from "@angular/forms";
import {AuthService} from "../../auth.service";
import {UserData} from "../../services/userdata.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'data-login-component',
  templateUrl: './data-login.component.html',
  styleUrls: ['./data-login.component.css']
})
export class DataLoginComponent implements OnInit {
  cssClass: string;
  login: Boolean = false;

  constructor(private toastr: ToastrService,private loginService: LoginService, private router: Router, private authService: AuthService) {

  }

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

  verifyLogin(login: Login){

    if(login.id_login !== -1){
      this.toastr.success("Login efetuado","Sucesso!");
      this.authService.login();
      UserData.setUser(login);
      if(login.nu_type === 1) {
        this.router.navigate(['home-aluno/' + login.nu_code]);
      }else if(login.nu_type === 2){
        this.router.navigate(['home-professor/' + login.nu_code]);
      }else if(login.nu_type === 3){
        this.router.navigate(['home-secretaria/' + login.nu_code]);
      }else{
        this.toastr.error("Tipo de usuario invalido","Erro!");
      }
    }else{
      this.toastr.error("Login ou senha inválidos","Erro!")
    }

  }

  onSingin(form: NgForm){

    if( (form.value.code !== '') && (form.value.password !== '')){
      this.loginService.loginQuery(new LoginObject(form.value.code, form.value.password)).
      subscribe(
        result => this.verifyLogin(result)
       );
    }else{
      this.toastr.error("Campos não preenchidos","Erro!");
    }
  }

}
