package com.toolsapp.repository.tools;

import com.toolsapp.domain.tools.AbstractTool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ToolRepository<E extends AbstractTool> extends CrudRepository<E, Long> {
}
