package com.corpus.test;


import com.corpus.test.po.CorpusBean;
import com.corpus.test.task.CorpusInsertTask;
import com.corpus.test.task.CorpusInsertTaskForForkJoin;
import com.corpus.test.task.CorpusInsertTastWithJDBC;
import com.corpus.test.util.JayCommonUtil;
import com.corpus.test.util.ReadCorpusUtil;
import com.corpus.test.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class App {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //样例数据见demoData/demo.txt
        String path = "D:\\testData\\nlp\\0-DICT-CORE.TXT";
//        String path = "D:\\testData\\nlp\\0-DICT-CORE-20w.TXT";
        List<CorpusBean> corpusBeanList = ReadCorpusUtil.read(path);
        long endRead = System.currentTimeMillis();
        System.out.println("Read File Consume: " + (endRead - start) + "ms");

        List<List<CorpusBean>> listList = JayCommonUtil.splitList(corpusBeanList, 10000);
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();

        CountDownLatch countDownLatch = new CountDownLatch(listList.size());
        ExecutorService executorService = Executors.newCachedThreadPool();

//        runAsForkJoin(listList, sqlSession);

//        runAsJdbc(listList, executorService, countDownLatch);

        runAsTask(listList, executorService, sqlSession, countDownLatch);

        long endInsert = System.currentTimeMillis();
        System.out.println("All consume: " + (endInsert - start) + "ms");
    }

    /**
     * 使用ForkJoin框架运行任务
     *
     * @param listList
     * @param sqlSession
     */
    public static void runAsForkJoin(List<List<CorpusBean>> listList, SqlSession sqlSession) {
        for (List<CorpusBean> corpusBeans : listList) {
            CorpusInsertTaskForForkJoin forForkJoin = new CorpusInsertTaskForForkJoin(corpusBeans, sqlSession);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.execute(forForkJoin);
            forForkJoin.join();
            if (forForkJoin.isCompletedAbnormally()) {
                System.out.println(forForkJoin.getException());
            }
        }
        sqlSession.commit();
    }

    /**
     * 使用JDBC运行任务
     *
     * @param listList
     * @param executorService
     * @param countDownLatch
     * @throws Exception
     */
    public static void runAsJdbc(List<List<CorpusBean>> listList, ExecutorService executorService, CountDownLatch countDownLatch) throws Exception {
        for (List<CorpusBean> corpusBeans : listList) {
            executorService.execute(new CorpusInsertTastWithJDBC(corpusBeans, countDownLatch));
        }
        countDownLatch.await();
    }

    /**
     * 使用多线程运行任务
     * @param listList
     * @param executorService
     * @param sqlSession
     * @param countDownLatch
     * @throws Exception
     */
    public static void runAsTask(List<List<CorpusBean>> listList, ExecutorService executorService, SqlSession sqlSession, CountDownLatch countDownLatch) throws Exception {
        for (List<CorpusBean> corpusBeans : listList) {
            executorService.execute(new CorpusInsertTask(corpusBeans, countDownLatch, sqlSession));
        }
        countDownLatch.await();
    }
}

