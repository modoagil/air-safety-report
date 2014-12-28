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
package br.com.modoagil.asr.repository.security;

import br.com.modoagil.asr.model.security.User;
import br.com.modoagil.asr.repository.support.GenericRepository;

/**
 * Repositório de acesso a dados de {@link User}
 *
 * @created 17/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see GenericRepository
 */
public interface UserRepository extends GenericRepository<User, Long> {

    /**
     * Consulta na base de dados usuário pelo email fornecido
     *
     * @param email
     *            email do usuário na base de dados
     * @return {@link Usuario}
     */
    User findByEmailIgnoreCase(final String email);

    /**
     * Consulta na base de dados usuário pelo nome fornecido
     *
     * @param fullName
     *            nome completo do usuário na base de dados
     * @return {@link Usuario}
     */
    User findByFullNameIgnoreCase(final String fullName);

}
