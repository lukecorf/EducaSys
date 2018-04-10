import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import { Login } from'./models/login.model';
import 'rxjs/add/operator/catch';
import {Usuario} from "../../models/usuario.model";


@Injectable()
export class LoginService{

  private url = 'http://localhost:8080/login';

  constructor(private http: HttpClient){}

  loginQuery(login: Login):Observable<Usuario>{
      return this.http.post<Usuario>(this.url,JSON.stringify(login));
  }
}
