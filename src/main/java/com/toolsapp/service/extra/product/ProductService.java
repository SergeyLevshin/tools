package com.toolsapp.service.extra.product;

import com.toolsapp.domain.extra.Product;
import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.domain.tools.CuttingTool;
import com.toolsapp.domain.tools.MeasuringTool;
import com.toolsapp.domain.tools.SupportTool;
import com.toolsapp.repository.CommonRepository;
import com.toolsapp.service.AbstractCommonService;
import com.toolsapp.service.tools.common.GeneralToolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractCommonService<Product, CommonRepository<Product>> {

    private final GeneralToolService toolService;

    protected ProductService(CommonRepository<Product> repository, GeneralToolService toolService) {
        super(repository);
        this.toolService = toolService;
    }

    //There are only Tool methods below.

    public List<AbstractTool> getAllTools() {
        return toolService.findAllSortByName();
    }

    public List<SupportTool> getAllSupportTools() {
        return toolService.findAllSortByName().stream()
                .filter(tool -> tool.getClass().equals(SupportTool.class))
                .map(tool -> (SupportTool) tool)
                .collect(Collectors.toList());
    }


    public List<CuttingTool> getAllCuttingTools() {
        return toolService.findAllSortByName().stream()
                .filter(tool -> tool.getClass().equals(CuttingTool.class))
                .map(tool -> (CuttingTool) tool)
                .collect(Collectors.toList());
    }


    public List<MeasuringTool> getAllMeasuringTools() {
        return toolService.findAllSortByName().stream().
                filter(tool -> tool.getClass().equals(MeasuringTool.class))
                .map(tool -> (MeasuringTool) tool)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean addTool(long productId, long toolId) {
        Optional<AbstractTool> tool = toolService.findById(toolId);
        if (tool.isPresent()) {
            saveTool(productId, tool.get());
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional
    private void saveTool(long productId, AbstractTool tool) {
        if (findById(productId).isPresent()) {
            Product product = findById(productId).get();
            product.addTool(tool);
            save(product);
        }
    }
}
