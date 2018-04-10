import { Injectable } from '@angular/core';
import {Usuario} from "../models/usuario.model";

@Injectable()
export class UserData {
    static user: Usuario = new Usuario("err","err","","",false,"","","","",-1);

    public  static setUsuario(usuario: Usuario){
      this.user = usuario;
    }

    public static getUsuario(){
      return this.user;
    }
}
