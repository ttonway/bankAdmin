package com.psbc.service.Impl;

import com.psbc.dao.PosterMapper;
import com.psbc.pojo.PosterImage;
import com.psbc.service.PosterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public List<PosterImage> selectAll() {
        return posterMapper.selectAll();
    }

    @Override
    public int selectByCnt() {
        return posterMapper.selectByCnt();
    }
}
