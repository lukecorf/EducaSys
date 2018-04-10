import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  cssClass='col-md-3 data-area';
  cssClass2: string;

  constructor() { }

  onResize(event) {
      if(event.target.innerWidth < 992 && event.target.innerWidth >= 768){
        this.cssClass='col-md-12 data-area-tablet';
        this.cssClass2='vertical-center';
      }else if(event.target.innerWidth < 768){
        this.cssClass='col-md-12 data-area-mobile';
        this.cssClass2='vertical-center-mobile';
      }else{
        this.cssClass='col-3 data-area';
        this.cssClass2='vertical-center';
      }
  }

  ngOnInit() {
    if(window.screen.width < 992 && window.screen.width >= 768){
      this.cssClass='col-md-12 data-area-tablet';
      this.cssClass2='vertical-center';
    }else if(window.screen.width < 768){
      this.cssClass='col-md-12 data-area-mobile';
      this.cssClass2='vertical-center-mobile';
    }else{
      this.cssClass='col-md-3 data-area';
      this.cssClass2='vertical-center';
    }
  }

}
