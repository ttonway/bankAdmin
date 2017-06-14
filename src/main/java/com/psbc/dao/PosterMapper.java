package com.psbc.dao;

import com.psbc.pojo.PosterImage;

import java.util.List;
import java.util.Map;

public interface PosterMapper {
    int deleteByPrimaryKey(Long posterId);

    int insert(PosterImage poster);

    PosterImage selectByPrimaryKey(Long posterId);

    List<PosterImage> selectByList(Map<String,Object> map);

    List<Map<String,Object>> selectByTypeCnt(Map<String,Object> map);

    int selectByCnt();
}