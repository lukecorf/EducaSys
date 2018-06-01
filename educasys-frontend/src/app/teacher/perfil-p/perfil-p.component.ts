import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TeacherService} from "../teacher.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ToastrService} from "ngx-toastr";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Subscription} from "rxjs/Subscription";
import {Professor} from "../../secretary/professor-s/professor-s.model";
import {Observable} from "rxjs/Observable";
import {Upload} from "../../secretary/secretaria.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {FirebaseConfig} from "../../../environments/firebase.config";

@Component({
  selector: 'app-perfil-p',
  templateUrl: './perfil-p.component.html',
  styleUrls: ['./perfil-p.component.css']
})
export class PerfilPComponent implements OnInit {


  @BlockUI() blockUI: NgBlockUI;
  open: boolean = true;
  private timer;
  private sub: Subscription;
  opened: string;
  closed: string;
  professor: Professor = new Professor;
  selectedFile: boolean = false;
  selectedFiles: FileList;
  code: string;
  password: string;

  constructor(private route:ActivatedRoute, private teacherService: TeacherService, private modalService: NgbModal, private router: Router, private toastr: ToastrService) {
    this.code = route.snapshot.params['id'];

    this.teacherService.getProfessorByCode(this.code).subscribe(professor =>{
      this.professor = professor;
      this.professor.url_img_professor = "";
      this.professor.pw_senha_prof = "";
    });
  }

  ngOnInit() {
    this.opened = 'open';
    this.closed = 'content';

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt() {
    this.open = !this.open;
  }

  selectFile(event){
    if(!this.selectedFile){
      this.selectedFile = true;
    }
    this.selectedFiles = event.target.files;
  }

  enviaArquivo(){
    this.blockUI.start("Realizando upload...");

    if(this.selectedFile) {
      let file = this.selectedFiles.item(0)
      let currentUpload = new Upload(file);
      this.efetuarUpload(currentUpload);
    }else{
      this.teacherService.updateProfessor(this.professor).subscribe(
        aluno=>{
          this.blockUI.stop();
          this.toastr.success("Professor atualizado","Sucesso!");
          this.router.navigate(['home-professor/'+this.code]);
        }
      );
    }
  }

  efetuarUpload(upload: Upload){
    if (firebase.apps.length === 0) {
      firebase.initializeApp(FirebaseConfig);
    }
    let storageRef = firebase.storage().ref();
    let uploadTask = storageRef.child(`${'/files'}/${upload.file.name}`).put(upload.file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) =>  {
        // upload em progresso
        upload.progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100
      },
      (error) => {
        // upload não efetuado
        console.log(error);
      },
      () => {
        //upload efetuado
        upload.url = uploadTask.snapshot.downloadURL
        upload.name = upload.file.name

        this.professor.url_img_professor = upload.url;

        this.teacherService.updateProfessor(this.professor).subscribe(
          aluno=>{
            this.blockUI.stop();
            this.toastr.success("Aluno atualizado","Sucesso!");
            this.router.navigate(['home-professor/'+this.code]);
          }
        );
      }
    );
  }

  openc(content) {
    this.modalService.open(content).result.then((result) => {
      if(result){
        if(this.professor.pw_senha_prof !== this.password){
          this.toastr.error("As senhas são diferentes","Erro!");
          this.professor.pw_senha_prof= "";
        }
      }else{
        this.professor.pw_senha_prof = "";
      }
    }, (reason) => {
      console.log('Dismissed');
    });
  }
}
