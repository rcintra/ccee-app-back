package br.com.ccee.fileupload.transformer;

import br.com.ccee.fileupload.transformer.element.AgentesElement;
import br.com.ccee.fileupload.transformer.helper.Jaxb2Helper;
import org.springframework.core.ResolvableType;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XmlToObjectTransformer extends AbstractTransformer {
    private ClassLoader classLoader;
    private ResolvableType targetType;
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    public XmlToObjectTransformer() {
    }

    public XmlToObjectTransformer(@Nullable Class<?> targetClass){
        this(ResolvableType.forClass(targetClass));
    }

    public XmlToObjectTransformer(ResolvableType targetType) {
        Assert.notNull(targetType, "'targetType' must not be null");
        this.targetType = targetType;
    }

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
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        return agentes;
    }

}
