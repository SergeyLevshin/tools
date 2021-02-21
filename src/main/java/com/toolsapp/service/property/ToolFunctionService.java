package com.toolsapp.service.property;

import com.toolsapp.domain.property.ToolFunction;
import com.toolsapp.repository.extra.property.ToolFunctionRepository;
import org.springframework.stereotype.Service;

@Service
public class ToolFunctionService extends AbstractPropertyService<ToolFunction, ToolFunctionRepository> {

    public ToolFunctionService(ToolFunctionRepository repository) {
        super(repository);
    }
}
