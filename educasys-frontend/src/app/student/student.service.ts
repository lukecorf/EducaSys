import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Aluno} from "./student.model";
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./home-a/models/materia.model";

@Injectable()
export class StudentService{

  private url = 'http://localhost:8888/getAlunoByCode';
  private urlD = 'http://localhost:8888/getDisciplinaById';
  private urlMateria = 'http://localhost:8888/getDisciplinas';

  constructor(private  http: HttpClient){}

  getAlunoByCode(id: string):Observable<Aluno>{
    return this.http.get<Aluno>(this.url+'/'+id);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlD+"/"+id);
  }

  getDisciplinas(id: number):Observable<Disciplina[]>{
    return this.http.get<Disciplina[]>(this.urlMateria+'/'+id);
  }
}
