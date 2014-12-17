package br.com.modoagil.asr.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.support.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA entity to relates an air safety report on a Category
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
@Table(name = "taxonomy_reports")
@EqualsAndHashCode(callSuper = true)
public class TaxonomyReport extends AbstractEntity {

    private static final long serialVersionUID = -3558622120537900967L;

    @Getter
    @Setter
    @JsonProperty
    @OneToOne(optional = false)
    @JoinColumn(name = "asr_id")
    private AirSafetyReport report;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "taxonomy_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Taxonomy taxonomy;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Category category;

}
