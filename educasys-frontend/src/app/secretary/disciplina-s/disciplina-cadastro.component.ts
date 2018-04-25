import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./disciplina-s.model";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute, Router} from "@angular/router";
import {SecretariaService} from "../secretaria.service";

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
  setClickedRow : Function;
  closeResult: string;
  disabledEdit: boolean = false;
  id: number;
  title: string = 'Cadastrar';
  disciplina: Disciplina;

  constructor( private modalService: NgbModal,private router: Router, private route:ActivatedRoute, private secretariaService: SecretariaService ) {

    this.disciplina = new Disciplina();

    if(route.snapshot.params['type'] == 1){
      this.title = "Cadastrar"
    }else if(route.snapshot.params['type'] == 2){
      this.disabledEdit = true;
      this.title= 'Visualizar';
      this.id = route.snapshot.params['id'];
      this.getDisciplina();
    }else if(route.snapshot.params['type'] == 3){
      this.title= 'Editar';
      this.id = route.snapshot.params['id'];
      this.getDisciplina();
    }

    this.setClickedRow = function(index){
      this.selectedRow = index;
    }

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
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      console.log(this.closeResult);
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

}
