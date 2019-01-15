package com.vk.service.impl;

import com.vk.mapper.AreaMapper;
import com.vk.model.Area;
import com.vk.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by weijin on 2018/7/2.
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Area> getArea() {
        return areaMapper.getArea();
    }
}
