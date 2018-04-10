import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {Info} from "./models/info.model";
import {LoginInfoService} from "./info-login.service";
@Component({
  selector: 'info-login-component',
  templateUrl: './info-login.component.html',
  styleUrls: ['./info-login.component.css']
})
export class InfoLoginComponent implements OnInit {

  infos: Info[];

  constructor(loginInfo: LoginInfoService) {
    loginInfo.getInfo().subscribe(
      info => {
        this.infos = info;
      }
    );
  }

  ngOnInit() {

  }

}
