export class Upload {

  $key: string;
  file:File;
  name:string;
  url:string;
  progress:number;
  createdAt: Date = new Date();

  constructor(file:File) {
    this.file = file;
  }
}

export class Secretaria {
  constructor( public id_secretaria ?: number,
               public st_nome_secretaria ?:string,
               public st_code ?:string,
               public pw_senha_secretaria ?:string,
  ){}
}
