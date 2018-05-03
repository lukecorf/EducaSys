import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import {Login, LoginObject} from'./models/login.model';
import 'rxjs/add/operator/catch';

@Injectable()
export class LoginService{

  private url = 'http://localhost:8080/login';

  constructor(private http: HttpClient){}

  loginQuery(login: LoginObject):Observable<Login>{
      return this.http.post<Login>(this.url,JSON.stringify(login));
  }
}
