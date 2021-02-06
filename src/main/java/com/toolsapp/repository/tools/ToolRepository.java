package com.toolsapp.repository.tools;

import com.toolsapp.models.tools.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ToolRepository<E extends Tool> extends CrudRepository<E, Long> {
}
