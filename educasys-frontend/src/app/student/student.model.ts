
export class Aluno{
  constructor(public id_aluno?: number,
              public st_nome_aluno?: string,
              public st_nome_pai?: string,
              public st_nome_mae?: string,
              public st_endereco?: string,
              public co_email?: string,
              public co_telefone?: string,
              public dt_data_nasc?: string,
              public dc_cpf?: string,
              public dc_rg?: string,
              public url_img_aluno?: string,
              public pw_senha_aluno?: string
  ){}
}

export class AtividadeEntrega{
  constructor(public id_aluno?:number,
              public id_atividade?:number,
              public url?:string
  ){}
}

export class DisciplinaA{
  constructor(public id_disciplina?: number,
              public st_nome?: string,
              public id_professor?: number,
              public st_nome_prof?: string,
              public url_img?: string,
              public nu_faltas?: string,
              public dt_next_prova?: Date,
              public nu_nota?:number,
              public id_aluno?: number,
              public nu_carga_horaria?: number,

  ){}
}
