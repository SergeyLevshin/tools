package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.repository.tools.AbstractToolRepository;

public abstract class AbstractToolService<E extends AbstractTool,
        R extends AbstractToolRepository<E>> implements ToolService<E>{
}
