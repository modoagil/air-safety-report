package br.com.modoagil.asr.model.support;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JPA entity and JSON model for attachments
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Table(name = "attachments")
@EqualsAndHashCode(callSuper = true)
public class Attachment extends AbstractEntity {

    private static final long serialVersionUID = 4571775183834766912L;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "mime_type")
    private String mimeType;

    @Getter
    @Setter
    @JsonProperty
    @Column(nullable = false, name = "attachment_path")
    private String attachmentPath;

}
