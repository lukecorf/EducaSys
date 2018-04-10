import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import {Info} from "./models/info.model";
import 'rxjs/add/operator/catch';



@Injectable()
export class LoginInfoService {

  private url   = 'http://localhost:8080/getLoginInfo';

  constructor(private http: HttpClient){}

  getInfo():Observable<Info[]>{
    return this.http.get<Info[]>(this.url);
  }

}
