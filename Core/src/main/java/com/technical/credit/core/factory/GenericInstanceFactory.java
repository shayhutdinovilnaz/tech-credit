package com.technical.credit.core.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GenericInstanceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(GenericInstanceFactory.class);

    public <T> T getInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            LOG.error("The generic instance factory couldn't create a instance. Required class : {}", clazz, e);
        }
        return null;
    }
}
