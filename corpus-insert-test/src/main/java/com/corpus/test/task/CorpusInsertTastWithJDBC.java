package com.corpus.test.task;

import com.corpus.test.mapper.CorpusMapper;
import com.corpus.test.po.CorpusBean;
import com.corpus.test.util.ConnectionPool;
import com.corpus.test.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class CorpusInsertTastWithJDBC implements Runnable{
    private List<CorpusBean> corpusBeanList;
    private CountDownLatch countDownLatch;
    PreparedStatement preparedStatement;

    public CorpusInsertTastWithJDBC(List<CorpusBean> corpusBeans, CountDownLatch countDownLatch) {

        this.corpusBeanList = corpusBeans;
        this.countDownLatch = countDownLatch;

        try {
            Connection connection = ConnectionPool.getc3p0Connection();
            String sql = "INSERT INTO corpus (word,classify,frequncy) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void run(){
        try {
            for (CorpusBean corpusBean:corpusBeanList) {
                try {
                    insert( corpusBean);
                } catch (Exception e) {
                    System.out.println("Insert error: "+e);
                }
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        countDownLatch.countDown();
    }

    private void insert(CorpusBean corpusBean) throws Exception{
        preparedStatement.setNString(1, corpusBean.getWord());
        preparedStatement.setNString(2, corpusBean.getClassify());
        preparedStatement.setNString(3, corpusBean.getFrequncy());
        preparedStatement.addBatch();
    }
}
