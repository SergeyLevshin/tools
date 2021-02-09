package com.toolsapp.repository.tools;

import com.toolsapp.models.tools.AbstractTool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractToolRepository<E extends AbstractTool> extends CrudRepository<E, Long> {
}
