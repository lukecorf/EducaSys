import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Aluno, AtividadeEntrega, DisciplinaA} from "./student.model";
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
  private urlSAtividade= 'http://localhost:8888/setAtividade';
  constructor(private  http: HttpClient){}

  getAlunoByCode(id: string):Observable<Aluno>{
    return this.http.get<Aluno>(this.url+'/'+id);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlD+"/"+id);
  }

  getDisciplinas(id: number):Observable<DisciplinaA[]>{
    return this.http.get<DisciplinaA[]>(this.urlMateria+'/'+id);
  }

  setFileAtividade(a: AtividadeEntrega):Observable<AtividadeEntrega>{
    return this.http.post<AtividadeEntrega>(this.urlSAtividade, a);
  }

  getAtividadesByIdDisciplina(id: number, ida: number):Observable<Atividade[]>{
    return this.http.get<Atividade[]>(this.urlGAtividades+"/"+id+"/"+ida);
  }

  getArquivosByIdDisciplina(id: number):Observable<Arquivo[]>{
    return this.http.get<Arquivo[]>(this.urlGArquivos+"/"+id);
  }

  getFaltasByDisciplina(id:number, ida:number):Observable<number>{
    return this.http.get<number>(this.urlGFaltas+'/'+id+'/'+ida);
  }

}
