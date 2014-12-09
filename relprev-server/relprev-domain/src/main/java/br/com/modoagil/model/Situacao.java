package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.annotation.Hiddenable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para persistência e retorno de JSON das situação de um Relatório de Prevenção
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@Table(name = "situacoes")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class Situacao extends AbstractEntity<Situacao> {

    private static final long serialVersionUID = 990407097291869785L;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "tem_encaminhamento")
    @NotNull(message = "validation.Situacao.temEncaminhamento.NotNull.message")
    private Boolean temEncaminhamento;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "tem_divulgacao")
    @NotNull(message = "validation.Situacao.temDivulgacao.NotNull.message")
    private Boolean temDivulgacao;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "tem_acao_recomendada")
    @NotNull(message = "validation.Situacao.temAcaoRecomendada.NotNull.message")
    private Boolean temAcaoRecomendada;

    @Getter
    @Setter
    @JsonProperty(value = "concluido")
    @Column(nullable = false, name = "foi_concluido")
    @NotNull(message = "validation.Situacao.foiConcluido.NotNull.message")
    private Boolean foiConcluido;

}
