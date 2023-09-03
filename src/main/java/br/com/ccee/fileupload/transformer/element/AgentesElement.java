package br.com.ccee.fileupload.transformer.element;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "agentes")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class AgentesElement {

    @XmlElement(name = "agente")
    private List<AgenteElement> agentes;

    private String nomeArquivo;

    private byte[] data;

}
