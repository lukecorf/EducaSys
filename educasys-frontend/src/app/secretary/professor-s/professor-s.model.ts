export class ProfessorList{
  constructor(public id_professor: number,
              public st_nome_professor: string,
              public co_email: string,
              public co_telefone: string,
              public dc_cpf: string
  ){}
}

export class Professor{
  constructor(public id_professor?: number,
              public st_nome_professor?: string,
              public co_telefone?: string,
              public co_email?: string,
              public dc_cpf?: string,
              public dc_rg?: string,
              public st_endereco?: string,
              public pw_senha_prof?: string,
              public dt_data_nasc?: string,
              public url_img_professor?:string
  ){}
}
