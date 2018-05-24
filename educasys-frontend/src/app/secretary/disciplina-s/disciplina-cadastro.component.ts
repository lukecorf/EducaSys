import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./disciplina-s.model";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";
import {SecretariaService} from "../secretaria.service";
import {AlunoList} from "../aluno-s/aluno-s.model";
import {Professor, ProfessorList} from "../professor-s/professor-s.model";
import {Upload} from "../secretaria.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {FirebaseConfig} from "../../../environments/firebase.config";

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
  professorText: string = 'Professor';
  title: string = 'Cadastrar';
  disciplina: Disciplina;
  alunos: AlunoList[];
  professores: ProfessorList[];
  selectedFiles: FileList;
  currentUpload: Upload;
  type: number = 0;
  selectedFile: boolean = false;

  constructor( private modalService: NgbModal,private router: Router, private route:ActivatedRoute, private secretariaService: SecretariaService ) {
    if (firebase.apps.length === 0) {
      firebase.initializeApp(FirebaseConfig);
    }
    this.disciplina = new Disciplina();
    this.disciplina.ls_alunos = [];
    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar";
      this.type = 1;
      this.getProfessores();
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.type = 2;
      this.id = route.snapshot.params['id'];
      this.getDisciplina();
      this.getProfessores()
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.type = 3;
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

  getDisciplina(){
    this.secretariaService.getDisciplinaById(this.id).subscribe(
      disciplina => {
        this.disciplina = disciplina;
      }
    );
  }

  getProfessores(){
    this.secretariaService.getProfessores().subscribe(
      professores => {
        this.professores = professores;
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
    this.secretariaService.getAlunos().subscribe(
      alunos => {
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

  goSave() {

    if (this.type === 1) {
      let file = this.selectedFiles.item(0)
      this.currentUpload = new Upload(file);
      this.disciplina.id_professor = this.professores[this.selectedRow3].id_professor;
      this.disciplina.st_nome_prof = this.professores[this.selectedRow3].st_nome_professor;
      this.disciplina.id_disciplina = 0;
      this.imagemUpload(this.currentUpload, this.disciplina);
    }else if (this.type === 3){
      if(this.selectedFile){
        let file = this.selectedFiles.item(0)
        this.currentUpload = new Upload(file);
        this.disciplina.id_professor = this.professores[this.selectedRow3].id_professor;
        this.disciplina.st_nome_prof = this.professores[this.selectedRow3].st_nome_professor;
        this.imagemUpload(this.currentUpload, this.disciplina);
      }else{
        this.disciplina.id_professor = this.professores[this.selectedRow3].id_professor;
        this.disciplina.st_nome_prof = this.professores[this.selectedRow3].st_nome_professor;
        this.secretariaService.updateDisciplina(this.disciplina).subscribe(ok =>{
          if(ok){
            this.router.navigate(['disciplina-s']);
          }
        });
      }
    }
  }

  imagemUpload(upload: Upload, disciplina: Disciplina){
    let storageRef = firebase.storage().ref();
    let uploadTask = storageRef.child(`${'/imagens'}/${upload.file.name}`).put(upload.file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) =>  {
        //efetuando upload
        upload.progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100
      },
      (error) => {
        console.log("Erro ao efetuar upload!");
        // upload failed
        console.log(error);
      },
      () => {
        console.log("Upload Efetuado!")
        // upload success
        upload.url = uploadTask.snapshot.downloadURL
        upload.name = upload.file.name
        disciplina.url_img = upload.url;
        if(this.type === 1) {
          this.secretariaService.setDisciplina(disciplina).subscribe(disciplina => {
            if (disciplina.st_nome !== null) {
              this.router.navigate(['disciplina-s']);
            }
          });
        }else if (this.type === 3){
          this.secretariaService.updateDisciplina(disciplina).subscribe(ok =>{
            if(ok){
              this.router.navigate(['disciplina-s']);
            }
          });
        }
      }
    );
  }

  selectFile(event){
    if(!this.selectedFile){
      this.selectedFile = true;
    }
    this.selectedFiles = event.target.files;
  }
}
