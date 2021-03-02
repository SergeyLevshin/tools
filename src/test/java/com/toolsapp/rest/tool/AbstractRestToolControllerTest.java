package com.toolsapp.rest.tool;

import com.toolsapp.domain.tools.AbstractTool;
import com.toolsapp.service.CommonService;

public class AbstractRestToolControllerTest<E extends AbstractTool
        , S extends CommonService<E>>
        implements CommonRestToolControllerTest<E>{

    private final S service;

    public AbstractRestToolControllerTest(S service) {
        this.service = service;
    }


}
