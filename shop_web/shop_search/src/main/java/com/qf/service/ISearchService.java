package com.qf.service;

import com.qf.entity.Goods;
import com.qf.entity.Page;

import java.util.List;

public interface ISearchService {

    boolean insert(Goods goods);

    List<Goods> query(String keyword, Page page);
}
