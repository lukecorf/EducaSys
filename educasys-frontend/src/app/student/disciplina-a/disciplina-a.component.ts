import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {StudentService} from "../student.service";
import {Disciplina} from "../home-a/models/materia.model";
import {Arquivo, Atividade} from "../../teacher/teacher.module";
import {Upload} from "../../secretary/secretaria.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {AtividadeEntrega} from "../student.model";
import {FirebaseConfig} from "../../../environments/firebase.config";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'disciplina-a',
  templateUrl: './disciplina-a.component.html',
  styleUrls: ['./disciplina-a.component.css']
})
export class DisciplinaAComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
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

  constructor(private toastr: ToastrService, private modalService: NgbModal, private route: ActivatedRoute, private studentService: StudentService) {
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

    this.studentService.getDisciplinaById(this.id).subscribe(
      disciplina =>{
        this.disciplina = disciplina;
        this.pieChartData = [this.faltas,(this.disciplina.nu_carga_horaria-this.faltas)];
      }

    );

    this.studentService.getFaltasByDisciplina(this.ida,this.id).subscribe(
      faltas =>{
        this.faltas = faltas;
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
  }

  openUrl(url:string){
    window.open(url, "_blank");
  }

  getAtividades(){
    this.studentService.getAtividadesByIdDisciplina(this.id,this.ida).subscribe(
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
    this.blockUI.start("Realizando upload...");
    let file = this.selectedFiles.item(0)
    let currentUpload = new Upload(file);
    this.efetuarUpload(currentUpload);
  }

  efetuarUpload(upload: Upload){
    firebase.initializeApp(FirebaseConfig);
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
            this.getAtividades();
            this.blockUI.stop();
            this.toastr.success("Arquivo enviado","Sucesso!");
          }
        );
      }
    );
  }

  openc(content) {
    this.modalService.open(content).result.then((result) => {
      if(result){
        this.enviaArquivo();
      }else{
        console.log('Problems here man');
      }
    }, (reason) => {
      console.log('Dismissed');
    });
  }

}
