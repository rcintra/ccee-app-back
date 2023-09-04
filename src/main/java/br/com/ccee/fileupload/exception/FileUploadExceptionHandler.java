package br.com.ccee.fileupload.exception;

import br.com.ccee.fileupload.message.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INTERNAL_ERROR = "Erro ao processar a requisição";
    private static final String MSG_FILE_MAX_SIZE = "Arquivo muito grande!";

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(MSG_FILE_MAX_SIZE));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handlerAllException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, new ResponseMessage(INTERNAL_ERROR), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
