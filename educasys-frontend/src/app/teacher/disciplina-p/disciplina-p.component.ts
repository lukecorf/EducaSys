import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute} from "@angular/router";
import { Observable, Subscription } from 'rxjs/Rx';
import {TeacherService} from "../teacher.service";
import {Disciplina} from "../../student/home-a/models/materia.model";
import {Arquivo, Atividade} from "../teacher.module";
import {Upload} from "../../secretary/secretaria.model";
import {FirebaseService} from "../../secretary/firebase.service";

@Component({
  selector: 'disciplina-p-component',
  templateUrl: './disciplina-p.component.html',
  styleUrls: ['./disciplina-p.component.css']
})
export class DisciplinaPComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  id:number;
  opc: number = -1;
  disciplina: Disciplina = new Disciplina(-1,"",-1,"","","",-1,-1);
  atividade: Atividade = new Atividade();
  closeResult: string;
  selectedFiles: FileList;
  currentUpload: Upload;

  constructor(private modalService: NgbModal, private route: ActivatedRoute, private teacherService: TeacherService, private firebaseService: FirebaseService) {
    this.id = this.route.snapshot.params['id'];
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
  }

  changeOpt(){
    this.open = !this.open;
  }

  openModal(idModal){
    this.opc = idModal;
  }

  openM(content) {

    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      if(result ==='Close click'){
        if(this.opc === 2){
          this.atividade.id_diciplina= this.disciplina.id_disciplina;
          this.atividade.id_atividade = -1;
          this.teacherService.setAtividade(this.atividade).subscribe(
            atividade=>{

            }
          )
        }else if(this.opc === 3){
          let file = this.selectedFiles.item(0)
          this.currentUpload = new Upload(file);
          let arquivo: Arquivo = new Arquivo();
          arquivo.id_disciplina = this.disciplina.id_disciplina;
          arquivo.id_arquivo = -1;
          arquivo.nu_tamanho_arquivo = file.size;
          arquivo.st_nome_arquivo = file.name;
          this.firebaseService.pushUploadFile(this.currentUpload,arquivo);
        }
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

  selectFile(event){
    this.selectedFiles = event.target.files;
  }
}
