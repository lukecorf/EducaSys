import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./disciplina-s.model";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";
import {SecretariaService} from "../secretaria.service";
import {AlunoList} from "../aluno-s/aluno-s.model";
import {ProfessorList} from "../professor-s/professor-s.model";

@Component({
  selector: 'disciplina-cadastro-component',
  templateUrl: './disciplina-cadastro.component.html',
  styleUrls: ['./disciplina-cadastro.component.css']
})
export class DisciplinaCadastroComponent implements OnInit {

  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string = 'open';
  closed: string = 'content';
  selectedRow: number = -1;
  selectedRow2: number = -1;
  selectedRow3: number = -1;
  setClickedRow : Function;
  setClickedRow2 : Function;
  setClickedRow3 : Function;
  closeResult: string;
  disabledEdit: boolean = false;
  id: number;
  professorText: string = 'Selecione um professor...';
  title: string = 'Cadastrar';
  disciplina: Disciplina;
  alunos: AlunoList[];
  professores: ProfessorList[];

  constructor( private modalService: NgbModal,private router: Router, private route:ActivatedRoute, private secretariaService: SecretariaService ) {

    this.disciplina = new Disciplina();
    this.disciplina.ls_alunos = [];
    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar"
      this.getProfessores();
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.id = route.snapshot.params['id'];
      this.getDisciplina();
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.id = route.snapshot.params['id'];
      this.getDisciplina();
      this.getProfessores();
    }

    this.setClickedRow = function(index){
      this.selectedRow = index;
    }

    this.setClickedRow2 = function(index){
      this.selectedRow2 = index;
    }

    this.setClickedRow3 = function(index){
      this.selectedRow3 = index;
      this.professorText = this.professores[this.selectedRow3].st_nome_professor;
    }
  }

  getProfessores(){
    this.secretariaService.getProfessores().subscribe(
      professores => {
        this.professores = professores;
      }
    );
  }

  getDisciplina(){
    this.secretariaService.getDisciplinaById(this.id).subscribe(
      disciplina => {
        this.disciplina = disciplina;
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

  openM(content) {
    console.log("Entrei defora");
    this.secretariaService.getAlunos().subscribe(
      alunos => {
        console.log("ENTREI AQUI");
        this.alunos = alunos;
      }
    );
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      console.log(this.closeResult);
      if(result ==='Close click'){
        this.disciplina.ls_alunos.push(this.alunos[this.selectedRow2]);
      }
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      console.log(this.closeResult);
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  goBack(){
    this.router.navigate(['disciplina-s']);
  }

  goSave(){
    this.disciplina.id_professor = this.professores[this.selectedRow3].id_professor;
    this.disciplina.st_nome_prof = this.professores[this.selectedRow3].st_nome_professor;
    this.disciplina.url_img = "";
    this.disciplina.id_disciplina = 0;

    this.secretariaService.setDisciplina(this.disciplina).subscribe(disciplina => {
      if(disciplina.st_nome !== null){
        this.router.navigate(['disciplina-s']);
      }
    });
  }
}
