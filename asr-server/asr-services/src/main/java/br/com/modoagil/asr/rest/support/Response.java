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

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.modoagil.asr.model.support.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidade para representação de dados de resposta e também de chamadas ao endpoints REST
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 */
@JsonInclude(Include.NON_EMPTY)
public class Response<E extends AbstractEntity> implements Serializable {

    private static final long serialVersionUID = 415873352876691068L;

    @Getter
    @Setter
    @JsonProperty
    private Boolean success;

    @Getter
    @Setter
    @JsonProperty
    private List<E> data;

    @Setter
    @JsonProperty
    private Long count;

    @Getter
    @Setter
    @JsonProperty
    private String message;

    @Getter
    @Setter
    @JsonProperty
    private Integer status;

    public Long getCount() {
        return this.count != null ? this.count : this.getData() != null ? this.getData().size() : 0L;
    }

}
