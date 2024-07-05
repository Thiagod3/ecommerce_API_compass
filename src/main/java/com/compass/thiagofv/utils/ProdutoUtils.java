package com.compass.thiagofv.utils;

import com.compass.thiagofv.domain.Produto;
import com.compass.thiagofv.exceptions.InvalidPriceException;
import com.compass.thiagofv.exceptions.InvalidProductException;
import com.compass.thiagofv.exceptions.InvalidStockException;

public class ProdutoUtils {

    public static void validateProdutoData(Produto prod) {
        if (prod.getEstoque() < 0) {
            throw new InvalidStockException("O estoque do produto não pode ser negativo");
        }

        if (prod.getPreco() < 0) {
            throw new InvalidPriceException("O preço do produto não pode ser negativo");
        }

        if (prod.getNome() == null || prod.getNome().isEmpty()) {
            throw new InvalidProductException("O nome do produto é obrigatório");
        }

    }
}
