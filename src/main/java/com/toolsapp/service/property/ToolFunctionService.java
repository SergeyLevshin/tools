package com.toolsapp.service.property;

import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class ToolFunctionService extends AbstractCommonService<ToolFunction, CommonRepository<ToolFunction>> {

    public ToolFunctionService(CommonRepository<ToolFunction> repository) {
        super(repository);
    }
}
