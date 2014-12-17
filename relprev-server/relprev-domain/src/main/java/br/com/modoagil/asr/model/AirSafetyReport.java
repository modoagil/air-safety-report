package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.Anexo;
import br.com.modoagil.model.support.ModelConstants;
import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Entidade para persistência e retorno de JSON de relatórios de prevenção
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@JsonInclude(Include.NON_EMPTY)
@JsonRootName(value = "relPrev")
@EqualsAndHashCode(callSuper = true)
@Table(name = "relatorios_prevencao")
@Updatable(newinsert = true, updatable = false)
public class RelatorioPrevencao extends AbstractEntity<RelatorioPrevencao> {

    private static final long serialVersionUID = -2567465353998731784L;

    private static final String RELPREV = "relPrev";

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_60)
    @NotNull(message = "validation.RelatorioPrevencao.envolvidos.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1,
        max = ModelConstants.FIELD_SIZE_60,
        message = "validation.RelatorioPrevencao.envolvidos.Size.message")
    private String envolvidos;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_60)
    @NotNull(message = "validation.RelatorioPrevencao.local.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1,
        max = ModelConstants.FIELD_SIZE_60,
        message = "validation.RelatorioPrevencao.local.Size.message")
    private String local;

    @Getter
    @Setter
    @JsonProperty(value = "descricao")
    @Column(nullable = false, name = "descricao", length = ModelConstants.COLUMN_SIZE_600)
    @NotNull(message = "validation.RelatorioPrevencao.descricaoSituacaoPerigosa.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1,
        max = ModelConstants.FIELD_SIZE_600,
        message = "validation.RelatorioPrevencao.descricaoSituacaoPerigosa.Size.message")
    private String descricaoSituacaoPerigosa;

    @Getter
    @Setter
    @JsonProperty(value = "data")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data")
    @Past(message = "validation.RelatorioPrevencao.dataSituacaoPerigosa.Past.message")
    @NotNull(message = "validation.RelatorioPrevencao.dataSituacaoPerigosa.NotNull.message")
    private Date dataSituacaoPerigosa;

    @Getter
    @Setter
    @JsonProperty(value = "situacao")
    @Column(name = "situacao", length = ModelConstants.COLUMN_SIZE_5000)
    private String situacao;

    @ManyToOne
    @JsonProperty
    @JoinColumn(name = "elosipaer_id")
    // TODO não existe na EOR esta associação. Confirmar como ficará
    private EloSipaer eloSipaer;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "relator_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Relator relator;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "situacoes_id")
    @NotNull(message = "validation.RelatorioPrevencao.situacoes.NotNull.message")
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.EAGER)
    private Situacao situacoes;

    @Getter
    @Setter
    @JoinColumn(name = "relprev_id")
    @JsonProperty(value = "anexos")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Anexo> anexos;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<AcaoRecomendada> acoesRecomendadas;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private AcaoRecomendada acaoRecomendada;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<ClassificacaoRisco> classificacoesRisco;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private ClassificacaoRisco classificacaoRisco;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<Encaminhamento> encaminhamentos;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private Encaminhamento encaminhamento;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<Observacao> observacoes;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private Observacao observacao;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<ParecerSetor> pareceresSetor;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private ParecerSetor parecerSetor;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = RELPREV, fetch = FetchType.EAGER)
    private Set<Resposta> respostas;

    @Getter
    @Setter
    @Transient
    @JsonProperty
    private Resposta resposta;

}
