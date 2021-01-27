package com.toolsapp.service;

import com.toolsapp.models.instrument.CuttingTool;
import com.toolsapp.repository.CuttingToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final CuttingToolsRepository cuttingToolsRepo;

    @Autowired
    public UserServiceImpl(CuttingToolsRepository cuttingToolsRepo) {
        this.cuttingToolsRepo = cuttingToolsRepo;
    }

    @Override
    public List<CuttingTool> findAll() {
        return (List<CuttingTool>) cuttingToolsRepo.findAll();
    }

    @Override
    public Optional<CuttingTool> save(CuttingTool cuttingTool) {
        return Optional.of(cuttingToolsRepo.save(cuttingTool));
    }
}
