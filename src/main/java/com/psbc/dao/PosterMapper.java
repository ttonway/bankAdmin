package com.psbc.dao;

import com.psbc.pojo.PosterImage;

import java.util.List;
import java.util.Map;

public interface PosterMapper {
    int deleteByPrimaryKey(Long posterId);

    int insert(PosterImage poster);

    PosterImage selectByPrimaryKey(Long posterId);

    List<PosterImage> selectAll();

    int selectByCnt();
}