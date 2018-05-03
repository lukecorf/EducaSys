export class Disciplina{
  constructor(public id_disciplina?: number,
              public st_nome?: string,
              public nu_carga_horaria?: number,
              public tx_descricao?: string,
              public st_nome_prof?: string,
              public url_img?: string,
              public nu_faltas?:number,
              public id_professor?: number,
  ){}
}

