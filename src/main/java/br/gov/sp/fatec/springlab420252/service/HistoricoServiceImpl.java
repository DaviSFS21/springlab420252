package br.gov.sp.fatec.springlab420252.service;

import br.gov.sp.fatec.springlab420252.entity.Historico;
import br.gov.sp.fatec.springlab420252.repository.HistoricoRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class HistoricoServiceImpl implements HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoServiceImpl(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public Historico save(Historico historico) {
        if (historico.getData() == null) historico.setData(LocalDate.now());

        if (historico.getAlcance() != null) {
            if ((historico.getAlcance() < 0 || historico.getAlcance() > 1)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alcance deve estar entre 0 e 1!");
            }
        }

        String nomeAntigo = historico.getAutNomeAntigo();
        String nomeNovo = historico.getAutNomeNovo();

        if (nomeNovo.equals(nomeAntigo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome novo deve ser diferente do nome antigo!");
        }
        if (nomeNovo.length() <= 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome novo deve ter mais de 8 caracteres!");
        }
        if (!nomeNovo.startsWith("ROLE_")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome novo deve começar com 'ROLE_'.");
        }
        return historicoRepository.save(historico);
    }

    @Override
    public List<Historico> findAll() {

        return historicoRepository.findAll();
    }

    @Override
    public Set<Historico> findByWords(String first, String second) {
        return historicoRepository.findByWords(first, second);
    }
}
