package br.com.ccee.fileupload.transformer.helper;

import br.com.ccee.fileupload.transformer.element.AgentesElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Jaxb2Helper {

    public static AgentesElement unMarshaling(File file) throws JAXBException, IOException {
        InputStream inputStream = new FileInputStream(file);
        return unMarshaling(inputStream);
    }

    public static AgentesElement unMarshaling(InputStream xmlFile) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AgentesElement.class);
        Unmarshaller jaxbUnmarshalled = jaxbContext.createUnmarshaller();

        AgentesElement agentesElement = (AgentesElement) jaxbUnmarshalled.unmarshal(xmlFile);
        agentesElement.setData(xmlFile.readAllBytes());

        return agentesElement;
    }

}
