import { Injectable } from '@angular/core';
import {Login} from "../login/data-login/models/login.model";

@Injectable()
export class UserData {
    static login: Login= new Login();

    public  static setUser(login: Login){
      this.login = login;
    }

    public static getUserCode(){
      return this.login.nu_code;
    }

    public static getUserType(){
      return this.login.nu_type;
    }
}
