package com.toolsapp.repository.extra.property;

import com.toolsapp.models.property.ToolProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PropertyRepository<E extends ToolProperty> extends CrudRepository<E, Long> {
}
