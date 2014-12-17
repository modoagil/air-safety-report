package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.ModelConstants;
import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para persistência e retorno de JSON de taxonomias de um relatório de prevenção
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@Table(name = "taxonomias")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@Updatable(newinsert = true, updatable = false)
public class Taxonomia extends AbstractEntity<Taxonomia> {

    private static final long serialVersionUID = -8111373397877993819L;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.Taxonomia.nome.NotNull.message")
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_20)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_20,
        message = "validation.Taxonomia.nome.Size.message")
    private String nome;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    @NotNull(message = "validation.Taxonomia.status.NotNull.message")
    private Boolean status;

    @Getter
    @Setter
    @JsonProperty
    @Column(name = "padrao_minimo", nullable = false)
    @NotNull(message = "validation.Taxonomia.padraoMinimo.NotNull.message")
    private Boolean padraoMinimo;

    /*
     * FORMATAÇÕES
     * Existem duas formatações para os itens de uma taxonomia, em que o usuário deve escolher uma.
     * 1). A taxonomia tem somente um campo para uma Descrição(TEXTO(600))
     * 2). A taxonomia tem uma lista de categorias(TEXTO(15)), onde cada categoria pode conter uma lista com
     * sub-categorias(TEXTO(15)).
     */
    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_600)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_600,
        message = "validation.Taxonomia.descricao.Size.message")
    private String descricao;

    @Getter
    @Setter
    @JsonProperty(value = "categorias")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "taxonomia_categorias",
        joinColumns = {@JoinColumn(name = "taxonomia_id")},
        inverseJoinColumns = {@JoinColumn(name = "categoria_id")})
    private Set<Categoria> categorias;

}
