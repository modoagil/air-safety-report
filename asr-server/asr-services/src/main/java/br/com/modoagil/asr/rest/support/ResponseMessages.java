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
 * Mensagens para retorno na resposta das chamadas aos Serviços
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
public interface ResponseMessages {

    String CREATE_MESSAGE = "Objeto criado com Sucesso";
    String UPDATE_MESSAGE = "Objeto de id %s atualizado com Sucesso";
    String DELETE_MESSAGE = "Objeto de id %s removido com Sucesso";

    String NOTFOUND_UPDATE_MESSAGE = "Objeto com id %s não foi encontrado para ser atualizado";
    String NOTFOUND_DELETE_MESSAGE = "Objeto com id %s não foi encontrado para ser removido";

    String FIND_MESSAGE = "Objeto com id %s consultado com Sucesso";
    String LIST_MESSAGE = "%s objeto(s) listado(s) com Sucesso";

    String NOTFOUND_MESSAGE = "Objeto com id %s é inexistente na base de Dados";
    String NOTFOUND_LIST_MESSAGE = "Não existe(m) objeto(s) a ser(em) Listado(s)";

}
