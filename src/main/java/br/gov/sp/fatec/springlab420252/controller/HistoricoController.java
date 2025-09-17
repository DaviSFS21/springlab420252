package br.gov.sp.fatec.springlab420252.controller;

import br.gov.sp.fatec.springlab420252.dto.HistoricoDTO;
import br.gov.sp.fatec.springlab420252.dto.HistoricoMapper;
import br.gov.sp.fatec.springlab420252.entity.Historico;
import br.gov.sp.fatec.springlab420252.service.HistoricoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/historico")
public class HistoricoController {
    private final HistoricoService historicoService;
    private final HistoricoMapper historicoMapper;

    public HistoricoController(HistoricoService historicoService, HistoricoMapper historicoMapper) {
        this.historicoService = historicoService;
        this.historicoMapper = historicoMapper;
    }

    @PostMapping
    public ResponseEntity<Historico> createHistorico(@RequestBody HistoricoDTO dto) {
        Historico novo = historicoService.save(historicoMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/historico")).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<Historico>> getAllHistoricos() {
        return new ResponseEntity<>(historicoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<Historico>> searchHistoricos(@RequestParam String first, @RequestParam String second) {
        return new ResponseEntity<>(historicoService.findByWords(first, second), HttpStatus.OK);
    }
}
