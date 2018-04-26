import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Disciplina, DisciplinaList} from "./disciplina-s/disciplina-s.model";
import {Aluno, AlunoList} from "./aluno-s/aluno-s.model";
import {Professor, ProfessorList} from "./professor-s/professor-s.model";

@Injectable()
export class SecretariaService{

  private urlD = 'http://localhost:8099/getDisciplinas';
  private urlA = 'http://localhost:8099/getAlunos';
  private urlP = 'http://localhost:8099/getProfessores';
  private urlDById = 'http://localhost:8099/getDisciplinaById';
  private urlAById = 'http://localhost:8099/getAlunoById';
  private urlPById = 'http://localhost:8099/getProfessorById';
  private urlPSet = 'http://localhost:8099/saveProfessor';

  constructor(private http: HttpClient){}

  setProfessor(p:Professor):Observable<Professor>{
    return this.http.post(this.urlPSet,p);
  }

  getDisciplinas():Observable<DisciplinaList[]>{
    return this.http.get<DisciplinaList[]>(this.urlD);
  }

  getAlunos():Observable<AlunoList[]>{
    return this.http.get<AlunoList[]>(this.urlA);
  }

  getProfessores():Observable<ProfessorList[]>{
    return this.http.get<ProfessorList[]>(this.urlP);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlDById+'/'+id);
  }

  getAlunoById(id: number):Observable<Aluno>{
    return this.http.get<Aluno>(this.urlAById+'/'+id);
  }

  getProfessorById(id: number):Observable<Professor>{
    return this.http.get<Professor>(this.urlPById+'/'+id);
  }

}
