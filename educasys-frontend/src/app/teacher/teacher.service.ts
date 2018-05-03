import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Professor} from "../secretary/professor-s/professor-s.model";
import {Disciplina} from "../student/home-a/models/materia.model";
import {Arquivo, Atividade} from "./teacher.module";

@Injectable()
export class TeacherService{

  private url = 'http://localhost:8090/getProfessorByCode';
  private urlD = 'http://localhost:8090/getDisciplinaById';
  private urlMateria = 'http://localhost:8090/getDisciplinas';
  private urlSAtividade= 'http://localhost:8090/saveAtividade';
  private urlSArquivo= 'http://localhost:8090/saveArquivo';


  constructor(private  http: HttpClient){}

  getProfessorByCode(id: string):Observable<Professor>{
    return this.http.get<Professor>(this.url+'/'+id);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlD+"/"+id);
  }

  getDisciplinas(id: number):Observable<Disciplina[]>{
    return this.http.get<Disciplina[]>(this.urlMateria+'/'+id);
  }

  setAtividade(a:Atividade):Observable<Atividade>{
    return this.http.post(this.urlSAtividade,a);
  }

  setArquivo(a:Arquivo):Observable<Arquivo>{
    return this.http.post(this.urlSArquivo,a);
  }
}
