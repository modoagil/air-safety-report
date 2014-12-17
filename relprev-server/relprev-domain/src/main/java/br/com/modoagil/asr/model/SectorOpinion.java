package br.com.modoagil.asr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.model.support.ModelConstants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA entity and JSON model for sector opnion
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
@Table(name = "sector_opinions")
@EqualsAndHashCode(callSuper = true)
public class SectorOpinion extends AbstractEntity {

    private static final long serialVersionUID = -2223879036406313667L;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "asr_id")
    private AirSafetyReport report;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_600, nullable = false)
    @NotNull(message = "validation.SectorOpinion.description.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_600,
            message = "validation.SectorOpinion.description.Size.message")
    private String description;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "validation.SectorOpinion.date.NotNull.message")
    // TODO incluir validação para a data, que não pode ser menor que a data do relprev
    private Date date;

}
