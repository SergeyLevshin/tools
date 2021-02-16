package com.toolsapp.repository.extra.property;

import com.toolsapp.models.property.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractPropertyRepository<E extends Property> extends CrudRepository<E, Long> {
}
