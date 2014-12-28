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
package br.com.modoagil.asr.rest.support;

/**
 * URLs dos endpoints REST (web services)
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
public interface WebServicesURL {

    /**
     * GET, POST
     */
    String URL_LIST = "";
    /**
     * GET
     */
    String URL_FIND = "/{id}";
    /**
     * DELETE
     */
    String URL_DELETE = "{id}";
    /**
     * POST
     */
    String URL_CREATE = "/create";
    /**
     * PUT
     */
    String URL_UPDATE = "/update";

    // relprev service
    String URL_RELPREV = "/relprev";
    String URL_RELPREV_FIND_LOCAL = "/local";
    String URL_RELPREV_FIND_DESCRICAO = "/descricao";

    // elosipaer service
    String URL_ELOSIPAER = "/elosipaer";

    // relator service
    String URL_RELATOR = "/relator";

    // taxonomia service
    String URL_TAXONOMIA = "/taxonomia";

    // categoria service
    String URL_CATEGORIA = "/categoria";

    // usuario service
    String URL_USUARIO = "/usuario";
    String URL_USUARIO_FIND_EMAIL = "/email";
    String URL_USUARIO_FIND_NOME = "/nome";

}
