import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Aluno} from "./student.model";
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./home-a/models/materia.model";
import {Arquivo, Atividade} from "../teacher/teacher.module";

@Injectable()
export class StudentService{

  private url = 'http://localhost:8888/getAlunoByCode';
  private urlD = 'http://localhost:8888/getDisciplinaById';
  private urlMateria = 'http://localhost:8888/getDisciplinas';
  private urlGAtividades = 'http://localhost:8888/getAtividades';
  private urlGArquivos= 'http://localhost:8888/getArquivos';
  private urlGFaltas= 'http://localhost:8888/getFaltas';
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

  getAtividadesByIdDisciplina(id: number):Observable<Atividade[]>{
    return this.http.get<Atividade[]>(this.urlGAtividades+"/"+id);
  }

  getArquivosByIdDisciplina(id: number):Observable<Arquivo[]>{
    return this.http.get<Arquivo[]>(this.urlGArquivos+"/"+id);
  }

  getFaltasByDisciplina(id:number, ida:number):Observable<number>{
    return this.http.get<number>(this.urlGFaltas+'/'+id+'/'+ida);
  }

}
