package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.tools.GeneralToolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO make it simpler

@Service
public class GeneralToolService
        implements ToolService<AbstractTool> {

    private final GeneralToolRepository repository;

    public GeneralToolService(GeneralToolRepository repository) {
        this.repository = repository;
    }

    public List<CuttingTool> findAllCuttingTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(CuttingTool.class))
                .map(t -> (CuttingTool) t)
                .collect(Collectors.toList());
    }


    public List<SupportTool> findAllSupportTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(SupportTool.class))
                .map(t -> (SupportTool) t)
                .collect(Collectors.toList());
    }

    public List<MeasuringTool> findAllMeasuringTools() {
        return findAll().stream()
                .filter(t -> t.getClass().equals(MeasuringTool.class))
                .map(t -> (MeasuringTool) t)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractTool> findAll() {
        return (List<AbstractTool>) repository.findAll();
    }

    @Override
    public Optional<AbstractTool> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        if (findById(id).isPresent()
                && findById(id).get().getQuantity() == 0)
        repository.deleteById(id);
    }

    //this method never uses, you should save only specific Tool
    @Override
    public AbstractTool save(AbstractTool tool) {
        return repository.save(tool);
    }
}
