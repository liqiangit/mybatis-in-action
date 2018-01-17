package com.corpus.test.mapper;


import com.corpus.test.po.CorpusBean;

import java.util.List;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public interface CorpusMapper {
    int insertCorpus(CorpusBean bean);
    int insertCorpusList(List<CorpusBean> corpusBeanList);
}
