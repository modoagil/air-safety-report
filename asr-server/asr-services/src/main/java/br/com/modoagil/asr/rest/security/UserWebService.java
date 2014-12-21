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
package br.com.modoagil.asr.rest.security;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import lombok.Getter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.modoagil.asr.model.security.User;
import br.com.modoagil.asr.repository.security.UserRepository;
import br.com.modoagil.asr.rest.support.GenericWebService;
import br.com.modoagil.asr.rest.support.Response;
import br.com.modoagil.asr.rest.support.ResponseBuilder;
import br.com.modoagil.asr.rest.support.ResponseMessages;
import br.com.modoagil.asr.rest.support.WebServicesURL;

/**
 * REST services for {@link User}
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see GenericWebService
 */
@RestController
@RequestMapping(value = WebServicesURL.URL_USUARIO)
public class UserWebService extends GenericWebService<User, UserRepository> {

    @Getter
    @Autowired
    private UserRepository repository;

    /**
     * Busca por usuário pelo email fornecido
     *
     * @param email
     *            email a ser consultado
     * @return {@link Usuario}
     */
    @RequestMapping(value = WebServicesURL.URL_USUARIO_FIND_EMAIL + "/{email}", method = {GET, POST},
            produces = APPLICATION_JSON)
    public Response<User> findUsuarioByEmail(@PathVariable("email") final String email) {
        Response<User> response;
        getLogger().debug("buscando usuário pelo email '" + email + "'");
        try {
            final User usuario = getRepository().findByEmailIgnoreCase(email);
            if (usuario != null) {
                response = new ResponseBuilder<User>().success(true).data(usuario)
                        .message(String.format(ResponseMessages.FIND_MESSAGE, usuario.getId())).status(HttpStatus.OK)
                        .build();
                getLogger().debug("sucesso ao buscar usuário pelo email '" + email + "'");
            } else {
                response = new ResponseBuilder<User>().success(true)
                        .message(String.format("Usuário com email %s não foi encontrado", email)).status(HttpStatus.OK)
                        .build();
                getLogger().debug("não foi encontrado usuário com email '" + email + "'");
            }
        } catch (final Exception e) {
            final String message = ExceptionUtils.getRootCauseMessage(e);
            response = new ResponseBuilder<User>().success(false).message(message).status(HttpStatus.BAD_REQUEST)
                    .build();
            getLogger().error("erro ao buscar usuário pelo email '" + email + "'", e);
        }
        return response;
    }

}
