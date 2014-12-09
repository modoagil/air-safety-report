package br.com.modoagil.model.support;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para persistência e retorno de JSON de anexos de um relatório de prevenção
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@Table(name = "anexos")
@EqualsAndHashCode(callSuper = true)
@Updatable(newinsert = true, updatable = false)
public class Anexo extends AbstractEntity<Anexo> {

    private static final long serialVersionUID = 4571775183834766912L;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "mime_type")
    private String mimeType;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "path_anexo")
    private String pathAnexo;

}
