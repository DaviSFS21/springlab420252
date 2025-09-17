package br.gov.sp.fatec.springlab420252.dto;

public record HistoricoDTO(
    String autNomeAntigo,
    String autNomeNovo,
    String autorizacao,
    String data,
    Double alcance
) {
}
