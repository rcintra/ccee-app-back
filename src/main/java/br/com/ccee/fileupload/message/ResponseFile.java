package br.com.ccee.fileupload.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseFile {
    private Integer id;
    private String nome;
    private byte[] data;
}
