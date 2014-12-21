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

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.modoagil.asr.model.Category;
import br.com.modoagil.asr.repository.CategoryRepository;
import br.com.modoagil.asr.rest.support.GenericWebService;
import br.com.modoagil.asr.rest.support.WebServicesURL;

/**
 * REST services for {@link Category}
 *
 * @created 18/12/2014
 * @author Bruno César Ribeiro e Silva - <a href="mailto:bruno@brunocesar.com">bruno@brunocesar.com</a>
 * @see GenericWebService
 */
@RestController
@RequestMapping(value = WebServicesURL.URL_CATEGORIA)
public class CategoryWebService extends GenericWebService<Category, CategoryRepository> {

    @Getter
    @Autowired
    private CategoryRepository repository;

}
