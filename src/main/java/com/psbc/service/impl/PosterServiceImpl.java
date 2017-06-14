package com.psbc.service.impl;

import com.psbc.dao.PosterMapper;
import com.psbc.pojo.PosterImage;
import com.psbc.service.PosterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PosterServiceImpl implements PosterService {

    @Resource
    private PosterMapper posterMapper;

    @Override
    public int deleteByPrimaryKey(Long posterId) {
        return posterMapper.deleteByPrimaryKey(posterId);
    }

    @Override
    public int insert(PosterImage poster) {
        return posterMapper.insert(poster);
    }

    @Override
    public PosterImage selectByPrimaryKey(Long posterId) {
        return posterMapper.selectByPrimaryKey(posterId);
    }

    @Override
    public List<PosterImage> selectByList(Map<String,Object> map) {
        return posterMapper.selectByList(map);
    }

    @Override
    public List<Map<String,Object>> selectByTypeCnt(Map<String,Object> map) {
        return posterMapper.selectByTypeCnt(map);
    }

    @Override
    public int selectByCnt() {
        return posterMapper.selectByCnt();
    }
}
