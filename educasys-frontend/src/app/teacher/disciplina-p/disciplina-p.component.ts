import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute} from "@angular/router";
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { Observable, Subscription } from 'rxjs/Rx';
import {TeacherService} from "../teacher.service";
import {Disciplina} from "../../student/home-a/models/materia.model";
import {AluAtividade, Arquivo, Atividade, Notas} from "../teacher.module";
import {Upload} from "../../secretary/secretaria.model";
import {AlunoList} from "../../secretary/aluno-s/aluno-s.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {FirebaseConfig} from "../../../environments/firebase.config";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'disciplina-p-component',
  templateUrl: './disciplina-p.component.html',
  styleUrls: ['./disciplina-p.component.css']
})
export class DisciplinaPComponent implements OnInit {
  @BlockUI() blockUI: NgBlockUI;
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  id:number;
  opc: number = -1;
  disciplina: Disciplina = new Disciplina(-1,"",-1,"","","",-1,-1);
  atividade: Atividade = new Atividade();
  atividades: Atividade[];
  arquivos: Arquivo[];
  alunos: AlunoList[];
  faltas: Boolean[];
  entregas: AluAtividade[];
  notas:Notas[] = new Array();
  closeResult: string;
  selectedFiles: FileList;

  constructor(private toastr: ToastrService, private modalService: NgbModal, private route: ActivatedRoute, private teacherService: TeacherService) {
    this.id = this.route.snapshot.params['id'];
    if (firebase.apps.length === 0) {
      firebase.initializeApp(FirebaseConfig);
    }
  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    }else{
      this.opened = 'open';
      this.closed = 'content';
    }

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());

    this.teacherService.getDisciplinaById(this.id).subscribe(
      disciplina => {
        this.disciplina = disciplina;
      }
    );

    this.getAtividades();
    this.getArquivos();
  }

  getAtividades(){
    this.teacherService.getAtividadesByIdDisciplina(this.id).subscribe(
      atividades =>{
        this.atividades = atividades;
      }
    );
  }

  getArquivos(){
    this.teacherService.getArquivosByIdDisciplina(this.id).subscribe(
      arquivos =>{
        this.arquivos = arquivos;
      }
    );
  }

  changeOpt(){
    this.open = !this.open;
  }

  setAtividade(i: number, id: number){
    this.atividade = this.atividades[i];
    this.opc = id;
    if(id === 6){
      this.setFaltas();
    }
  }

  openModal(idModal){
    this.opc = idModal;
  }

  openUrl(url:string){
    window.open(url, "_blank");
  }

  deleteArquivo(id: number){
    this.teacherService.deleteFileById(id).subscribe(
      id=>{
        this.getArquivos();
        this.toastr.success("Arquivo deletado","Sucesso!");
      }
    );
  }

  deleteAtividade(id: number){
    this.teacherService.deleteAtividadeById(id).subscribe(
      atividade =>{
        this.getAtividades();
        this.toastr.success("Atividade deletada","Sucesso!");
      }
    )
  }

  changeFalta(id: number){
    this.faltas[id] = !this.faltas[id];
  }

  setNotas(){
    this.teacherService.getNotasAtividade(this.atividade.id_atividade).subscribe(
      notas => {
        this.notas = notas;
      }
    );

  }

  setFaltas(){
    this.teacherService.getAlunosByIdDisciplina(this.disciplina.id_disciplina).subscribe(
      alunos=>{
        this.alunos = alunos;
        this.faltas = new Array(this.alunos.length);

        for(let x = 0; x < this.alunos.length; x++){
          this.faltas[x] = false;
        }
      }
    );
  }

  efetuaChamada(){
    let idFaltas : Array<number> = new Array<number>();
    for(let x = 0; x <  this.faltas.length; x++){
      if (this.faltas[x]){
        idFaltas.push(this.alunos[x].id_aluno);
      }
    }

    if(idFaltas.length > 0) {
      this.teacherService.setFaltas(idFaltas,this.disciplina.id_disciplina).subscribe(
        ok => {
          if (ok) {
            this.toastr.success("Chamada realizada","Sucesso!");
          } else {
            this.toastr.error("Problemas ao efetuar chamada","Erro!");
          }
        }
      )
    }
  }

  criaAtividade(){
    this.atividade.id_diciplina= this.disciplina.id_disciplina;
    this.atividade.id_atividade = -1;
    this.teacherService.setAtividade(this.atividade).subscribe(
      atividade=>{
        if(atividade.st_nome_atividade !== null) {
          this.getAtividades();
          this.toastr.success("Atividade criada","Sucesso!")
        }
        this.atividade = new Atividade();
      }
    )
  }

  enviaArquivo(){
    let file = this.selectedFiles.item(0);
    let currentUpload = new Upload(file);
    let arquivo: Arquivo = new Arquivo();
    arquivo.id_disciplina = this.disciplina.id_disciplina;
    arquivo.id_arquivo = -1;
    arquivo.nu_tamanho_arquivo = file.size;
    arquivo.st_nome_arquivo = file.name;

    this.efetuarUpload(currentUpload,arquivo);
  }

  efetuarUpload(upload: Upload, arquivo: Arquivo){
    this.blockUI.start("Efetuando Upload...");
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
        upload.url = uploadTask.snapshot.downloadURL;
        upload.name = upload.file.name;
        arquivo.url_arquivo= upload.url;
        this.teacherService.setArquivo(arquivo).subscribe(
          arquivo=>{
            this.getArquivos();
            this.blockUI.stop();
            this.toastr.success("Arquivo enviado","Sucesso!");
          }
        );
      }
    );
  }

  getEntregas(){
    this.teacherService.getEntergas(this.atividade.id_atividade,this.atividade.id_diciplina).subscribe(
      entregas=>{
        this.entregas = entregas;
      }
    );
  }

  openM(content) {

    if(this.opc === 1){
      this.setFaltas();
    }else if(this.opc === 5){
      this.getEntregas();
    }

    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      if(result ==='Close click'){

        if(this.opc === 1){
          this.efetuaChamada();
        }
        if(this.opc === 2){
          this.criaAtividade();
        }else if(this.opc === 3){
          this.enviaArquivo();
        }else if(this.opc === 4){
          this.teacherService.updateAtividade(this.atividade).subscribe(
            atividade=>{
              this.getAtividades();
              this.toastr.success("Atividade atualizada","Sucesso!");
            }
          )
        }else if(this.opc === 6){
          this.teacherService.setNotasAtividade(this.notas).subscribe(notas=>{
            this.toastr.success("Notas atualizadas","Sucesso");
          })
        }
      }else{
        this.atividade = new Atividade();
      }
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;

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

  selectFile(event){
    this.selectedFiles = event.target.files;
  }
}
