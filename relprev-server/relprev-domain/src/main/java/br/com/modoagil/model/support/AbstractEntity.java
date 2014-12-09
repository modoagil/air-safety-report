package br.com.modoagil.model.support;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidade abstrata para objetos persistentes.<br />
 * Todas as outras entidades persistentes do modelo de dados devem extender esta entidade ou uma sub-classe dela
 *
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@MappedSuperclass
public abstract class AbstractEntity<E extends Serializable> implements Persistable<Long> {

    private static final long serialVersionUID = -2187928984731943693L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Version
    @JsonIgnore
    private Long versao;

    @Getter
    @Setter
    @Column
    @JsonIgnore
    private Boolean hidden;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_insercao_alteracao")
    private Date dataInsercaoAlteracao;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return this.getId() == null;
    }

    @Override
    public String toString() {
        return String.format("%s id: %s", this.getClass().getSimpleName(), this.getId());
    }

}
