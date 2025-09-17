package br.gov.sp.fatec.springlab420252.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import java.time.LocalDate;

@Entity
@Table(name = "his_historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "his_id")
    private Long id;

    @Column(name = "his_aut_nome_antigo", nullable = false, length = 20)
    private String autNomeAntigo;

    @Column(name = "his_aut_nome_novo", nullable = false, length = 20)
    private String autNomeNovo;

    @Column(name = "his_data", nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "his_autorizacao", nullable = false, foreignKey = @ForeignKey(name = "fk_his_autorizacao"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Autorizacao autorizacao;

    @Column(name = "his_alcance")
    private Double alcance;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutNomeAntigo() {
        return autNomeAntigo;
    }

    public void setAutNomeAntigo(String autNomeAntigo) {
        this.autNomeAntigo = autNomeAntigo;
    }

    public String getAutNomeNovo() {
        return autNomeNovo;
    }

    public void setAutNomeNovo(String autNomeNovo) {
        this.autNomeNovo = autNomeNovo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Double getAlcance() {
        return alcance;
    }

    public void setAlcance(Double alcance) {
        this.alcance = alcance;
    }
}

