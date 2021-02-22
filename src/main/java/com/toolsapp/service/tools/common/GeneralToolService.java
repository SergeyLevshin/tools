package com.toolsapp.service.tools.common;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.tools.GeneralToolRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO make it simpler

@Service
public class GeneralToolService
        implements ToolService<AbstractTool> {

    private final GeneralToolRepository repository;

    public GeneralToolService(GeneralToolRepository repository) {
        this.repository = repository;
    }

    public List<CuttingTool> findAllCuttingTools() {
        return null;
    }


    public List<SupportTool> findAllSupportTools() {
        return null;
    }

    public List<MeasuringTool> findAllMeasuringTools() {
        return null;
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

    @Override
    public void save(AbstractTool tool) {

    }
}
