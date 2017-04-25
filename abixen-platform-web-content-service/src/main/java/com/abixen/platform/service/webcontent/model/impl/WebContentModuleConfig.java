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

package com.abixen.platform.service.webcontent.model.impl;

import com.abixen.platform.common.model.audit.AuditingModel;
import com.abixen.platform.service.webcontent.model.web.WebContentModuleConfigWeb;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "web_content_module_config")
@SequenceGenerator(sequenceName = "web_content_module_config_seq", name = "web_content_module_config_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class WebContentModuleConfig extends AuditingModel implements WebContentModuleConfigWeb, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "web_content_module_config_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "module_id", nullable = false)
    private Long moduleId;

    @Column(name = "content_id", nullable = false)
    private Long contentId;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}
