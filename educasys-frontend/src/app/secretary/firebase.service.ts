import {Injectable} from "@angular/core";
import {AngularFireDatabase, FirebaseListObservable} from "angularfire2/database";
import {Upload} from "./secretaria.model";
import * as firebase from 'firebase/app';
import 'firebase/storage';
import {Observable} from "rxjs/Observable";
import {Disciplina} from "./disciplina-s/disciplina-s.model";
import {SecretariaService} from "./secretaria.service";
import {Router} from "@angular/router";
import {Arquivo} from "../teacher/teacher.module";
import {TeacherService} from "../teacher/teacher.service";
import {DisciplinaPComponent} from "../teacher/disciplina-p/disciplina-p.component";

@Injectable()
export class FirebaseService{
  private baseImagePath:string = '/images';

  constructor(private db: AngularFireDatabase, private secretariaService: SecretariaService, private teacherService: TeacherService, private router : Router){}

  pushUpload(upload: Upload, disciplina: Disciplina){
    let storageRef = firebase.storage().ref();
    let uploadTask = storageRef.child(`${this.baseImagePath}/${upload.file.name}`).put(upload.file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) =>  {
        console.log("Efetuando Upload...");
        // upload in progress
        upload.progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100
      },
      (error) => {
        console.log("Erro ao efetuar upload!");
        // upload failed
        console.log(error);
      },
      () => {
        console.log("Upload Efetuado!")
        // upload success
        upload.url = uploadTask.snapshot.downloadURL
        upload.name = upload.file.name
        disciplina.url_img = upload.url;
        this.secretariaService.setDisciplina(disciplina).subscribe(disciplina => {
          if(disciplina.st_nome !== null){
            this.router.navigate(['disciplina-s']);
          }
        });

      }
    );
  }



  pushUploadFile(upload: Upload, arquivo: Arquivo){
    let storageRef = firebase.storage().ref();
    let uploadTask = storageRef.child(`${this.baseImagePath}/${upload.file.name}`).put(upload.file);

    uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED,
      (snapshot) =>  {
        console.log("Efetuando Upload...");
        // upload in progress
        upload.progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100
      },
      (error) => {
        console.log("Erro ao efetuar upload!");
        // upload failed
        console.log(error);

      },
      () => {
        console.log("Upload Efetuado!")
        // upload success
        upload.url = uploadTask.snapshot.downloadURL
        upload.name = upload.file.name
        arquivo.url_arquivo= upload.url;
        console.log("Entrei aqui 1");

      }
    );
  }





  // Writes the file details to the realtime db
  private saveFileData(upload: Upload) {
    this.db.list(`${this.baseImagePath}/`).push(upload);
  }

  deleteUpload(upload: Upload) {
    this.deleteFileData(upload.$key)
      .then( () => {
        this.deleteFileStorage(upload.name)
      })
      .catch(error => console.log(error))
  }

  // Deletes the file details from the realtime db
  private deleteFileData(key: string) {
    return this.db.list(`${this.baseImagePath}/`).remove(key);
  }

  // Firebase files must have unique names in their respective storage dir
  // So the name serves as a unique key
  private deleteFileStorage(name:string) {
    let storageRef = firebase.storage().ref();
    storageRef.child(`${this.baseImagePath}/${name}`).delete()
  }
}
