package br.com.ccee.fileupload.transformer.element;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "regiao")
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
public class RegiaoElement {

    @XmlAttribute(name = "sigla")
    private String sigla;

    @XmlElement(name = "geracao")
    private List<ValuesElement> geracao;

    @XmlElement(name = "compra")
    private List<ValuesElement> compra;

    @XmlElement(name = "precoMedio")
    private List<ValuesElement> precoMedio;
}
