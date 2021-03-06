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

package com.abixen.platform.core.domain.service.impl;

import com.abixen.platform.common.infrastructure.annotation.PlatformDomainService;
import com.abixen.platform.core.application.exception.UserActivationException;
import com.abixen.platform.core.application.form.UserSearchForm;
import com.abixen.platform.core.application.service.PasswordGeneratorService;
import com.abixen.platform.core.domain.model.User;
import com.abixen.platform.core.domain.repository.UserRepository;
import com.abixen.platform.core.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@PlatformDomainService
public class UserServiceImpl implements UserService {

    private static final int GENERATOR_LENGTH = 12;
    private static final int GENERATOR_NO_OF_CAPS_ALPHA = 2;
    private static final int GENERATOR_NO_OF_DIGITS = 8;
    private static final int GENERATOR_NO_OF_SPECIAL_CHARS = 2;

    private final UserRepository userRepository;
    private final PasswordGeneratorService passwordGeneratorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordGeneratorService passwordGeneratorService) {
        this.userRepository = userRepository;
        this.passwordGeneratorService = passwordGeneratorService;
    }

    @Override
    public User find(final Long id) {
        log.debug("find() - id: {}", id);

        return userRepository.findOne(id);
    }

    @Override
    public User find(final String username) {
        log.debug("find() - username: {}", username);

        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(final Pageable pageable, final UserSearchForm userSearchForm) {
        log.debug("findAll() - pageable: {}, userSearchForm: {}", pageable, userSearchForm);

        return userRepository.findAll(pageable, userSearchForm);
    }

    @Override
    public User create(final User user) {
        log.debug("create() - user: {}", user);

        return userRepository.save(user);
    }

    @Override
    public User update(final User user) {
        log.debug("update() - user: {}", user);

        return userRepository.save(user);
    }

    @Override
    public void delete(final Long id) {
        log.debug("delete() - id: {}", id);

        userRepository.delete(id);
    }

    @Override
    public void activate(final String userHashKey) {
        log.info("Activation user with hash key {}", userHashKey);

        final User user = userRepository.findByHashKey(userHashKey);

        if (user == null) {
            log.error("Cannot activate user with hash key {}. Wrong hash key.", userHashKey);
            throw new UserActivationException("Cannot activate user because a hash key is wrong.");
        }

        user.activate();

        userRepository.save(user);
    }

    @Override
    public String generatePassword() {
        return passwordGeneratorService.generate(GENERATOR_LENGTH, GENERATOR_NO_OF_CAPS_ALPHA, GENERATOR_NO_OF_DIGITS, GENERATOR_NO_OF_SPECIAL_CHARS);
    }

}