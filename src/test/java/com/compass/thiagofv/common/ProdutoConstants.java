package com.compass.thiagofv.common;

import com.compass.thiagofv.domain.Produto;

public class ProdutoConstants {
    public static final Produto PRODUTO = new Produto("testador", 500D, 50);
    public static final Produto PRODUTO_INVALIDO = new Produto("", -5D, 0);
}
