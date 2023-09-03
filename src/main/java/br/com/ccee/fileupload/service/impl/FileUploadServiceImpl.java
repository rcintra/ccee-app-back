package br.com.ccee.fileupload.service.impl;

import br.com.ccee.fileupload.enums.TipoValor;
import br.com.ccee.fileupload.message.Consolidado;
import br.com.ccee.fileupload.repository.RegiaoRepository;
import br.com.ccee.fileupload.repository.TipoValoresRepository;
import br.com.ccee.fileupload.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FileUploadServiceImpl implements FileUploadService {

    private RegiaoRepository regiaoRepository;
    private TipoValoresRepository tipoValoresRepository;

    public FileUploadServiceImpl(RegiaoRepository regiaoRepository, TipoValoresRepository tipoValoresRepository) {
        this.regiaoRepository = regiaoRepository;
        this.tipoValoresRepository = tipoValoresRepository;
    }

    public List<Consolidado> consultarValoresConsolidados() {
        List<Consolidado> consolidados = new ArrayList<>();
        for (String regiao : regiaoRepository.findAllGroupBySigla()) {
            consolidados.add(Consolidado.builder()
                            .regiao(regiao)
                            .geracao(totalTipoValor(TipoValor.GERACAO, regiao))
                            .compra(totalTipoValor(TipoValor.COMPRA, regiao))
                    .build());
        }
        return consolidados;
    }

    private BigDecimal totalTipoValor(TipoValor tipoValor, String sigla) {
        return tipoValoresRepository.findByTipoAndRegiaoSigla(tipoValor, sigla)
                .stream()
                .map(v -> v.getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
