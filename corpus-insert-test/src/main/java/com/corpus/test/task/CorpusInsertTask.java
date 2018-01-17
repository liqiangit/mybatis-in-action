package com.corpus.test.task;

import com.corpus.test.mapper.CorpusMapper;
import com.corpus.test.po.CorpusBean;
import com.corpus.test.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class CorpusInsertTask implements Runnable {
    private List<CorpusBean> corpusBeanList;
    private CountDownLatch countDownLatch;
    private SqlSession sqlSession;

    public CorpusInsertTask(List<CorpusBean> corpusBeans, CountDownLatch countDownLatch, SqlSession sqlSession) {
        this.corpusBeanList = corpusBeans;
        this.countDownLatch = countDownLatch;
        this.sqlSession = sqlSession;
    }

    @Override
    public void run(){
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
           CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
             /*try {
                corpusMapper.insertCorpusList(corpusBeanList);
            } catch (Exception e) {
                System.out.println(e);
            }*/
            for (CorpusBean corpusBean:corpusBeanList) {
                try {
                    corpusMapper.insertCorpus(corpusBean);
                } catch (Exception e) {
                    System.out.println("Insert error: "+e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            if (sqlSession != null) {
                sqlSession.close();
            }
            countDownLatch.countDown();
        }
    }
}
