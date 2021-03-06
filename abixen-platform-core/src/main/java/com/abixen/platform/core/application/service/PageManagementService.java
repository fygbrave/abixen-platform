/**
 * Copyright (c) 2010-present Abixen Systems. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.abixen.platform.core.application.service;

import com.abixen.platform.core.application.dto.PageDto;
import com.abixen.platform.core.application.form.PageForm;
import com.abixen.platform.core.application.form.PageSearchForm;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PageManagementService {

    PageDto findPage(Long id);

    List<PageDto> findAllPages();

    org.springframework.data.domain.Page<PageDto> findAllPages(Pageable pageable, PageSearchForm pageSearchForm);

    PageForm createPage(PageForm pageForm);

    PageForm updatePage(PageForm pageForm);

    void deletePage(Long id);

}