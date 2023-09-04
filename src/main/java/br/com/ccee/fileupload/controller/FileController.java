package br.com.ccee.fileupload.controller;

import br.com.ccee.fileupload.message.Consolidado;
import br.com.ccee.fileupload.message.FileInfo;
import br.com.ccee.fileupload.message.ResponseMessage;
import br.com.ccee.fileupload.service.ArquivoService;
import br.com.ccee.fileupload.service.FileUploadService;
import br.com.ccee.fileupload.service.IntegrationGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private IntegrationGateway gateway;
    private ArquivoService arquivoService;
    private FileUploadService fileUploadService;

    public FileController(IntegrationGateway gateway, ArquivoService arquivoService, FileUploadService fileUploadService){
        this.gateway                = gateway;
        this.arquivoService         = arquivoService;
        this.fileUploadService      = fileUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        gateway.sendMessage(file.getBytes(), file.getOriginalFilename());
        logger.info("Arquivo upload com sucesso: " + file.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMessage("Arquivo upload com sucesso: " + file.getOriginalFilename()
                )
        );
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> arquivos = arquivoService.findAll().stream().map(a-> {
           return new FileInfo(a.getNome(), a.getCreateTime());
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(arquivos);
    }

    @GetMapping("/consolidado")
    public ResponseEntity<List<Consolidado>> getValoresConsolidados() {
        return ResponseEntity.status(HttpStatus.OK).body(fileUploadService.consultarValoresConsolidados());
    }
}
