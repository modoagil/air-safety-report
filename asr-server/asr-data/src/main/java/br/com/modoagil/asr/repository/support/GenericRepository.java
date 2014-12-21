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
package br.com.modoagil.asr.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.modoagil.asr.model.support.AbstractEntity;

/**
 * Repositório genérico para extender recursos do Spring Data, para queries ou métodos mais elaborados
 *
 * @created 17/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see JpaRepository
 */
@NoRepositoryBean
public interface GenericRepository<E extends AbstractEntity, PK extends Serializable> extends JpaRepository<E, PK>,
        JpaSpecificationExecutor<E> {

    /**
     * Recupera o {@link EntityManager}
     *
     * @return {@link EntityManager}
     */
    EntityManager getEntityManager();

}
