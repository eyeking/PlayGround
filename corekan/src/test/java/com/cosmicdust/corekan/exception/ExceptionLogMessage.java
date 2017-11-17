package com.cosmicdust.corekan.exception;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogMessage {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionLogMessage.class.getName());
    public static void main(String[] args) {
        try {
            BigDecimal number = null;
            number.abs();
        } catch (RuntimeException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
