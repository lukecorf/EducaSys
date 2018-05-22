import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Disciplina, DisciplinaList} from "./disciplina-s/disciplina-s.model";
import {Aluno, AlunoList} from "./aluno-s/aluno-s.model";
import {Professor, ProfessorList} from "./professor-s/professor-s.model";
import {Secretaria} from "./secretaria.model";


@Injectable()
export class SecretariaService{

  private urlD = 'http://localhost:8099/getDisciplinas';
  private urlDById = 'http://localhost:8099/getDisciplinaById';
  private urlDSet = 'http://localhost:8099/saveDisciplina';
  private urlDDelete = 'http://localhost:8099/deleteDisciplina';
  private urlDSearch = 'http://localhost:8099/searchDisciplinas';

  private urlA = 'http://localhost:8099/getAlunos';
  private urlAById = 'http://localhost:8099/getAlunoById';
  private urlASet = 'http://localhost:8099/saveAluno';
  private urlAUpdate = 'http://localhost:8099/updateAluno';
  private urlADelete = 'http://localhost:8099/deleteAluno';
  private urlASearch = 'http://localhost:8099/searchAlunos';

  private urlP = 'http://localhost:8099/getProfessores';
  private urlPById = 'http://localhost:8099/getProfessorById';
  private urlPSet = 'http://localhost:8099/saveProfessor';
  private urlPUpdate = 'http://localhost:8099/updateProfessor';
  private urlPDelete = 'http://localhost:8099/deleteProfessor';
  private urlPSearch = 'http://localhost:8099/searchProfessores';

  private urlMenu= 'http://localhost:8099/getSecretariaByCode';


  constructor(private http: HttpClient){}

  getDisciplinas():Observable<DisciplinaList[]>{
    return this.http.get<DisciplinaList[]>(this.urlD);
  }

  searchDisciplinas(name: string):Observable<DisciplinaList[]>{
    return this.http.get<DisciplinaList[]>(this.urlDSearch+'/'+name);
  }

  getDisciplinaById(id: number):Observable<Disciplina>{
    return this.http.get<Disciplina>(this.urlDById+'/'+id);
  }

  setDisciplina(d:Disciplina):Observable<Disciplina>{
    return this.http.post(this.urlDSet,d);
  }

  getAlunos():Observable<AlunoList[]>{
    return this.http.get<AlunoList[]>(this.urlA);
  }

  searchAlunos(name: string):Observable<AlunoList[]>{
    return this.http.get<AlunoList[]>(this.urlASearch+'/'+name);
  }

  getAlunoById(id: number):Observable<Aluno>{
    return this.http.get<Aluno>(this.urlAById+'/'+id);
  }

  setAluno(a:Aluno):Observable<Aluno>{
    return this.http.post(this.urlASet,a);
  }

  deleteDisciplinaById(id: number): Observable<any>{
    return this.http.delete(this.urlDDelete+'/'+id);
  }

  updateAluno(a:Aluno):Observable<Aluno>{
    return this.http.put(this.urlAUpdate,a);
  }

  deleteAlunoById(id: number): Observable<any>{
    return this.http.delete(this.urlADelete+'/'+id);
  }

  getProfessores():Observable<ProfessorList[]>{
    return this.http.get<ProfessorList[]>(this.urlP);
  }

  searchProfessores(name: string):Observable<ProfessorList[]>{
    return this.http.get<ProfessorList[]>(this.urlPSearch+'/'+name);
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

  getSecretariaByCode(id: string):Observable<Secretaria>{
    return this.http.get<Secretaria>(this.urlMenu+'/'+id);
  }

}
