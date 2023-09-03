package br.com.ccee.fileupload.transformer.element;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "valor")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ValuesElement {

    @XmlElement(name = "valor")
    private List<ValueElement> valores;

}
