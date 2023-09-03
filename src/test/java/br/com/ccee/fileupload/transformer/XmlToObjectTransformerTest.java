package br.com.ccee.fileupload.transformer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XmlToObjectTransformerTest {

    File xmlFile;

    @BeforeAll
    public void setup() {
        String xmlResource = "exemplo_02.xml";
        ClassLoader classLoader = getClass().getClassLoader();
        xmlFile = new File(classLoader.getResource(xmlResource).getFile());
    }

    @Test
    public void parseXml() throws IOException {
        XmlToObjectTransformer transformer = new XmlToObjectTransformer();
        Message<?> msg = MessageBuilder.withPayload(Files.readAllBytes(Paths.get(xmlFile.getPath()))).build();
        transformer.doTransform(msg);
    }
}
