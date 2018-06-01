import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../student.service";
import {Aluno} from "../../secretary/aluno-s/aluno-s.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Upload} from "../../secretary/secretaria.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {FirebaseConfig} from "../../../environments/firebase.config";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'perfil-a',
  templateUrl: './perfil-a.component.html',
  styleUrls: ['./perfil-a.component.css']
})
export class PerfilAComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  open: boolean = true;
  private timer;
  private sub: Subscription;
  opened: string;
  closed: string;
  aluno: Aluno = new Aluno;
  selectedFile: boolean = false;
  selectedFiles: FileList;
  code: string;
  password: string;

  constructor(private route:ActivatedRoute, private studentService: StudentService, private modalService: NgbModal, private router: Router, private toastr: ToastrService) {
    this.code = route.snapshot.params['id'];
    this.studentService.getAlunoByCode(this.code).subscribe(aluno => {
      this.aluno = aluno;
      this.aluno.url_img_aluno = "";
      this.aluno.pw_senha_aluno = "";
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
      this.studentService.updateAluno(this.aluno).subscribe(
        aluno=>{
          this.blockUI.stop();
          this.toastr.success("Aluno atualizado","Sucesso!");
          this.router.navigate(['home-aluno/'+this.code]);
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

        this.aluno.url_img_aluno = upload.url;

        this.studentService.updateAluno(this.aluno).subscribe(
          aluno=>{
            this.blockUI.stop();
            this.toastr.success("Aluno atualizado","Sucesso!");
            this.router.navigate(['home-aluno/'+this.code]);
          }
        );
      }
    );
  }

  openc(content) {
    this.modalService.open(content).result.then((result) => {
      if(result){
        if(this.aluno.pw_senha_aluno !== this.password){
          this.toastr.error("As senhas são diferentes","Erro!");
          this.aluno.pw_senha_aluno = "";
        }
      }else{
        this.aluno.pw_senha_aluno = "";
      }
    }, (reason) => {
      console.log('Dismissed');
    });
  }
}
