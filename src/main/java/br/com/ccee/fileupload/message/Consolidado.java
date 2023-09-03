package br.com.ccee.fileupload.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consolidado {

    private String regiao;
    private BigDecimal geracao;
    private BigDecimal compra;

}
