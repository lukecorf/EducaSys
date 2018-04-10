import { Component, OnInit } from '@angular/core';

import {DisciplinaStatus} from "./models/disciplinaStatus.model";
import {HistoricoData} from "./models/historicoData.model";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'historico-a',
  templateUrl: './historico-a.component.html',
  styleUrls: ['./historico-a.component.css']
})
export class HistoricoAComponent implements OnInit {
  private timer;
  private sub: Subscription;
  open: boolean = true;
  opened: string;
  closed: string;
  menu: string = "";
  periodo: string = "2017/2";
  periodoData: DisciplinaStatus[];
  historico: HistoricoData[];

  constructor() {

    this.historico = [
      new HistoricoData('2016/1', [
        new DisciplinaStatus('001', 'Redes 1', 'Completo', 'Everthon Valadão', '60', 71.5),
        new DisciplinaStatus('002', 'Compiladores', 'Completo', 'Walace Rodrigues', '60', 74.5),
        new DisciplinaStatus('003', 'Logica', 'Completo', 'Daniele Noronha', '60', 84.0),
        new DisciplinaStatus('004', 'IHC', 'Pendente', 'Mano Hell', '30', 12.5)
      ]),
      new HistoricoData('2016/2', [
        new DisciplinaStatus('005', 'AED', 'Completo', 'Diego Melo', '60', 22.5),
        new DisciplinaStatus('006', 'Teoria', 'Completo', 'Diego Melo', '60', 89.5),
        new DisciplinaStatus('007', 'Web Avançado', 'Completo', 'Bruno Ferreira', '30', 89.5),
        new DisciplinaStatus('008', 'LFA', 'Pendente', 'Walace Rodrigues', '60', 45.5)
      ]),
      new HistoricoData('2017/1', [
        new DisciplinaStatus('009', 'Programação 1', 'Completo', 'Herbert Richards', '60', 75.5),
        new DisciplinaStatus('010', 'IA', 'Completo', 'Diego Melo', '60', 67.5),
        new DisciplinaStatus('011', 'Calculo 2', 'Completo', 'Luzia', '60', 100.0),
        new DisciplinaStatus('012', 'Informatica em Sociedade', 'Completo', 'Renato Renan', '30', 99.5)
      ]),
      new HistoricoData('2017/2', [
        new DisciplinaStatus('013', 'GAAL', 'Pendente', 'Luzia', '60', 25.5),
        new DisciplinaStatus('014', 'Eletronica Digital', 'Completo', 'Otavio Menezes', '60', 66.0),
        new DisciplinaStatus('015', 'Sistemas Embarcados', 'Completo', 'Otavio Menezes', '60', 89.5),
        new DisciplinaStatus('016', 'Matematica Discreta', 'Completo', 'Diego Melo', '60', 87.9)
      ])
    ];
  }

  ngOnInit() {
    if (window.screen.width < 768) {
      this.opened = 'open-mobile';
      this.closed = 'content-mobile';
    } else {
      this.opened = 'open';
      this.closed = 'content';
    }

    for (var item of this.historico) {
      console.log(item.periodo);
      if (item.periodo == this.periodo) {
        console.log('aopa');
        this.periodoData = item.historico;
        break;
      }
    }

    this.timer = Observable.timer(500);
    this.sub = this.timer.subscribe(t => this.changeOpt());

  }

  changeOpt() {
    this.open = !this.open;
  }

  onClickMenu() {
    if (this.menu == "") {
      this.menu = "show-on-click";
    } else {
      this.menu = "";
    }
  }

  changePeriod(period: string) {
    this.periodo = period;
    console.log(this.periodo);

    for (var item of this.historico) {
      if (item.periodo == period) {
        this.periodoData = item.historico;
        break;
      }
    }

  }
}
