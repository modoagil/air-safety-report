package br.com.modoagil.model.support;

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
@Updatable(newinsert = true, updatable = false)
public class Anexo extends AbstractEntity<Anexo> {

    private static final long serialVersionUID = 4571775183834766912L;

    @JsonProperty
    @Column(nullable = false, name = "mime_type")
    private String mimeType;

    @JsonProperty
    @Column(nullable = false, name = "path_anexo")
    private String pathAnexo;

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPathAnexo() {
        return this.pathAnexo;
    }

    public void setPathAnexo(final String pathAnexo) {
        this.pathAnexo = pathAnexo;
    }

}
