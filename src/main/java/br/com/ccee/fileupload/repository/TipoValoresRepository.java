package br.com.ccee.fileupload.repository;

import br.com.ccee.fileupload.enums.TipoValor;
import br.com.ccee.fileupload.model.TipoValores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoValoresRepository extends JpaRepository<TipoValores, Integer> {

    public List<TipoValores> findByTipoAndRegiaoSigla(TipoValor tipo, String sigla);

}
