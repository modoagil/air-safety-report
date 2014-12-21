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
package br.com.modoagil.asr.rest;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.validation.Valid;

import lombok.Getter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.modoagil.asr.model.AirSafetyAnswer;
import br.com.modoagil.asr.model.AirSafetyObservation;
import br.com.modoagil.asr.model.AirSafetyReport;
import br.com.modoagil.asr.model.Forwarding;
import br.com.modoagil.asr.model.RecommendedAction;
import br.com.modoagil.asr.model.RiskClassification;
import br.com.modoagil.asr.model.SectorOpinion;
import br.com.modoagil.asr.model.support.AbstractEntity;
import br.com.modoagil.asr.repository.AirSafetyAnswerRepository;
import br.com.modoagil.asr.repository.AirSafetyObservationRepository;
import br.com.modoagil.asr.repository.AirSafetyReportRepository;
import br.com.modoagil.asr.repository.ForwardingRepository;
import br.com.modoagil.asr.repository.RecommendedActionRepository;
import br.com.modoagil.asr.repository.RiskClassificationRepository;
import br.com.modoagil.asr.repository.SectorOpinionRepository;
import br.com.modoagil.asr.repository.support.GenericRepository;
import br.com.modoagil.asr.rest.support.GenericWebService;
import br.com.modoagil.asr.rest.support.Response;
import br.com.modoagil.asr.rest.support.ResponseBuilder;
import br.com.modoagil.asr.rest.support.ResponseMessages;
import br.com.modoagil.asr.rest.support.WebServicesURL;

/**
 * REST services for {@link RelatorioPrevencao}
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see GenericWebService
 */
@RestController
@RequestMapping(value = WebServicesURL.URL_RELPREV)
public class AirSafetyReportWebService extends GenericWebService<AirSafetyReport, AirSafetyReportRepository> {

    private static final String IDRELPREV = "idRelPrev";

    @Getter
    @Autowired
    private AirSafetyReportRepository repository;

    @Autowired
    private RecommendedActionRepository acaoRecomendadaRepository;

    @Autowired
    private RiskClassificationRepository classificacaoRiscoRepository;

    @Autowired
    private ForwardingRepository encaminhamentoRepository;

    @Autowired
    private AirSafetyObservationRepository observacaoRepository;

    @Autowired
    private SectorOpinionRepository parecerSetorRepository;

    @Autowired
    private AirSafetyAnswerRepository respostaRepository;

    /**
     * Busca por relatórios de prevenção que possuam o local informado
     *
     * @param local
     *            local a ser pesquisado nos relatórios de prevenção
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = WebServicesURL.URL_RELPREV_FIND_LOCAL + "/{local}", method = {GET, POST},
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> findRelPrevByLocal(@PathVariable("local") final String local) {
        Response<AirSafetyReport> response;
        getLogger().debug("listando relatórios de prevenção por local '" + local + "'");
        try {
            final List<AirSafetyReport> dataList = getRepository().findByLocalIgnoreCase(local);
            response = new ResponseBuilder<AirSafetyReport>().success(true).data(dataList)
                    .message(String.format(ResponseMessages.LIST_MESSAGE, dataList.size())).status(HttpStatus.OK)
                    .build();
            getLogger().debug("sucesso ao listar relatórios de prevenção por local '" + local + "'");
        } catch (final Exception e) {
            final String message = ExceptionUtils.getRootCauseMessage(e);
            response = new ResponseBuilder<AirSafetyReport>().success(false).message(message)
                    .status(HttpStatus.BAD_REQUEST).build();
            getLogger().error("erro ao listar relatórios de prevenção por local '" + local + "'", e);
        }
        return response;
    }

    /**
     * Busca por relatórios de prevenção que possuam a descrição informada
     *
     * @param descricao
     *            descrição a ser pesquisada nos relatórios de prevenção
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = WebServicesURL.URL_RELPREV_FIND_DESCRICAO + "/{descricao}", method = {GET, POST},
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> findRelPrevByDescricao(@PathVariable("descricao") final String descricao) {
        Response<AirSafetyReport> response;
        getLogger().debug("listando relatórios de prevenção por descrição '" + descricao + "'");
        try {
            final List<AirSafetyReport> dataList = getRepository().findByDescricaoSituacaoPerigosaContainsIgnoreCase(
                    descricao);
            response = new ResponseBuilder<AirSafetyReport>().success(true).data(dataList)
                    .message(String.format(ResponseMessages.LIST_MESSAGE, dataList.size())).status(HttpStatus.OK)
                    .build();
            getLogger().debug("sucesso ao listar relatórios de prevenção por descrição '" + descricao + "'");
        } catch (final Exception e) {
            final String message = ExceptionUtils.getRootCauseMessage(e);
            response = new ResponseBuilder<AirSafetyReport>().success(false).message(message)
                    .status(HttpStatus.BAD_REQUEST).build();
            getLogger().error("erro ao listar relatórios de prevenção por descrição '" + descricao + "'", e);
        }
        return response;
    }

    /**
     * Adiciona a um relatório de prevenção uma ação recomendada
     *
     * @param id
     *            id do relprev a ter a ação recomendada incluída
     * @param acaoRecomendada
     *            ação recomendada a ser incluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/acao", method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addAcaoRecomendada(@PathVariable("id") final Long id,
            @Valid @RequestBody final RecommendedAction acaoRecomendada) {
        return this.createInterationObject(id, acaoRecomendada, acaoRecomendadaRepository);
    }

    /**
     * Atualiza uma ação recomendada de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a ação recomendada atualizada
     * @param acaoRecomendada
     *            ação recomendada a ser atualizada
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/acao", method = PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateAcaoRecomendada(@PathVariable("id") final Long id,
            @RequestBody final RecommendedAction acaoRecomendada) {
        return this.updateInterationObject(id, acaoRecomendada, acaoRecomendadaRepository);
    }

    /**
     * Exclui uma ação recomendada de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a ação recomendada excluida
     * @param acaoRecomendada
     *            ação recomendada a ser excluida
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/acao/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteAcaoRecomendada(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, acaoRecomendadaRepository);
    }

    /**
     * Adiciona a um relatório de prevenção uma classifcação
     *
     * @param id
     *            id do relprev a ter a classifcação incluída
     * @param classificacao
     *            classifcação a ser incluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/classificacao", method = POST, consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addClassificacaoDeRisco(@PathVariable("id") final Long id,
            @RequestBody final RiskClassification classificacaoRisco) {
        return this.createInterationObject(id, classificacaoRisco, classificacaoRiscoRepository);
    }

    /**
     * Atualiza uma classificação de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a classifcação atualizada
     * @param classificacao
     *            classifcação a ser atualizada
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/classificacao", method = PUT, consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateClassificacaoDeRisco(@PathVariable("id") final Long id,
            @RequestBody final RiskClassification classificacaoRisco) {
        return this.updateInterationObject(id, classificacaoRisco, classificacaoRiscoRepository);
    }

    /**
     * Exclui uma classificação de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a classifcação excluida
     * @param classificacao
     *            classifcação a ser excluida
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/classificacao/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteClassificacaoDeRisco(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, classificacaoRiscoRepository);
    }

    /**
     * Atualiza um encaminhamento de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter o encaminhamento incluído
     * @param encaminhamento
     *            encaminhamento a ser incluído
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/encaminhamento", method = POST, consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addEncaminhamento(@PathVariable("id") final Long id,
            @RequestBody final Forwarding encaminhamento) {
        return this.createInterationObject(id, encaminhamento, encaminhamentoRepository);
    }

    /**
     * Adiciona a um relatório de prevenção um encaminhamento
     *
     * @param id
     *            id do relprev a ter o encaminhamento atualizado
     * @param encaminhamento
     *            encaminhamento a ser atualizado
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/encaminhamento", method = PUT, consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateEncaminhamento(@PathVariable("id") final Long id,
            @RequestBody final Forwarding encaminhamento) {
        return this.updateInterationObject(id, encaminhamento, encaminhamentoRepository);
    }

    /**
     * Exclui a um relatório de prevenção um encaminhamento
     *
     * @param id
     *            id do relprev a ter o encaminhamento excluido
     * @param encaminhamento
     *            encaminhamento a ser excluido
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/encaminhamento/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteEncaminhamento(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, encaminhamentoRepository);
    }

    /**
     * Adiciona a um relatório de prevenção uma observação
     *
     * @param id
     *            id do relprev a ter a observação incluída
     * @param observacao
     *            observação a ser incluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/observacao", method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addObservacao(@PathVariable("id") final Long id,
            @RequestBody final AirSafetyObservation observacao) {
        return this.createInterationObject(id, observacao, observacaoRepository);
    }

    /**
     * Atualiza a observação de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a observação atualizada
     * @param observacao
     *            observação a ser atualizada
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/observacao", method = PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateObservacao(@PathVariable("id") final Long id,
            @RequestBody final AirSafetyObservation observacao) {
        return this.updateInterationObject(id, observacao, observacaoRepository);
    }

    /**
     * Exclui a observação de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a observação excluída
     * @param observacao
     *            observação a ser excluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/observacao/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteObservacao(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, observacaoRepository);
    }

    /**
     * Adiciona a um relatório de prevenção o parecer do setor
     *
     * @param id
     *            id do relprev a ter o parecer do setor incluído
     * @param parecerSetor
     *            parecer do setor a ser incluído
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/parecer", method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addParecerSetor(@PathVariable("id") final Long id,
            @RequestBody final SectorOpinion parecerSetor) {
        return this.createInterationObject(id, parecerSetor, parecerSetorRepository);
    }

    /**
     * Atualiza o parecer do setor de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter o parecer do setor atualizado
     * @param parecerSetor
     *            parecer do setor a ser atualizado
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/parecer", method = PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateParecerSetor(@PathVariable("id") final Long id,
            @RequestBody final SectorOpinion parecerSetor) {
        return this.updateInterationObject(id, parecerSetor, parecerSetorRepository);
    }

    /**
     * Exclui o parecer do setor de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter o parecer do setor excluído
     * @param parecerSetor
     *            parecer do setor a ser excluído
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/parecer/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteParecerSetor(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, parecerSetorRepository);
    }

    /**
     * Adiciona a um relatório de prevenção uma resposta
     *
     * @param id
     *            id do relprev a ter a resposta incluída
     * @param resposta
     *            resposta a ser incluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/resposta", method = POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> addResposta(@PathVariable("id") final Long id,
            @RequestBody final AirSafetyAnswer resposta) {
        return this.createInterationObject(id, resposta, respostaRepository);
    }

    /**
     * Atualiza a resposta de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a resposta atualizada
     * @param resposta
     *            resposta a ser atualizada
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/resposta", method = PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> updateResposta(@PathVariable("id") final Long id,
            @RequestBody final AirSafetyAnswer resposta) {
        return this.updateInterationObject(id, resposta, respostaRepository);
    }

    /**
     * Exclui a resposta de um relatório de prevenção
     *
     * @param id
     *            id do relprev a ter a resposta excluída
     * @param resposta
     *            resposta a ser excluída
     * @return {@link Response}
     */
    @ResponseBody
    @RequestMapping(value = "/{idRelPrev}/resposta/{id}", method = DELETE, produces = APPLICATION_JSON)
    public Response<AirSafetyReport> deleteResposta(@PathVariable(IDRELPREV) final Long idRelPrev,
            @PathVariable("id") final Long id) {
        return this.deleteInterationObject(idRelPrev, id, respostaRepository);
    }

    /**
     * Cria o objeto da interação com o relatório de prevenção. A interação pode ser uma das seguintes:
     * <ul>
     * <li>{@link AcaoRecomendada}</li>
     * <li>{@link ClassificacaoRisco}</li>
     * <li>{@link Encaminhamento}</li>
     * <li>{@link Observacao}</li>
     * <li>{@link ParecerSetor}</li>
     * <li>{@link Resposta}</li>
     * </ul>
     *
     * @param relPrevID
     *            id do relatório de prevenção a ter a interação persistida
     * @param entity
     *            entidade da interação do relatório de prevenção a ser persistida
     * @param targetRepository
     *            repositório de dados da interação do relatório de prevenção
     * @return {@link Response}
     */
    private <E extends AbstractEntity, R extends GenericRepository<E, Long>> Response<AirSafetyReport> createInterationObject(
            final Long relPrevID, final E entity, final R targetRepository) {
        return this.persistInterationObject(relPrevID, entity, targetRepository);
    }

    /**
     * Atualiza o objeto da interação com o relatório de prevenção. A interação pode ser uma das seguintes:
     * <ul>
     * <li>{@link AcaoRecomendada}</li>
     * <li>{@link ClassificacaoRisco}</li>
     * <li>{@link Encaminhamento}</li>
     * <li>{@link Observacao}</li>
     * <li>{@link ParecerSetor}</li>
     * <li>{@link Resposta}</li>
     * </ul>
     *
     * @param relPrevID
     *            id do relatório de prevenção a ter a interação persistida
     * @param entity
     *            entidade da interação do relatório de prevenção a ser persistida
     * @param targetRepository
     *            repositório de dados da interação do relatório de prevenção
     * @return {@link Response}
     */
    private <E extends AbstractEntity, R extends GenericRepository<E, Long>> Response<AirSafetyReport> updateInterationObject(
            final Long relPrevID, final E entity, final R targetRepository) {
        return this.persistInterationObject(relPrevID, entity, targetRepository);
    }

    /**
     * Exclui o objeto da interação com o relatório de prevenção. A interação pode ser uma das seguintes:
     * <ul>
     * <li>{@link AcaoRecomendada}</li>
     * <li>{@link ClassificacaoRisco}</li>
     * <li>{@link Encaminhamento}</li>
     * <li>{@link Observacao}</li>
     * <li>{@link ParecerSetor}</li>
     * <li>{@link Resposta}</li>
     * </ul>
     *
     * @param idRelPrev
     *            id do relatório de prevenção
     * @param id
     *            id do objeto da entidade da interação do relatório de prevenção a ser excluído
     * @param targetRepository
     *            repositório de dados da interação do relatório de prevenção
     */
    private <E extends AbstractEntity, R extends GenericRepository<E, Long>> Response<AirSafetyReport> deleteInterationObject(
            final Long idRelPrev, final Long id, final R targetRepository) {
        // TODO implements
        return null;
    }

    private <E extends AbstractEntity, R extends GenericRepository<E, Long>> Response<AirSafetyReport> persistInterationObject(
            final Long relPrevID, @Valid final E entity, final R targetRepository) {
        // TODO implements
        return null;
    }

}
