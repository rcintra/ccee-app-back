package br.com.ccee.fileupload.repository;

import br.com.ccee.fileupload.model.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {

    @Query("select r.sigla from Regiao r group by r.sigla")
    public List<String> findAllGroupBySigla();

}
