package br.com.ccee.fileupload.transformer;

import br.com.ccee.fileupload.transformer.element.AgentesElement;
import br.com.ccee.fileupload.transformer.helper.Jaxb2Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlToObjectTransformer extends AbstractTransformer {

    private static final Logger logger = LoggerFactory.getLogger(XmlToObjectTransformer.class);

    public XmlToObjectTransformer() {}

    @Override
    protected AgentesElement doTransform(Message<?> message) {
        byte[] stream = (byte[]) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        InputStream input = new ByteArrayInputStream(stream);

        Jaxb2Helper helper = new Jaxb2Helper();

        AgentesElement agentes = null;

        try {
            agentes = helper.unMarshaling(input);
            agentes.setData(stream);
            agentes.setNomeArquivo((String) headers.get("file_name"));

            logger.info("XmlParse do arquivo {} com sucesso.", agentes.getNomeArquivo());

        } catch (JAXBException | IOException e) {
            logger.error("Error ao realizar o parse do XML: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return agentes;
    }

}
