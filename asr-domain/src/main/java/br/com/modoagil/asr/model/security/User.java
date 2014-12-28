/*
 *     Copyright 2014 Modo Ágil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package br.com.modoagil.asr.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
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
 * JPA entity and JSON model for users
 *
 * @created 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "user", "removed"}),
        @UniqueConstraint(columnNames = {"email", "hidden"})})
public class User extends AbstractEntity {

    private static final long serialVersionUID = -2583035988668453632L;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_120)
    @Email(message = "validation.User.email.Email.message", regexp = ModelConstants.EMAIL_PATTERN)
    private String email;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_30)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_30,
            message = "validation.User.specialty.Size.message")
    private String specialty;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.function.NotNull.message")
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_30)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_60,
            message = "validation.User.function.Size.message")
    private String function;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.fullName.NotNull.message")
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_60, name = "full_name")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_60,
            message = "validation.User.fullName.Size.message")
    private String fullName;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.post.NotNull.message")
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_20)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_20,
            message = "validation.User.post.Size.message")
    private String post;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.sectorAcronym.NotNull.message")
    @Column(nullable = false, length = ModelConstants.COLUMN_SIZE_15, name = "sector_acronym")
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_15,
            message = "validation.User.sectorAcronym.Size.message")
    private String sectorAcronym;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_30, name = "cell_phone")
    @Pattern(regexp = ModelConstants.PHONE_PATTERN, message = "validation.User.cellPhone.Phone.message")
    private String cellPhone;

    @Getter
    @Setter
    @JsonProperty
    @Column(length = ModelConstants.COLUMN_SIZE_30, name = "land_line")
    @Pattern(regexp = ModelConstants.PHONE_PATTERN, message = "validation.User.landLine.Phone.message")
    private String landLine;

    /* TODO separar informações para login do usuário */
    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.login.NotNull.message")
    @Column(length = ModelConstants.COLUMN_SIZE_45, nullable = false, updatable = false)
    @Size(min = ModelConstants.FIELD_SIZE_1, max = ModelConstants.FIELD_SIZE_45,
            message = "validation.User.login.Size.message")
    private String login;

    @Getter
    @Setter
    @JsonProperty
    @NotNull(message = "validation.User.password.NotNull.message")
    @Column(length = ModelConstants.COLUMN_SIZE_40, nullable = false)
    @Size(min = ModelConstants.FIELD_SIZE_40, max = ModelConstants.FIELD_SIZE_40,
            message = "validation.User.password.Size.message")
    private String password;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false)
    @NotNull(message = "validation.User.active.NotNull.message")
    private Boolean active;

}
