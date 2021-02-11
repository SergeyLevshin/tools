package com.toolsapp.service.tools;

import com.toolsapp.models.tools.Accessory;
import com.toolsapp.repository.tools.AccessoryRepository;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService extends AbstractToolService<Accessory, AccessoryRepository> {

    public AccessoryService(AccessoryRepository repository) {
        super(repository);
    }
}
