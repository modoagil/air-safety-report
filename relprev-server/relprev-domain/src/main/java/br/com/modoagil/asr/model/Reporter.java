package br.com.modoagil.asr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.model.support.ModelConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA entity and JSON model for reporter of an air safety report
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Table(name = "reporters")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
public class Reporter extends AbstractEntity {

    private static final long serialVersionUID = -671624807223719350L;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_50)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_50,
            message = "validation.Reporter.name.Size.message")
    private String name;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_20)
    @Pattern(regexp = ModelConstants.PHONE_PATTERN, message = "validation.Reporter.cellPhone.Phone.message")
    private String cellPhone;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_20)
    @Pattern(regexp = ModelConstants.PHONE_PATTERN, message = "validation.Reporter.homePhone.Phone.message")
    private String homePhone;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_120)
    @Email(message = "validation.Reporter.email.Email.message", regexp = ModelConstants.EMAIL_PATTERN)
    private String email;

}
