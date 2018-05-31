import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Router} from "@angular/router";
import {SecretariaService} from "../secretaria.service";
import {Aluno} from "./aluno-s.model";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'aluno-cadastro-component',
  templateUrl: './aluno-cadastro.component.html',
  styleUrls: ['./aluno-cadastro.component.css']
})
export class AlunoCadastroComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  disabledEdit: boolean = false;
  title: string;
  id: number;
  type: number;
  aluno: Aluno;
  password: string = '';

  constructor(private toastr: ToastrService, private router: Router, private route:ActivatedRoute, private secretariaService: SecretariaService) {
    this.aluno = new Aluno();
    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar";
      this.type = 1;
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.type = 2;
      this.id = route.snapshot.params['id'];
      this.getAluno();
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.type = 3;
      this.id = route.snapshot.params['id'];
      this.getAluno();
    }
  }

  getAluno(){
    this.secretariaService.getAlunoById(this.id).subscribe(
      aluno => {
        this.aluno = aluno;
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
    this.router.navigate(['aluno-s']);
  }

  goSave(form) {

    if (!form.valid) {
      this.toastr.error("Preencha todos os campos obrigatorios");
      return;
    }

    if(this.type === 1) {
      if(this.aluno.pw_senha_aluno === this.password) {
        this.aluno.url_img_aluno = "http://www.rafacademy.com/wp-content/uploads/2017/03/user-default.png";
        this.secretariaService.setAluno(this.aluno).subscribe(aluno => {
          if (aluno.st_nome_aluno !== null) {
            if(aluno.id_aluno === -1){
              this.toastr.error("Ja existe um aluno com estes documentos","Erro!");
            }else {
              this.toastr.success("Aluno cadastrado", "Sucesso!");
              this.router.navigate(['aluno-s']);
            }
          }else{
            this.toastr.error("Erro ao cadastrar aluno","Erro!");
          }
        });
      }else{
        this.toastr.error("As senhas são diferentes","Erro!");
      }
    }else{
      this.secretariaService.updateAluno(this.aluno).subscribe(ok =>{
        this.toastr.success("Dados atualizados","Sucesso!");
        this.router.navigate(['aluno-s']);
      })
    }
  }

}
