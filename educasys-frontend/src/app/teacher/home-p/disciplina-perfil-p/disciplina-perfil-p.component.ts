import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'disciplina-perfil-p',
  templateUrl: './disciplina-perfil-p.component.html',
  styleUrls: ['./disciplina-perfil-p.component.css']
})
export class DisciplinaPerfilPComponent implements OnInit {

  @Input() codigo: string;
  @Input() nome: string;
  @Input() status: string;
  @Input() professor:string;
  @Input() proxProva: string;
  @Input() img: string;
  @Input() falta: number;
  @Input() horas: number;


  constructor(private router: Router) { }

  ngOnInit() {
  }

  goDisciplina(){
    this.router.navigate(['disciplina-p/'+this.codigo]);
  }

}
