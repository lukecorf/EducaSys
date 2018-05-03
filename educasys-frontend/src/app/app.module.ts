import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from './app-routing.module';
import { MatIconModule} from "@angular/material";
import { ChartsModule } from 'ng2-charts';
import { NgxMaskModule } from 'ngx-mask';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FirebaseConfig } from './../environments/firebase.config';
import { AngularFireModule } from 'angularfire2/index';
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
import {AuthGuard} from './auth-guard.service';
import {HttpClientModule} from "@angular/common/http";
import { DisciplinaAComponent } from './student/disciplina-a/disciplina-a.component';
import { HistoricoAComponent } from './student/historico-a/historico-a.component';
import { PerfilAComponent } from './student/perfil-a/perfil-a.component';
import { HomePComponent } from './teacher/home-p/home-p.component';
import { MenuPComponent } from './teacher/menu-p/menu-p.component';
import { DisciplinaPerfilPComponent } from './teacher/home-p/disciplina-perfil-p/disciplina-perfil-p.component';
import { DisciplinaPComponent } from './teacher/disciplina-p/disciplina-p.component';
import {UserData} from "./services/userdata.service";
import { HomeSComponent } from './secretary/home-s/home-s.component';
import { DisciplinaSComponent } from './secretary/disciplina-s/disciplina-s.component';
import { MenuSComponent } from './secretary/menu-s/menu-s.component';
import { DisciplinaCadastroComponent } from './secretary/disciplina-s/disciplina-cadastro.component';
import { AlunoSComponent } from './secretary/aluno-s/aluno-s.component';
import { AlunoCadastroComponent } from './secretary/aluno-s/aluno-cadastro.component';
import { ProfessorSComponent } from './secretary/professor-s/professor-s.component';
import { ProfessorCadastroComponent } from './secretary/professor-s/professor-cadastro.component';
import {SecretariaService} from "./secretary/secretaria.service";
import {AngularFireDatabase} from "angularfire2/database";
import {FirebaseService} from "./secretary/firebase.service";
import {StudentService} from "./student/student.service";
import {TeacherService} from "./teacher/teacher.service";

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
    AlunoCadastroComponent,
    ProfessorSComponent,
    ProfessorCadastroComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    ChartsModule,
    AngularFireModule.initializeApp(FirebaseConfig),
    NgxMaskModule.forRoot(),
    NgbModule.forRoot(),
 ],
  providers: [LoginService, StudentService, TeacherService, SecretariaService, LoginInfoService, AuthService, AuthGuard, UserData, AngularFireDatabase, FirebaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
