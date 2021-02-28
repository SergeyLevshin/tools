package com.toolsapp.service.tools.common;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.repository.extra.WorkerRepository;
import com.toolsapp.service.AbstractCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralToolService extends AbstractCommonService<AbstractTool, CommonRepository<AbstractTool>> {

    private final WorkerRepository workerRepository;

    public GeneralToolService(CommonRepository<AbstractTool> repository, WorkerRepository workerRepository) {
        super(repository);
        this.workerRepository = workerRepository;
    }

    public List<? extends AbstractTool> findAllTools(){
        return findAllSortByName();
    }

    @Transactional
    public List<CuttingTool> findAllCuttingTools() {
        return findAllSortByName().stream()
                .filter(t -> t.getClass().equals(CuttingTool.class))
                .map(t -> (CuttingTool) t)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SupportTool> findAllSupportTools() {
        return findAllSortByName().stream()
                .filter(t -> t.getClass().equals(SupportTool.class))
                .map(t -> (SupportTool) t)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MeasuringTool> findAllMeasuringTools() {
        return findAllSortByName().stream()
                .filter(t -> t.getClass().equals(MeasuringTool.class))
                .map(t -> (MeasuringTool) t)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTool(long toolId) {
        AbstractTool tool = findById(toolId).get();
        List<Worker> workers = (List<Worker>) workerRepository.findAll();
        boolean isContains = true;
        for (Worker worker : workers) {
            if (worker.getTools().containsKey(tool)) {
                isContains = false;
                break;
            }
        }
        if (!isContains) {
            deleteById(toolId);
        }
    }
}
