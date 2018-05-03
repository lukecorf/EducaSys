package com.tcc.index;

import com.tcc.index.DTO.LoginDTO;
import com.tcc.index.database.Login;

public class LoginMapper {
    public static LoginDTO EntitytoDTO(Login l){
        LoginDTO log = new LoginDTO();
        log.setId_login(l.getId());
        log.setNu_code(l.getCode());
        log.setNu_type(l.getType());

        return log;
    }
}
