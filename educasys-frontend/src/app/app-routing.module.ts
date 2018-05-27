/*
*  Este modulo é responsavel por armazenar as possiveis rotas do Website. Tais rotas podem possuir configurações nas
*  quais impossibilita o acesso daquela rota caso o usuario nao tenha efetuado o login por exemplo.
* */

import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";

//==Components==========================================================================================================

import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth-guard.service";
import {HomeAComponent} from "./student/home-a/home-a.component";
import { NotFoundComponent } from './not-found/not-found.component';
import {DisciplinaAComponent} from "./student/disciplina-a/disciplina-a.component";
import {PerfilAComponent} from "./student/perfil-a/perfil-a.component";
import {HomePComponent} from "./teacher/home-p/home-p.component";
import {DisciplinaPComponent} from "./teacher/disciplina-p/disciplina-p.component";
import {DisciplinaSComponent} from "./secretary/disciplina-s/disciplina-s.component";
import {DisciplinaCadastroComponent} from "./secretary/disciplina-s/disciplina-cadastro.component";
import {AlunoCadastroComponent} from "./secretary/aluno-s/aluno-cadastro.component";
import {ProfessorCadastroComponent} from "./secretary/professor-s/professor-cadastro.component";
import {AlunoSComponent} from "./secretary/aluno-s/aluno-s.component";
import {ProfessorSComponent} from "./secretary/professor-s/professor-s.component";
import {HomeSComponent} from "./secretary/home-s/home-s.component";
import {DisciplinasAComponent} from "./student/historico-a/disciplinas-a.component";


const appRoutes: Routes = [
  { path: '' , component: LoginComponent},
  { path: 'home-aluno/:id',canActivate: [AuthGuard] , component: HomeAComponent },
  { path: 'home-professor/:id',canActivate: [AuthGuard] , component: HomePComponent },
  { path: 'home-secretaria/:id',canActivate: [AuthGuard] , component: HomeSComponent },
  { path: 'disciplina-a/:id/:ida',canActivate: [AuthGuard] , component: DisciplinaAComponent},
  { path: 'disciplina-p/:id',canActivate: [AuthGuard] , component: DisciplinaPComponent},
  { path: 'disciplina-s', canActivate: [AuthGuard] , component: DisciplinaSComponent},
  { path: 'aluno-s', canActivate: [AuthGuard] , component: AlunoSComponent},
  { path: 'professor-s', canActivate: [AuthGuard] , component: ProfessorSComponent},
  { path: 'disciplina-s-cadastro/:type/:id', canActivate: [AuthGuard] , component: DisciplinaCadastroComponent},
  { path: 'aluno-s-cadastro/:type/:id', canActivate: [AuthGuard] , component: AlunoCadastroComponent},
  { path: 'professor-s-cadastro/:type/:id', canActivate: [AuthGuard] , component: ProfessorCadastroComponent},
  { path: 'disciplinas-a/:id', canActivate: [AuthGuard] , component: DisciplinasAComponent},
  { path: 'perfil-a/:id', canActivate: [AuthGuard] , component: PerfilAComponent},

  { path: 'not-found' , component: NotFoundComponent },
  { path: '**', redirectTo: '/not-found'}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  //Exporta as rotas que existem nesse modulo para que outros modulos possam usa-las
  exports: [RouterModule]
})

export class  AppRoutingModule{

}
