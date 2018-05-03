package com.tcc.secretaria.mapper;

import com.tcc.secretaria.DTO.SecretariaDTO;
import com.tcc.secretaria.database.Secretaria;

public class SecretariaMapper {

    public static SecretariaDTO EntitytoDTO(Secretaria s){
        SecretariaDTO set = new SecretariaDTO();
        set.setId_secretaria(s.getCodigo());
        set.setPw_senha_secretaria(s.getPassword());
        set.setSt_code(s.getCode());
        set.setSt_nome_secretaria(s.getSecretaria());

        return set;
    }
}
