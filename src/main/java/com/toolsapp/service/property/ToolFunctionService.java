package com.toolsapp.service.property;

import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.repository.CommonRepository;
import org.springframework.stereotype.Service;

@Service
public class ToolFunctionService extends AbstractPropertyService<ToolFunction, CommonRepository<ToolFunction>> {

    public ToolFunctionService(CommonRepository<ToolFunction> repository) {
        super(repository);
    }
}
