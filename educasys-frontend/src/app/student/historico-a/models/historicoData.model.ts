import { DisciplinaStatus } from './disciplinaStatus.model';

export  class HistoricoData{
  constructor(public periodo: string,
              public historico: DisciplinaStatus[]) {}
}
