package com.compass.thiagofv.domain;

//Variaveis
import static com.compass.thiagofv.common.ProdutoConstants.PRODUTO;
import static com.compass.thiagofv.common.ProdutoConstants.PRODUTO_INVALIDO;

import com.compass.thiagofv.common.ProdutoConstants;
import com.compass.thiagofv.repositories.ProdutoRepository;
import com.compass.thiagofv.services.ProdutoService;
import com.compass.thiagofv.services.VendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private VendaService vendaService;

    @Test
    public void createProduto_WithValidData_ReturnsProduto(){
        when(produtoRepository.save(PRODUTO)).thenReturn(PRODUTO);

        Produto sut = produtoService.create(PRODUTO);

        assertThat(sut).isEqualTo(PRODUTO);
    }

    @Test
    public void createProduto_WithInvalidData_ThrowsException() {
        when(produtoRepository.save(PRODUTO_INVALIDO)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> produtoService.create(PRODUTO_INVALIDO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void listProdutos_ReturnsAllProdutos() {
        List<Produto> produtos = new ArrayList<>() {
            {
                add(PRODUTO);
            }
        };
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> sut = produtoService.getAll();

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(PRODUTO);
    }
}
