import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {SecretariaService} from "../secretaria.service";
import {Professor} from "./professor-s.model";
import {ToastrService} from "ngx-toastr";
import {BlockUI, NgBlockUI} from "ng-block-ui";

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
  type: number;
  password: string = '';

  constructor(private toastr: ToastrService,private router: Router, private route: ActivatedRoute, private secretariaService: SecretariaService) {
    this.professor = new Professor();
    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar";
      this.type = 1;
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.type = 2;
      this.id = route.snapshot.params['id'];
      this.getProfessor();
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.type = 3;
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

  goSave(form){

    if (!form.valid) {
      this.toastr.error("Preencha todos os campos obrigatorios");
      return;
    }

    if(this.type === 1) {
      if(this.password === this.professor.pw_senha_prof) {
        this.professor.url_img_professor = "http://www.rafacademy.com/wp-content/uploads/2017/03/user-default.png";
        this.secretariaService.setProfessor(this.professor).subscribe(professor => {
          if (professor.st_nome_professor !== null) {
            if(professor.id_professor === -1){
              this.toastr.error("Ja existe um professor com estes documentos","Erro!");
            }else{
              this.toastr.success("Professor cadastrado","Sucesso!");
              this.router.navigate(['professor-s']);
            }
          }else{
            this.toastr.error("Erro ao cadastrar professor","Erro!");
          }
        });
      }else{
        this.toastr.error("As senhas são diferentes","Erro!");
      }
    }else{
      this.secretariaService.updateProfessor(this.professor).subscribe(
        ok =>{
          this.toastr.success("Dados atualizados","Sucesso!");
          this.router.navigate(['professor-s']);
        }
      );
    }
  }

}
