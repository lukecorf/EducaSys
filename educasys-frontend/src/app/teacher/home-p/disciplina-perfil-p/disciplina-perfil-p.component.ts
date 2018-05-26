import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AlunoList} from "../../../secretary/aluno-s/aluno-s.model";
import {Arquivo, Atividade} from "../../teacher.module";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TeacherService} from "../../teacher.service";
import {ToastrService} from "ngx-toastr";
import {Upload} from "../../../secretary/secretaria.model";
import {FirebaseConfig} from "../../../../environments/firebase.config";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'disciplina-perfil-p',
  templateUrl: './disciplina-perfil-p.component.html',
  styleUrls: ['./disciplina-perfil-p.component.css']
})
export class DisciplinaPerfilPComponent implements OnInit {

  @Input() codigo: number;
  @Input() nome: string;
  @Input() status: string;
  @Input() professor:string;
  @Input() proxProva: string;
  @Input() img: string;
  @Input() falta: number;
  @Input() horas: number;

  @BlockUI() blockUI: NgBlockUI;

  alunos: AlunoList[];
  faltas: Boolean[];
  opc: number = -1;
  atividade: Atividade = new Atividade();
  closeResult: string;
  selectedFiles: FileList;

  constructor(private toastr: ToastrService, private router: Router, private modalService: NgbModal, private teacherService: TeacherService) {
    if (firebase.apps.length === 0) {
      firebase.initializeApp(FirebaseConfig);
    }
  }

  ngOnInit() {
  }

  goDisciplina(){
    this.router.navigate(['disciplina-p/'+this.codigo]);
  }

  setFaltas(){
    this.teacherService.getAlunosByIdDisciplina(this.codigo).subscribe(
      alunos=>{
        this.alunos = alunos;
        this.faltas = new Array(this.alunos.length);

        for(let x = 0; x < this.alunos.length; x++){
          this.faltas[x] = false;
        }
      }
    );
  }

  openModal(idModal){
    this.opc = idModal;
  }

  efetuaChamada(){
    let idFaltas : Array<number> = new Array<number>();
    for(let x = 0; x <  this.faltas.length; x++){
      if (this.faltas[x]){
        idFaltas.push(this.alunos[x].id_aluno);
      }
    }

    if(idFaltas.length > 0) {
      this.teacherService.setFaltas(idFaltas).subscribe(
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

  changeFalta(id: number){
    this.faltas[id] = !this.faltas[id];
  }

  criaAtividade(){
    this.atividade.id_diciplina= this.codigo;
    this.atividade.id_atividade = -1;
    this.teacherService.setAtividade(this.atividade).subscribe(
      atividade=>{
        if(atividade.st_nome_atividade !== null) {
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
    arquivo.id_disciplina = this.codigo;
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
            this.blockUI.stop();
            this.toastr.success("Arquivo enviado","Sucesso!");
          }
        );
      }
    );
  }

  openM(content) {

    if(this.opc === 1){
      this.setFaltas();
    }
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      if(result ==='Close click'){
        if(this.opc === 1){
          this.efetuaChamada();
        }else if(this.opc === 2){
          this.criaAtividade();
        }else if(this.opc === 3){
          this.enviaArquivo();
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
