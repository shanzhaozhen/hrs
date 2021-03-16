package com.sbgs.hrscommon.dto;

import java.util.List;

public interface TreeModel {

    Long getId();

    void setId(Long id);

    Long getPid();

    void setPid(Long pid);

    List<? extends TreeModel> getChildren();

    void setChildren(List<? extends TreeModel> children);

    Integer getPriority();

}
