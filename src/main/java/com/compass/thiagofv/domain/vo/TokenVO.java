package com.compass.thiagofv.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenVO implements Serializable {

    private String nomeUsuario;
    private Boolean atenticado;
    private Date criado;
    private Date expirado;
    private String tokenAcesso;
    private String tokenAtualizado;

    public TokenVO() {
    }

    public TokenVO(String nomeUsuario, Boolean atenticado, Date criado, Date expirado, String tokenAcesso, String tokenAtualizado) {
        this.nomeUsuario = nomeUsuario;
        this.atenticado = atenticado;
        this.criado = criado;
        this.expirado = expirado;
        this.tokenAcesso = tokenAcesso;
        this.tokenAtualizado = tokenAtualizado;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Boolean getAtenticado() {
        return atenticado;
    }

    public void setAtenticado(Boolean atenticado) {
        this.atenticado = atenticado;
    }

    public Date getCriado() {
        return criado;
    }

    public void setCriado(Date criado) {
        this.criado = criado;
    }

    public Date getExpirado() {
        return expirado;
    }

    public void setExpirado(Date expirado) {
        this.expirado = expirado;
    }

    public String getTokenAcesso() {
        return tokenAcesso;
    }

    public void setTokenAcesso(String tokenAcesso) {
        this.tokenAcesso = tokenAcesso;
    }

    public String getTokenAtualizado() {
        return tokenAtualizado;
    }

    public void setTokenAtualizado(String tokenAtualizado) {
        this.tokenAtualizado = tokenAtualizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVO tokenVO = (TokenVO) o;
        return Objects.equals(nomeUsuario, tokenVO.nomeUsuario) && Objects.equals(atenticado, tokenVO.atenticado) && Objects.equals(criado, tokenVO.criado) && Objects.equals(expirado, tokenVO.expirado) && Objects.equals(tokenAcesso, tokenVO.tokenAcesso) && Objects.equals(tokenAtualizado, tokenVO.tokenAtualizado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeUsuario, atenticado, criado, expirado, tokenAcesso, tokenAtualizado);
    }
}
