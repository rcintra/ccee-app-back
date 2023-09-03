package br.com.ccee.fileupload.repository;

import br.com.ccee.fileupload.enums.TipoValor;
import br.com.ccee.fileupload.model.Agente;
import br.com.ccee.fileupload.model.Arquivo;
import br.com.ccee.fileupload.model.Regiao;
import br.com.ccee.fileupload.model.TipoValores;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AgenteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AgenteRepository repository;

    private Agente agente;

    @Before
    public void setup() {
        byte[] data = "hello".getBytes();
        Arquivo arquivo = Arquivo.builder().nome("file_01.xml").data(data).build();
        Agente agente = Agente.builder()
                        .arquivo(arquivo)
                        .codigo(1)
                        .data(LocalDateTime.now())
                .build();
        Regiao regiao = Regiao.builder().sigla("S").agente(agente).build();
        TipoValores geracao = TipoValores.builder().tipo(TipoValor.GERACAO).regiao(regiao).valor(new BigDecimal("1.234")).build();
        regiao.setGeracao(Arrays.asList(geracao));
        agente.setRegioes(Arrays.asList(regiao));

        this.agente = agente;
    }

    @Test
    public void test_findAll() throws JAXBException, IOException {

        entityManager.persist(agente.getArquivo());
        entityManager.persist(agente);
        entityManager.flush();

        List<Agente> all = repository.findAll();

        assertThat(all != null);
    }

}
