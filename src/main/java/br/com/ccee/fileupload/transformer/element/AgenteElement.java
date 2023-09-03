package br.com.ccee.fileupload.transformer.element;

import br.com.ccee.fileupload.transformer.adapter.LocalDateTimeAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement(name = "agente")
@XmlAccessorType(XmlAccessType.NONE)
@Getter
@Setter
public class AgenteElement {

    @XmlElement(name = "codigo")
    private Integer codigo;

    @XmlElement(name = "data")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime data;

    @XmlElement(name = "regiao")
    private List<RegiaoElement> regioes;

}
