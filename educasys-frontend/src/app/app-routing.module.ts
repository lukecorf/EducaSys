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
import {HistoricoAComponent} from "./student/historico-a/historico-a.component";
import {PerfilAComponent} from "./student/perfil-a/perfil-a.component";
import {HomePComponent} from "./teacher/home-p/home-p.component";
import {DisciplinaPComponent} from "./teacher/disciplina-p/disciplina-p.component";

const appRoutes: Routes = [
  { path: '' , component: LoginComponent},
  { path: 'home-aluno/:id',canActivate: [AuthGuard] , component: HomeAComponent },
  { path: 'home-professor/:id',canActivate: [AuthGuard] , component: HomePComponent },
  { path: 'disciplina-a/:id',canActivate: [AuthGuard] , component: DisciplinaAComponent},
  { path: 'disciplina-p/:id',canActivate: [AuthGuard] , component: DisciplinaPComponent},
  { path: 'historico-a/:id', canActivate: [AuthGuard] , component: HistoricoAComponent},
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
