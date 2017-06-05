package com.psbc.service;

import com.psbc.pojo.PosterImage;

import java.util.List;

public interface PosterService {
    int deleteByPrimaryKey(Long posterId);

    int insert(PosterImage poster);

    PosterImage selectByPrimaryKey(Long posterId);

    List<PosterImage> selectAll();

    int selectByCnt();
}