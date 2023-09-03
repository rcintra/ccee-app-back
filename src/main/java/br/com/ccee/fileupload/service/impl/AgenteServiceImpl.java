package br.com.ccee.fileupload.service.impl;

import br.com.ccee.fileupload.model.Agente;
import br.com.ccee.fileupload.repository.AgenteRepository;
import br.com.ccee.fileupload.service.AgenteService;
import br.com.ccee.fileupload.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgenteServiceImpl implements AgenteService {
    private AgenteRepository repository;

    private ArquivoService arquivoService;


    public AgenteServiceImpl(AgenteRepository repository, ArquivoService arquivoService) {
        this.repository     = repository;
        this.arquivoService = arquivoService;
    }

    @Transactional
    public void createAgentes(List<Agente> agentes) {
        for (Agente agente : agentes) {
            arquivoService.createArquivo(agente.getArquivo());
            repository.save(agente);
        }
    }
}
