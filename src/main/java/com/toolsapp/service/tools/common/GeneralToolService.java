package com.toolsapp.service.tools.common;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.extra.WorkerRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralToolService extends AbstractCommonService<AbstractTool, CommonRepository<AbstractTool>> {

    private final WorkerRepository workerRepository;

    public GeneralToolService(CommonRepository<AbstractTool> repository, WorkerRepository workerRepository) {
        super(repository);
        this.workerRepository = workerRepository;
    }

    @Transactional
    public boolean deleteTool(long toolId) {
        Optional<AbstractTool> tool = findById(toolId);
        if (tool.isPresent()) {
            if (isToolFound(tool.get())) {
                return false;
            }
        }
        return deleteById(toolId);
    }

    @Transactional
    private boolean isToolFound(AbstractTool tool) {
        List<Worker> workers = (List<Worker>) workerRepository.findAll();
        boolean isContains = false;
        for (Worker worker : workers) {
            if (worker.getTools().containsKey(tool)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}
