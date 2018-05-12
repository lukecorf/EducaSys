
export class Atividade{
  id_atividade : number;
  dt_data : Date;
  st_nome_atividade : string;
  bo_tipo_atividade : boolean;
  nu_valor_atividade : number;
  id_diciplina : number;

  constructor(id_atividade ?: number,
              dt_data ?: Date,
              st_nome_atividade ?: string,
              bo_tipo_atividade ?: boolean,
              nu_valor_atividade ?: number,
              id_diciplina ?: number,
  ){}
}

export class Arquivo{
  id_arquivo: number;
  url_arquivo: string;
  st_nome_arquivo: string;
  nu_tamanho_arquivo: number;
  id_disciplina: number;

  constructor(id_arquivo?: number,
              url_arquivo?: string,
              st_nome_arquivo?: string,
              nu_tamanho_arquivo?: number,
              id_disciplina?: number){

  }
}

export class AluAtividade{
  nome: string;
  url: string;

  constructor(nome?: string, url?:string){}
}
