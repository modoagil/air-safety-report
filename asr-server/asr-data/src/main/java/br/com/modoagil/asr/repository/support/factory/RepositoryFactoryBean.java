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
package br.com.modoagil.asr.repository.support.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import br.com.modoagil.asr.repository.support.impl.GenericRepositoryImpl;

/**
 * Factory to config custom repository
 *
 * @created 17/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see JpaRepositoryFactoryBean
 */
public class RepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends
        JpaRepositoryFactoryBean<T, S, ID> {

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(final EntityManager entityManager) {
        return new RepositoryFactory(entityManager);
    }

    private static class RepositoryFactory extends JpaRepositoryFactory {

        public RepositoryFactory(final EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        @SuppressWarnings({"unchecked", "rawtypes"})
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                final RepositoryMetadata metadata, final EntityManager entityManager) {
            final JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(metadata
                    .getDomainType());
            return new GenericRepositoryImpl(entityInformation, entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(final RepositoryMetadata metadata) {
            return GenericRepositoryImpl.class;
        }

    }

}
