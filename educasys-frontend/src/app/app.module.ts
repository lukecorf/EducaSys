import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from './app-routing.module';
import { MatIconModule} from "@angular/material";
import { ChartsModule } from 'ng2-charts';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { InfoLoginComponent } from './login/info-login/info-login.component';
import { DataLoginComponent } from './login/data-login/data-login.component';
import { HomeAComponent } from './student/home-a/home-a.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { MenuAComponent } from './student/menu-a/menu-a.component';
import { DisciplinaPerfilAComponent } from './student/home-a/disciplina-perfil-a/disciplina-perfil-a.component';

import { LoginInfoService } from './login/info-login/info-login.service';
import { LoginService } from './login/data-login/data-login.service';
import { AuthService } from './auth.service';
import { UserDataService } from './student/home-a/services/user-data.service';
import {AuthGuard} from './auth-guard.service';
import {HttpClientModule} from "@angular/common/http";
import { DisciplinaAComponent } from './student/disciplina-a/disciplina-a.component';
import { HistoricoAComponent } from './student/historico-a/historico-a.component';
import { PerfilAComponent } from './student/perfil-a/perfil-a.component';
import { HomePComponent } from './teacher/home-p/home-p.component';
import { MenuPComponent } from './teacher/menu-p/menu-p.component';
import { DisciplinaPerfilPComponent } from './teacher/home-p/disciplina-perfil-p/disciplina-perfil-p.component';
import { DisciplinaPComponent } from './teacher/disciplina-p/disciplina-p.component';
import { TeacherDataService } from './teacher/home-p/services/teacher-data.service';
import {UserData} from "./services/userdata.service";
import { HomeSComponent } from './secretary/home-s/home-s.component';
import { DisciplinaSComponent } from './secretary/disciplina-s/disciplina-s.component';
import { MenuSComponent } from './secretary/menu-s/menu-s.component';
import { DisciplinaCadastroComponent } from './secretary/disciplina-s/disciplina-cadastro.component';
import { AlunoSComponent } from './secretary/aluno-s/aluno-s.component';
import { AlunoCadastroComponent } from './secretary/aluno-s/aluno-cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    InfoLoginComponent,
    DataLoginComponent,
    HomeAComponent,
    NotFoundComponent,
    MenuAComponent,
    DisciplinaPerfilAComponent,
    DisciplinaAComponent,
    HistoricoAComponent,
    PerfilAComponent,
    HomePComponent,
    MenuPComponent,
    DisciplinaPerfilPComponent,
    DisciplinaPComponent,
    HomeSComponent,
    DisciplinaSComponent,
    MenuSComponent,
    DisciplinaCadastroComponent,
    AlunoSComponent,
    AlunoCadastroComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    ChartsModule,
    NgbModule.forRoot()
  ],
  providers: [LoginService, TeacherDataService, UserDataService, LoginInfoService, AuthService, AuthGuard, UserData],
  bootstrap: [AppComponent]
})
export class AppModule { }
