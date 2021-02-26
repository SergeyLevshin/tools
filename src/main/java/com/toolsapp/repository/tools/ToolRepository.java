package com.toolsapp.repository.tools;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ToolRepository<E extends AbstractTool> extends CommonRepository<E> {

}
