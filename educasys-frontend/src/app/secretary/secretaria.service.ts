import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Disciplina, DisciplinaList} from "./disciplina-s/disciplina-s.model";
import {Aluno, AlunoList} from "./aluno-s/aluno-s.model";
import {Professor, ProfessorList} from "./professor-s/professor-s.model";

@Injectable()
export class SecretariaService{

  private urlD = 'http://localhost:8099/getDisciplinas';
  private urlDById = 'http://localhost:8099/getDisciplinaById';
  private urlDSet = 'http://localhost:8099/saveDisciplina';

  private urlA = 'http://localhost:8099/getAlunos';
  private urlAById = 'http://localhost:8099/getAlunoById';
  private urlASet = 'http://localhost:8099/saveAluno';
  private urlAUpdate = 'http://localhost:8099/updateAluno';
  private urlADelete = 'http://localhost:8099/deleteAluno';

  private urlP = 'http://localhost:8099/getProfessores';
  private urlPById = 'http://localhost:8099/getProfessorById';
  private urlPSet = 'http://localhost:8099/saveProfessor';
  private urlPUpdate = 'http://localhost:8099/updateProfessor';
  private urlPDelete = 'http://localhost:8099/deleteProfessor';

  constructor(private http: HttpClient){}

  getDisciplinas():Observable<DisciplinaList[]>{
    return this.http.get<DisciplinaList[]>(this.urlD);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlDById+'/'+id);
  }

  setDisciplina(d:Disciplina):Observable<Disciplina>{
    console.log("Enviei a disciplina");
    return this.http.post(this.urlDSet,d);
  }

  getAlunos():Observable<AlunoList[]>{
    return this.http.get<AlunoList[]>(this.urlA);
  }

  getAlunoById(id: number):Observable<Aluno>{
    return this.http.get<Aluno>(this.urlAById+'/'+id);
  }

  setAluno(a:Aluno):Observable<Aluno>{
    return this.http.post(this.urlASet,a);
  }

  updateAluno(p:Professor):Observable<Professor>{
    return this.http.post(this.urlAUpdate,p);
  }

  deleteAlunoById(id: number): Observable<any>{
    return this.http.delete(this.urlADelete+'/'+id);
  }

  getProfessores():Observable<ProfessorList[]>{
    return this.http.get<ProfessorList[]>(this.urlP);
  }

  getProfessorById(id: number):Observable<Professor>{
    return this.http.get<Professor>(this.urlPById+'/'+id);
  }

  setProfessor(p:Professor):Observable<Professor>{
    return this.http.post(this.urlPSet,p);
  }

  updateProfessor(p:Professor):Observable<Professor>{
    return this.http.post(this.urlPUpdate,p);
  }

  deleteProfessorById(id: number): Observable<any>{
    return this.http.delete(this.urlPDelete+'/'+id);
  }

}
