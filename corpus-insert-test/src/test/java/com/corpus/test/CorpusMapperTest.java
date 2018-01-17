package com.corpus.test;

import com.corpus.test.mapper.CorpusMapper;
import com.corpus.test.po.CorpusBean;
import com.corpus.test.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class CorpusMapperTest {
    @Test
    public void test1Insert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();

            CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
            CorpusBean corpusBean = new CorpusBean();
            corpusBean.setWord("a");
            corpusBean.setClassify("bbb");
            corpusBean.setFrequncy("2");
            int result = corpusMapper.insertCorpus(corpusBean);
            Assert.assertEquals(1, result);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void test1InsertList() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();

            CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
            CorpusBean corpusBean = new CorpusBean();
            corpusBean.setWord("a");
            corpusBean.setClassify("bbb");
            corpusBean.setFrequncy("2");
            List<CorpusBean> corpusBeanList = new ArrayList<>();
            corpusBeanList.add(corpusBean);
            int result = corpusMapper.insertCorpusList(corpusBeanList);
//            Assert.assertEquals(1, result);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
