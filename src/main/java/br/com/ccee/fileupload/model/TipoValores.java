package br.com.ccee.fileupload.model;

import br.com.ccee.fileupload.enums.TipoValor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipoValores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoValores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoValor tipo;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao_id", nullable = false)
    private Regiao regiao;
}
