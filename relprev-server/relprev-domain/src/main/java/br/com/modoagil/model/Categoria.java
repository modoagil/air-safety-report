package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
 * Entidade para persistência e retorno de JSON de Categoria
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@Table(name = "categorias")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@Updatable(newinsert = true, updatable = false)
public class Categoria extends AbstractEntity<Categoria> {

    private static final long serialVersionUID = 2429958840047244813L;

    @Getter
    @Setter
    @JsonProperty
    @OneToOne(optional = true)
    @JoinColumn(name = "categoria_pai")
    private Categoria categoriaPai;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "taxonomia_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Taxonomia taxonomia;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    @NotNull(message = "validation.Categoria.nome.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, message = "validation.Categoria.nome.Size.message")
    private String nome;

}
