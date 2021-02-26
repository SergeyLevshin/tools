package com.toolsapp.service.property;

import com.toolsapp.domain.property.ToolProperty;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;

public abstract class AbstractPropertyService<E extends ToolProperty,
        R extends CommonRepository<E>> extends AbstractCommonService<E, R> {

    protected AbstractPropertyService(R repository) {
        super(repository);
    }
}
