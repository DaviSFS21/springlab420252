package br.gov.sp.fatec.springlab420252.dto;

import br.gov.sp.fatec.springlab420252.entity.Autorizacao;
import br.gov.sp.fatec.springlab420252.entity.Historico;
import br.gov.sp.fatec.springlab420252.repository.AutorizacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class HistoricoMapper {
    private final AutorizacaoRepository autorizacaoRepository;

    public HistoricoMapper(AutorizacaoRepository autorizacaoRepository) {
        this.autorizacaoRepository = autorizacaoRepository;
    }

    public Historico toEntity(HistoricoDTO dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DTO não pode ser nulo!");
        }
        Historico historico = new Historico();
        historico.setAutNomeAntigo(dto.autNomeAntigo());
        historico.setAutNomeNovo(dto.autNomeNovo());
        if (dto.data() != null) {
            try {
                historico.setData(LocalDate.parse(dto.data()));
            } catch (DateTimeParseException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de data inválido! Use yyyy-MM-dd.");
            }
        } else {
            historico.setData(null);
        }
        Autorizacao autorizacao = autorizacaoRepository.findById(Long.valueOf(dto.autorizacao()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autorização não encontrada: " + dto.autorizacao()));
        historico.setAutorizacao(autorizacao);
        historico.setAlcance(dto.alcance());
        return historico;
    }
}

