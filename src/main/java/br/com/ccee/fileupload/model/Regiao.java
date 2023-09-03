package br.com.ccee.fileupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regioes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sigla")
    private String sigla;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoValores> geracao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoValores> compra;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoValores> precoMedio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_id", nullable = false)
    private Agente agente;
}
