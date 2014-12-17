package br.com.modoagil.asr.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.security.User;
import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.model.support.ModelConstants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * JPA entity and JSON model for System Aeronautical Accident Investigation and Prevention (SIPAER, in portuguese)
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Table(name = "sipaer_link")
@JsonInclude(Include.NON_EMPTY)
@JsonRootName(value = "SIPAERLink")
@EqualsAndHashCode(callSuper = true)
public class SIPAERLink extends AbstractEntity {

    private static final long serialVersionUID = 3850763253702817582L;

    @Getter
    @Setter
    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_20)
    @NotNull(message = "validation.SIPAERLink.organization.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_20,
            message = "validation.SIPAERLink.organization.Size.message")
    private String organization;

    @Getter
    @Setter
    @JsonProperty(value = "acronym")
    @Column(name = "organization_acronym", nullable = false, length = ModelConstants.COLUMN_SIZE_20)
    @NotNull(message = "validation.SIPAERLink.organizationAcronym.NotNull.message")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_20,
            message = "validation.SIPAERLink.organizationAcronym.Size.message")
    private String organizationAcronym;

}
