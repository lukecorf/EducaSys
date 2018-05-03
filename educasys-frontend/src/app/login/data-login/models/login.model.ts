export class Login {
  public id_login?: number;
  public nu_type?: number;
  public nu_code?: string;

  constructor() {}
}

export class LoginObject{
  public id: string;
  public password: string;

  constructor(id: string, password: string){
    this.id = id;
    this.password = password;
  }

}
