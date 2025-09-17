package br.gov.sp.fatec.springlab420252.repository;

import br.gov.sp.fatec.springlab420252.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Query("SELECT h FROM Historico h WHERE h.autNomeAntigo LIKE %?1% AND h.autorizacao.nome LIKE ?2")
    Set<Historico> findByWords(String first, String second);
}

