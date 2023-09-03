package br.com.ccee.fileupload.repository;

import br.com.ccee.fileupload.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Integer> {

}
