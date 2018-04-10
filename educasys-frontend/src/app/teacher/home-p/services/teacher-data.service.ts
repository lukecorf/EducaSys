import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/catch';
import {Materia} from "../../../student/home-a/models/materia.model";
import {Aluno} from "../../../student/home-a/models/aluno.model";

@Injectable()
export class TeacherDataService {

  private urlAluno   = 'http://localhost:8090/getAluno';
  private urlMateria = 'http://localhost:8090/getDisciplinas';
  constructor(private http: HttpClient){}

  getAlunoInfo():Observable<Aluno>{
    return this.http.get<Aluno>(this.urlAluno);
  }

  getDisciplinas():Observable<Materia[]>{
    return this.http.get<Materia[]>(this.urlMateria);
  }
}
