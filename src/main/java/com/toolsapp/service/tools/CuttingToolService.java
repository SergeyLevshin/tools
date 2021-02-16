package com.toolsapp.service.tools;

import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.tools.CuttingToolsRepository;
import com.toolsapp.service.extra.WorkerService;
import org.springframework.stereotype.Service;

@Service
public class CuttingToolService extends AbstractToolService<CuttingTool, CuttingToolsRepository>  {

    public CuttingToolService(CuttingToolsRepository repository) {
        super(repository);
    }
}
