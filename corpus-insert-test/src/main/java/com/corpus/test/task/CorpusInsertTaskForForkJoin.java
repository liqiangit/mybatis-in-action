package com.corpus.test.task;

import com.corpus.test.mapper.CorpusMapper;
import com.corpus.test.po.CorpusBean;
import com.corpus.test.util.JayCommonUtil;
import com.corpus.test.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by liutingna on 2017/12/11.
 *
 * @author liutingna
 */
public class CorpusInsertTaskForForkJoin extends RecursiveAction {
    private static List<CorpusBean> corpusBeanList;
    private static SqlSession sqlSession;
    private int startIndex;
    private int endIndex;

    public CorpusInsertTaskForForkJoin(List<CorpusBean> corpusBeans, SqlSession sqlsession) {
        corpusBeanList = corpusBeans;
        sqlSession = sqlsession;
    }

    @Override
    public void compute() {

        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
            try {
                for (int i = 0; i < corpusBeanList.size(); i++) {
                    corpusMapper.insertCorpus(corpusBeanList.get(i));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            /*
            while (corpusBeanList != null) {
                if (corpusBeanList.size() >= 1000) {
                    CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
                    try {
                        List<CorpusBean> corpusBeans = corpusBeanList.subList(0, 1000);
                        corpusBeanList = corpusBeanList.subList(1000, corpusBeanList.size());
                        for (int i = 0; i < corpusBeans.size(); i++) {
                            corpusMapper.insertCorpus(corpusBeans.get(i));
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
                    try {
//                    corpusMapper.insertCorpusList(corpusBeanList);
                        for (int i = 0; i < corpusBeanList.size(); i++) {
                            corpusMapper.insertCorpus(corpusBeanList.get(i));
                        }
                        corpusBeanList = null;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }*/
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sqlSession.commit();
            if (sqlSession != null) {
                try {
                    sqlSession.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
