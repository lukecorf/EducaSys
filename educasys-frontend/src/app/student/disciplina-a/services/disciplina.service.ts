import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Materia} from "../../home-a/models/materia.model";
import {Aluno} from "../../home-a/models/aluno.model";
import {Observable} from "rxjs/Observable";

@Injectable()
export class DisciplinaService {

  private url = 'http://localhost:8080/getDisciplina';
  constructor(private http: HttpClient){}
/*
  getAluno():Observable<DisciplinaData>{
    return this.http.get<Aluno>(this.url);
  }

  getDisciplinas():Observable<Materia[]>{
    return this.http.get<Materia[]>(this.urlMateria);
  }
  */
}
