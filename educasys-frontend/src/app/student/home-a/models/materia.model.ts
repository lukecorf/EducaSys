export class Materia{
  constructor(public codigo: string,
              public nome: string,
              public status: string,
              public professor:string,
              public proxProva: string,
              public img: string,
              public falta: number,
              public nota: number) {}
}
