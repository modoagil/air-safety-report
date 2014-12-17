package br.com.modoagil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.ModelConstants;
import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para persistência e retorno de JSON de relatores de um relatório de prevenção
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@Table(name = "relatores")
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@Updatable(newinsert = true, updatable = false)
public class Relator extends AbstractEntity<Relator> {

    private static final long serialVersionUID = -671624807223719350L;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_50)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_50,
    message = "validation.Relator.nome.Size.message")
    private String nome;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_20)
    @Pattern(regexp = ModelConstants.TELEFONE_REGEX, message = "validation.Relator.telefoneCelular.Telefone.message")
    private String telefoneCelular;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_20)
    @Pattern(regexp = ModelConstants.TELEFONE_REGEX, message = "validation.Relator.telefoneResidencial.Telefone.message")
    private String telefoneResidencial;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_120)
    @Email(message = "validation.Relator.email.Email.message", regexp = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}")
    private String email;

}
