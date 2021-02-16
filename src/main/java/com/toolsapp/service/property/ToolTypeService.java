package com.toolsapp.service.property;

import com.toolsapp.models.property.ToolType;
import com.toolsapp.repository.extra.property.ToolTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ToolTypeService extends AbstractPropertyService<ToolType, ToolTypeRepository> {

    public ToolTypeService(ToolTypeRepository repository) {
        super(repository);
    }
}
