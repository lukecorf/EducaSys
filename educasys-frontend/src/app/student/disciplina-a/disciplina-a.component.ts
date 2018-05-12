import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {ModalModel} from "./models/modal.model";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {StudentService} from "../student.service";
import {Disciplina} from "../home-a/models/materia.model";
import {Arquivo, Atividade} from "../../teacher/teacher.module";
import {Upload} from "../../secretary/secretaria.model";
import {FirebaseService} from "../../secretary/firebase.service";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {AtividadeEntrega} from "../student.model";

@Component({
  selector: 'disciplina-a',
  templateUrl: './disciplina-a.component.html',
  styleUrls: ['./disciplina-a.component.css']
})
export class DisciplinaAComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  id: number;
  ida:number;
  disciplina: Disciplina = new Disciplina(-1,"",-1,"","","",-1,-1);
  atividades: Atividade[];
  arquivos: Arquivo[];
  atividade: Atividade;
  selectedFiles: FileList;
  faltas: number;

  public pieChartLabels:string[] = ['Faltas', 'Restante'];
  public pieChartData:number[] = [300,500];
  public pieChartType:string = 'pie';
  public pieChartOptions: any = {responsive: true, maintainAspectRatio: false}

  constructor(private modalService: NgbModal, private route: ActivatedRoute, private studentService: StudentService, private firebaseService: FirebaseService) {
    this.id = this.route.snapshot.params['id'];
    this.ida = this.route.snapshot.params['ida'];
  }

  ngOnInit() {
    if(window.screen.width < 768){
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    }else{
      this.opened = 'open';
      this.closed = 'content';
    }

    console.log("ID: "+this.id);

    this.studentService.getDisciplinaById(this.id).subscribe(
      disciplina =>{
        this.disciplina = disciplina;
        this.pieChartData = [this.faltas,(this.disciplina.nu_carga_horaria-this.faltas)];
      }

    );

    this.studentService.getFaltasByDisciplina(this.ida,this.id).subscribe(
      faltas =>{
        this.faltas = faltas;
        console.log('Faltas: '+this.faltas);
        this.pieChartData = [this.faltas,(this.disciplina.nu_carga_horaria-this.faltas)];
      }
    );

    this.getAtividades();
    this.getArquivos();
    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt(){
    this.open = !this.open;
  }

  setAtividade(id: number){
    this.atividade = this.atividades[id];
    //console.log('ID: '+this.atividade.id_atividade)
  }

  openUrl(url:string){
    window.open(url, "_blank");
  }

  getAtividades(){
    this.studentService.getAtividadesByIdDisciplina(this.id).subscribe(
      atividades=>{
        this.atividades = atividades;
      }
    )
  }

  getArquivos(){
    this.studentService.getArquivosByIdDisciplina(this.id).subscribe(
      arquivos=>{
        this.arquivos = arquivos;
      }
    )
  }

  selectFile(event){
    this.selectedFiles = event.target.files;
  }

  enviaArquivo(){
    let file = this.selectedFiles.item(0)
    let currentUpload = new Upload(file);
    this.efetuarUpload(currentUpload);
  }

  efetuarUpload(upload: Upload){
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

        let a :AtividadeEntrega = new AtividadeEntrega(this.ida,this.atividade.id_atividade,upload.url);

        this.studentService.setFileAtividade(a).subscribe(
          arquivo=>{
            console.log('Deu');
          }
        );
      }
    );
  }

  openc(content) {
    this.modalService.open(content).result.then((result) => {
      if(result){
        console.log('Everything OK');
        this.enviaArquivo();
      }else{
        console.log('Problems here man');
      }
    }, (reason) => {
      console.log('Dismissed');
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
}
