package com.tcc.aluno.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_LOGIN")
public class Login {

    @Id
    @Column(name = "ID_LOGIN")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "NU_CODE")
    @Size(max=11)
    private String code;

    @NotNull
    @Column(name = "ST_PASSWORD")
    private String password;

    @NotNull
    @Column(name = "NU_TYPE")
    private int type;

    public Login(@NotNull @Size(max = 11) String code, @NotNull String password, @NotNull int type) {
        this.code = code;
        this.password = password;
        this.type = type;
    }

    public Login(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
