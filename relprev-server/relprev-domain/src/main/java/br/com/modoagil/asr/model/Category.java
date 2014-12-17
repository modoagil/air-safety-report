package br.com.modoagil.asr.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.model.support.ModelConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA entity and JSON model for categories
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Table(name = "categories")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {

    private static final long serialVersionUID = 2429958840047244813L;

    @Getter
    @Setter
    @JsonProperty
    @OneToOne(optional = true)
    @JoinColumn(name = "parent")
    private Category parent;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "taxonomy_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Taxonomy taxonomy;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    @NotNull(message = "validation.Category.name.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, message = "validation.Category.name.Size.message")
    private String name;

}
