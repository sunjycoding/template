package com.example.template.common.util;

import com.example.template.common.constants.AppConstants;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.UUID;

/**
 * @author created by sunjy on 12/21/23
 */
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return UUID.randomUUID().toString().replace(AppConstants.HYPHEN, AppConstants.EMPTY_STRING);
    }

}
