package br.com.ccee.fileupload.service;

import br.com.ccee.fileupload.model.Arquivo;
import java.util.List;

public interface ArquivoService {

    public Arquivo createArquivo(Arquivo arquivo);
    public List<Arquivo> findAll();
}
