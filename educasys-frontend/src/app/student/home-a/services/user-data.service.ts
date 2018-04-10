import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import { Aluno } from'../models/aluno.model';
import 'rxjs/add/operator/catch';
import {Materia} from "../models/materia.model";

@Injectable()
export class UserDataService {

  private urlAluno   = 'http://localhost:8080/getAluno';
  private urlMateria = 'http://localhost:8888/getDisciplinas';
  constructor(private http: HttpClient){}

  getAluno():Observable<Aluno>{
    return this.http.get<Aluno>(this.urlAluno);
  }

  getDisciplinas():Observable<Materia[]>{
    return this.http.get<Materia[]>(this.urlMateria);
  }
}
