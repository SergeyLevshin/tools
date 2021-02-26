package com.toolsapp.service.property;

import com.toolsapp.domain.property.Producer;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;

@Service
public class ProducerService
        extends AbstractCommonService<Producer, CommonRepository<Producer>> {

    public ProducerService(CommonRepository<Producer> repository) {
        super(repository);
    }
}
