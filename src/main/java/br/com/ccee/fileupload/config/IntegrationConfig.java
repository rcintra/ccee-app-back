package br.com.ccee.fileupload.config;

import br.com.ccee.fileupload.transformer.XmlToObjectTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@IntegrationComponentScan
@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public MessageChannel receiverChanner() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "integration.gateway.channel", outputChannel = "integration.fileupload.xmlToObject.fromTransformer.channel")
    XmlToObjectTransformer xmlToObjectTransformer() {
        return new XmlToObjectTransformer();
    }
}
