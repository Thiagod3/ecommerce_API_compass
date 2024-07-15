package com.compass.thiagofv.domain.vo;

import java.io.Serializable;
import java.util.Objects;

public class CredencialVO implements Serializable {

    private String nomeUsuario;
    private String senha;

    public CredencialVO(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredencialVO credencialVO = (CredencialVO) o;
        return Objects.equals(nomeUsuario, credencialVO.nomeUsuario) && Objects.equals(senha, credencialVO.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeUsuario, senha);
    }
}
