package br.com.ccee.fileupload.service.impl;

import br.com.ccee.fileupload.model.Agente;
import br.com.ccee.fileupload.repository.AgenteRepository;
import br.com.ccee.fileupload.service.AgenteService;
import br.com.ccee.fileupload.service.ArquivoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AgenteServiceImpl implements AgenteService {

    private static final Logger logger = LoggerFactory.getLogger(AgenteServiceImpl.class);

    @Autowired
    private AgenteRepository repository;
    @Autowired
    private ArquivoService arquivoService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Transactional
    public void createAgentes(List<Agente> agentes) {
        transactionTemplate.execute( status -> {
            try {
                for (Agente agente : agentes) {
                    arquivoService.createArquivo(agente.getArquivo());
                    repository.save(agente);
                }
            } catch (DataAccessException e) {
                logger.error("Erro ao criar agentes: {}", e.getMessage(), e);
                throw e;
            }
            return null;
        });
    }
}
