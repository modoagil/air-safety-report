package br.com.modoagil.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.modoagil.model.support.AbstractEntity;
import br.com.modoagil.model.support.annotation.Hiddenable;
import br.com.modoagil.model.support.annotation.Updatable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade que relaciona um relatório de prevenção a taxonomia e categoria
 * 
 * @since 07/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@Entity
@Hiddenable
@JsonInclude(Include.NON_EMPTY)
@Table(name = "relatorio_taxonomia")
@Updatable(newinsert = true, updatable = false)
public class RelatorioTaxonomia extends AbstractEntity<RelatorioTaxonomia> {

    private static final long serialVersionUID = -3558622120537900967L;

    @JsonProperty
    @OneToOne(optional = false)
    @JoinColumn(name = "relprev_id")
    private RelatorioPrevencao relPrev;

    @JsonProperty
    @JoinColumn(name = "taxonomia_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Taxonomia taxonomia;

    @JsonProperty
    @JoinColumn(name = "categoria_id")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Categoria categoria;

    public RelatorioPrevencao getRelPrev() {
        return this.relPrev;
    }

    public void setRelPrev(final RelatorioPrevencao relPrev) {
        this.relPrev = relPrev;
    }

    public Taxonomia getTaxonomia() {
        return this.taxonomia;
    }

    public void setTaxonomia(final Taxonomia taxonomia) {
        this.taxonomia = taxonomia;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }

}
