package com.compass.thiagofv.utils;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.exceptions.InvalidValueException;

public class ProdutoUtils {

    public static void validateProdutoData(Produto prod) {
        if (prod.getEstoque() < 0) {
            throw new InvalidValueException("O estoque do produto não pode ser negativo");
        }

        if (prod.getPreco() < 0) {
            throw new InvalidValueException("O preço do produto não pode ser negativo");
        }

        if (prod.getNome() == null || prod.getNome().isEmpty()) {
            throw new InvalidValueException("O nome do produto é obrigatório");
        }

    }
}
