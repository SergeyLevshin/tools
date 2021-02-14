package com.toolsapp.service.extra.property;

import com.toolsapp.models.extra.property.ToolType;
import com.toolsapp.repository.extra.property.ToolTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ToolTypeService extends AbstractPropertyService<ToolType, ToolTypeRepository> {

    public ToolTypeService(ToolTypeRepository repository) {
        super(repository);
    }
}
