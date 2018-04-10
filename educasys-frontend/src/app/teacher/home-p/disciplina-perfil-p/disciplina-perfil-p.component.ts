import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'disciplina-perfil-p',
  templateUrl: './disciplina-perfil-p.component.html',
  styleUrls: ['./disciplina-perfil-p.component.css']
})
export class DisciplinaPerfilPComponent implements OnInit {

  @Input() codigo: string = '001';
  @Input() nome: string ='Redes';
  @Input() status: string = 'Em andamento';
  @Input() professor:string = 'Bruno Ferreira';
  @Input() proxProva: string = '26/12';
  @Input() img: string = 'http://helpdigitalti.com.br/wp-content/uploads/2017/02/rede1.jpg';
  @Input() falta: number = 25;
  @Input() nota: number = 22;
  @Input() horas: number = 60;


  constructor(private router: Router) { }

  ngOnInit() {
  }

  goDisciplina(){
    this.router.navigate(['disciplina-p/'+this.codigo]);
  }

}
