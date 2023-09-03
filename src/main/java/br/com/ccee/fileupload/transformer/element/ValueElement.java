package br.com.ccee.fileupload.transformer.element;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ValueElement {
    @XmlValue
    public BigDecimal valor;
}
