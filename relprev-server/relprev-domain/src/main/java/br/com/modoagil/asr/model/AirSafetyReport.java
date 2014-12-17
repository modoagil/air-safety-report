package br.com.modoagil.asr.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.model.support.Attachment;
import br.com.modoagil.asr.model.support.ModelConstants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * JPA entity and JSON model for air safety report
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
@JsonRootName(value = "asr")
@EqualsAndHashCode(callSuper = true)
@Table(name = "air_safety_reports")
public class AirSafetyReport extends AbstractEntity {

    private static final long serialVersionUID = -2567465353998731784L;

    private static final String ASR = "report";

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_60)
    @NotNull(message = "validation.AirSafetyReport.involveds.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_60,
            message = "validation.AirSafetyReport.involveds.Size.message")
    private String involveds;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_60)
    @NotNull(message = "validation.AirSafetyReport.place.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_60,
            message = "validation.AirSafetyReport.place.Size.message")
    private String place;

    @Getter
    @Setter
    @JsonProperty(value = "description")
    @Column(nullable = false, name = "description", length = ModelConstants.COLUMN_SIZE_600)
    @NotNull(message = "validation.AirSafetyReport.description.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_600,
            message = "validation.AirSafetyReport.description.Size.message")
    private String description;

    @Getter
    @Setter
    @JsonProperty(value = "dataTime")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "data_time")
    @Past(message = "validation.AirSafetyReport.dateTime.Past.message")
    @NotNull(message = "validation.AirSafetyReport.dateTime.NotNull.message")
    private Date dateTime;

    @Getter
    @Setter
    @JsonProperty
    @Column(name = "status", length = ModelConstants.COLUMN_SIZE_5000)
    private String status;

    @ManyToOne
    @JsonProperty
    @JoinColumn(name = "sipaer_link_id")
    private SIPAERLink sipaerLink;

    @Getter
    @Setter
    @JsonProperty
    @JoinColumn(name = "reporter_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Reporter reporter;

    @Getter
    @Setter
    @JoinColumn(name = "asr_id")
    @JsonProperty(value = "attachments")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Attachment> attachments;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<RecommendedAction> recommendedActions;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<RiskClassification> riskClassifications;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<Forwarding> forwardings;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<AirSafetyObservation> observations;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<SectorOpinion> sectorOpinions;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = ASR, fetch = FetchType.LAZY)
    private Set<AirSafetyAnswer> answers;

}
