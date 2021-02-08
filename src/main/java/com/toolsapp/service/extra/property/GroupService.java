package com.toolsapp.service.extra.property;

import com.toolsapp.models.extra.property.Group;
import com.toolsapp.repository.extra.property.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService extends AbstractPropertyService<Group, GroupRepository> {

    public GroupService(GroupRepository repository) {
        super(repository);
    }
}
