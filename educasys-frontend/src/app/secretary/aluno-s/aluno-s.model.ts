export class AlunoList{
  constructor(public id_aluno: number,
              public st_nome_aluno: string,
              public co_email: string,
              public co_telefone: string,
  ){}
}

export class Aluno{
  constructor(public id_aluno?: number,
              public st_nome_aluno?: string,
              public co_telefone?: string,
              public co_email?: string,
              public st_nome_pai?: string,
              public st_nome_mae?: string,
              public st_endereco?: string,
              public pw_senha_aluno?: string,
              public dt_data_nasc?: Date,
              public dc_rg?: string,
              public dc_cpf?: string,
              public url_img_aluno?: string,
){}
}
