package br.com.ccee.fileupload.service.impl;

import br.com.ccee.fileupload.model.Arquivo;
import br.com.ccee.fileupload.repository.ArquivoRepository;
import br.com.ccee.fileupload.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArquivoServiceImpl implements ArquivoService {

    private ArquivoRepository repository;

    public ArquivoServiceImpl(ArquivoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Arquivo createArquivo(Arquivo arquivo) {
        return repository.save(arquivo);
    }

    public List<Arquivo> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

}
