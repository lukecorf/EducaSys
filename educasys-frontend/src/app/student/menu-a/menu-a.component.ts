import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserData} from "../../services/userdata.service";
import {Aluno} from "../student.model";
import {StudentService} from "../student.service";
import {AuthService} from "../../auth.service";

@Component({
  selector: 'menua-component',
  templateUrl: './menu-a.component.html',
  styleUrls: ['./menu-a.component.css']
})
export class MenuAComponent implements OnInit {
  code: string;
  aluno: Aluno = new Aluno(-1,"","","","","","","","","","","");
  cssType: string;
  constructor(private router: Router, private studentService: StudentService,private authService: AuthService) {
    this.code = UserData.getUserCode();
  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.cssType = 'sidebar-mobile fixed-sidebar';
    }else{
      this.cssType = 'sidebar fixed-sidebar';
    }

    this.studentService.getAlunoByCode(this.code).subscribe(
      aluno => {
        this.aluno = aluno;
      }
    );


  }

  goDisciplinas(){
    this.router.navigate(['disciplinas-a/'+this.aluno.id_aluno]);
  }

  goHome(){
    this.router.navigate(['home-aluno/'+this.code]);
  }

  goPerfil(){
    this.router.navigate(['perfil-a/'+this.code]);
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
