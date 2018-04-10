/**
 * Created by lukew on 09/04/2018.
 */
export class Usuario {
  constructor(
    public matricula: string,
    public nome: string,
    public periodoL: string,
    public dataN: string,
    public sexo: boolean,
    public email: string,
    public fone: string,
    public cpf: string,
    public RG: string,
    public tipo: number
  ){}
}
