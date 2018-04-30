import {AlunoList} from "../aluno-s/aluno-s.model";

export class DisciplinaList{
    constructor(public nuId: number,
                public stNomeD: string,
                public stNomeP: string,
                public nuCargaH: number,
                public stPeriodo: string
    ){}
}

export class Disciplina{
  constructor(public id_disciplina?: number,
              public st_nome?: string,
              public nu_carga_horaria?: number,
              public tx_descricao?: string,
              public st_nome_prof?: string,
              public url_img?: string,
              public ls_alunos?: Array<AlunoList>,
              public id_professor?: number,
  ){}
}
