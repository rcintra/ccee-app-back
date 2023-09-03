package br.com.ccee.fileupload.transformer;

import br.com.ccee.fileupload.enums.TipoValor;
import br.com.ccee.fileupload.model.Agente;
import br.com.ccee.fileupload.model.Arquivo;
import br.com.ccee.fileupload.model.Regiao;
import br.com.ccee.fileupload.model.TipoValores;
import br.com.ccee.fileupload.transformer.element.*;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementToModelTransformer {

    private AgentesElement agentesElement;

    public ElementToModelTransformer(AgentesElement agentesElement) {
        this.agentesElement = agentesElement;
    }

    public List<Agente> doTransformer() {
        Assert.notNull(agentesElement, "'agentes' nao pode ser null");
        Assert.notNull(agentesElement.getAgentes(), "'agentes' nao pode ser null");
        List<Agente> entities = new ArrayList<>();

        Arquivo arquivo = Arquivo.builder()
                .nome(agentesElement.getNomeArquivo())
                .data(agentesElement.getData())
                .build();

        for (AgenteElement agenteE : agentesElement.getAgentes()) {
            Agente agente = Agente.builder().codigo(agenteE.getCodigo()).data(agenteE.getData()).arquivo(arquivo).build();
            agente.setRegioes(doTransformerRegiao(agenteE.getRegioes(), agente));
            entities.add(agente);
        }

        return entities;
    }

    private List<Regiao> doTransformerRegiao(List<RegiaoElement> regiaoElements, Agente agente) {
        List<Regiao> entities = null;
        if (regiaoElements != null) {
            entities = new ArrayList<>();
            for (RegiaoElement regEle : regiaoElements) {
                Regiao regiao = Regiao.builder().sigla(regEle.getSigla()).agente(agente).build();
                regiao.setGeracao(doTransformerTipoValor(regEle.getGeracao(), TipoValor.GERACAO, regiao));
                regiao.setCompra(doTransformerTipoValor(regEle.getCompra(), TipoValor.COMPRA, regiao));
                regiao.setPrecoMedio(doTransformerTipoValor(regEle.getPrecoMedio(), TipoValor.PRECOMEDIO, regiao));
                entities.add(regiao);
            }
        }
        return entities;
    }

    private List<TipoValores> doTransformerTipoValor(List<ValuesElement> valueElements, TipoValor tipo, Regiao regiao) {
        List<TipoValores> entities = null;
        if (valueElements != null) {
            for (ValuesElement valElets : valueElements) {
                if (valElets.getValores() != null) {
                    entities = new ArrayList<>();
                    for (ValueElement valEle : valElets.getValores()) {
                        entities.add(TipoValores.builder()
                                .tipo(tipo)
                                .regiao(regiao)
                                .valor(valEle.getValor())
                                .build());
                    }
                }
            }
        }
        return entities;
    }
}
