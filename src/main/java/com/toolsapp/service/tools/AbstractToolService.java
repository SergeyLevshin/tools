package com.toolsapp.service.tools;

import com.toolsapp.models.tools.AbstractTool;
import com.toolsapp.models.tools.CuttingTool;
import com.toolsapp.repository.tools.AbstractToolRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractToolService<E extends AbstractTool,
        R extends AbstractToolRepository<E>> implements ToolService<E>{

    protected final R repository;

    protected AbstractToolService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        repository.save(entity);
        return entity;
    }
    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    public Optional<E> findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id) {
        if (repository.findById(id).orElseThrow().getQuantityInUse() == 0)
        repository.deleteById(id);
        //TODO else throw exception
    }

    @Transactional
    public boolean checkToolQuantityFromTool(long toolId, int quantity) {
        E tool = repository.findById(toolId).orElseThrow();
        if (tool.getQuantity() >= quantity) {
            tool.setQuantity(tool.getQuantity() - quantity);
            tool.setQuantityInUse(tool.getQuantityInUse() + quantity);
            repository.save(tool);
            return true;
        }
        return false;
    }
    @Transactional
    public void changeQuantityInUse(long toolId, int quantity) {
        E tool = repository.findById(toolId).orElseThrow();
        tool.setQuantityInUse(tool.getQuantityInUse() - quantity);
        repository.save(tool);
    }

    @Transactional
    public Map<E, Integer> createToolMap(Map<Long, Integer> toolIdMap){
        Map<E, Integer> resultMap = new HashMap<>();
        for (Map.Entry<Long, Integer> tool : toolIdMap.entrySet()) {
            E newTool = repository.findById(tool.getKey()).orElseThrow();
            resultMap.put(newTool, tool.getValue());
        }
        return resultMap;
    }
}
