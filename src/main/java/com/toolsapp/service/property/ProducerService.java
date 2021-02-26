package com.toolsapp.service.property;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.repository.CommonRepository;
import org.springframework.stereotype.Service;

@Service
public class ProducerService
        extends AbstractPropertyService<Producer, CommonRepository<Producer>> {

    public ProducerService(CommonRepository<Producer> repository) {
        super(repository);
    }
}
