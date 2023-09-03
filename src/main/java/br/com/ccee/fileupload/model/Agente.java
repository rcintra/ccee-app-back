package br.com.ccee.fileupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "agentes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "data")
    private LocalDateTime data;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquivo_id", nullable = false)
    private Arquivo arquivo;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Regiao> regioes;

    public void addRegiao(Regiao regiao) {
        this.regioes.add(regiao);
    }
}
