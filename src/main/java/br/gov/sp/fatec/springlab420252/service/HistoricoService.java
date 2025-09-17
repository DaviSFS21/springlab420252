package br.gov.sp.fatec.springlab420252.service;

import br.gov.sp.fatec.springlab420252.entity.Historico;
import java.util.List;
import java.util.Set;

public interface HistoricoService {
    Historico save(Historico historico);
    List<Historico> findAll();
    Set<Historico> findByWords(String first, String second);
}
