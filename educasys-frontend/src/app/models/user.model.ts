export class User {
  public name: string;
  public  age: number;
  public period: string;
  public code: string;
  public  father: string;
  public mother: string;
  public cpf: string;
  public rg: string;

  constructor(name: string, age: number, period: string, code: string, father: string, mother: string, cpf: string, rg: string) {
    this.name = name;
    this.age = age;
    this.period = period;
    this.code = code;
    this.father = father;
    this.mother = mother;
    this.cpf = cpf;
    this.rg = rg;

  }
}

