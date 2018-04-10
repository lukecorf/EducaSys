import { Component, OnInit } from '@angular/core';
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ActivatedRoute} from "@angular/router";
import {ModalModel} from "../../student/disciplina-a/models/modal.model";
import { Observable, Subscription } from 'rxjs/Rx';

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

  constructor(private modalService: NgbModal, private route: ActivatedRoute) { }

  ngOnInit() {
    if(window.screen.width < 768){
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    }else{
      this.opened = 'open';
      this.closed = 'content';
    }

    console.log('Codigo da Disciplina: '+ this.route.snapshot.params['id']);
    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());
  }

  changeOpt(){
    this.open = !this.open;
  }

  openc(content) {
    this.modalService.open(content).result.then((result) => {
      if(result){
        console.log('Everything OK');
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
