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
package br.com.modoagil.asr.repository.support.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.repository.support.GenericRepository;

/**
 * Extende recursos do Spring Data, para queries mais elaboradas, além de implementar modelo de persistência adotado para o
 * projeto
 *
 * @created 17/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see SimpleJpaRepository
 */
@Transactional(readOnly = true)
public class GenericRepositoryImpl<E extends AbstractEntity, PK extends Serializable> extends
        SimpleJpaRepository<E, PK> implements GenericRepository<E, PK> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<E, ?> entityInformation;

    public GenericRepositoryImpl(final JpaEntityInformation<E, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Override
    @Transactional
    public <S extends E> S save(final S entity) {
        Assert.notNull(entity, "A entidade não pode ser nula!");
        // TODO
        return entity;
    }

    @Override
    @Transactional
    public void delete(final E entity) {
        Assert.notNull(entity, "A entidade não pode ser nula!");
        entity.setChangeDate(Calendar.getInstance().getTime());
    }

    @Override
    public List<E> findAll() {
        return super.findAll(this.isRemoved());
    }

    @Override
    public E findOne(final PK pk) {
        return this.findOne(this.isRemovedByID(pk));
    }

    /**
     * {@link Specification} para listagem de objetos não ocultos
     *
     * @return {@link Specification}
     */
    private Specification<E> isRemoved() {
        return (root, query, cb) -> cb.isFalse(root.<Boolean> get("removed"));
    }

    /**
     * {@link Specification} para consulta de um único objeto não oculto
     *
     * @param pk
     *            id do objeto a ser consultado
     * @return {@link Specification}
     */
    private Specification<E> isRemovedByID(final PK pk) {
        return (root, query, cb) -> {
            final Predicate id = cb.equal(root.get("id"), pk);
            final Predicate hidden = cb.isFalse(root.<Boolean> get("removed"));
            return cb.and(id, hidden);
        };
    }

    /**
     * {@link Specification} para consulta de um único objeto não oculto
     *
     * @param id
     *            id do objeto a ser consultado
     * @return {@link Specification}
     */
    private Specification<E> isRemovedByID(final Long id) {
        return (root, query, cb) -> {
            final Predicate pk = cb.equal(root.get("id"), id);
            final Predicate hidden = cb.isFalse(root.<Boolean> get("removed"));
            return cb.and(pk, hidden);
        };
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected JpaEntityInformation<E, ?> getEntityInformation() {
        return this.entityInformation;
    }

}
