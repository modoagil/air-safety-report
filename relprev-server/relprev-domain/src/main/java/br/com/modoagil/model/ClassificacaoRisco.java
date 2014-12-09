package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.ModelConstants;
import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para persistência e representação JSON sobre da Classificação de Risco
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@Table(name = "classificacoes_risco")
@Updatable(newinsert = true, updatable = false)
public class ClassificacaoRisco extends AbstractEntity<ClassificacaoRisco> {

    private static final long serialVersionUID = 80193580056312692L;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "relprev_id")
    private RelatorioPrevencao relPrev;

    @Getter
    @Setter
    @JsonProperty
    @Column(name = "avaliacao_inicial", length = ModelConstants.COLUMN_SIZE_2)
    private String avaliacaoInicial;

    @Getter
    @Setter
    @JsonProperty
    @Column(name = "avaliacao_final", length = ModelConstants.COLUMN_SIZE_2)
    private String avaliacaoFinal;

}
