package com.toolsapp.service.extra.property;

import com.toolsapp.models.extra.property.Producer;
import com.toolsapp.repository.extra.property.ProducerRepository;
import org.springframework.stereotype.Service;

@Service
public class ProducerService extends AbstractPropertyService<Producer, ProducerRepository> {

    public ProducerService(ProducerRepository repository) {
        super(repository);
    }
}
