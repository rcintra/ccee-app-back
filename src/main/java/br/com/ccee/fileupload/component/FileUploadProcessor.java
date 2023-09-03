package br.com.ccee.fileupload.component;

import br.com.ccee.fileupload.service.AgenteService;
import br.com.ccee.fileupload.transformer.ElementToModelTransformer;
import br.com.ccee.fileupload.transformer.element.AgentesElement;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class FileUploadProcessor {

    private AgenteService agenteService;

    public FileUploadProcessor(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @ServiceActivator(inputChannel = "integration.fileupload.xmlToObject.fromTransformer.channel")
    public void processXmlToObject(Message<?> message) throws MessagingException {

        AgentesElement agentes = (AgentesElement) message.getPayload();

        ElementToModelTransformer transformerElements = new ElementToModelTransformer(agentes);

        agenteService.createAgentes(transformerElements.doTransformer());

    }

}
