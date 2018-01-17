package com.corpus.test.util;


import com.corpus.test.po.CorpusBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */
public class ReadCorpusUtil {
    public static List<CorpusBean> read(String path) throws Exception {
        List<CorpusBean> corpusBeans = new ArrayList<>();
        FileReader fileReader = new FileReader(new File(path));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] segs = line.split("\\s");
            CorpusBean corpusBean = new CorpusBean();
            try {
                corpusBean.setWord(segs[0]);
                corpusBean.setClassify(segs[1]);
                corpusBean.setFrequncy(segs[2]);
                corpusBeans.add(corpusBean);
            } catch (Exception e) {
                System.out.println(e);
            }
            line = bufferedReader.readLine();
        }
        return corpusBeans;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        String path = "D:\\testData\\nlp\\0-DICT-CORE.TXT";
        List<CorpusBean> corpusBeans =null;
        try {
            corpusBeans = read(path);
        } catch (Exception e) {
            System.out.println("Read file error: "+e);
        }
        System.out.println(corpusBeans.size());

        /*SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            CorpusMapper corpusMapper = sqlSession.getMapper(CorpusMapper.class);
            for (CorpusBean corpusBean : corpusBeans) {
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
        }
        long end = System.currentTimeMillis();
        System.out.println("end-start："+(end-start)+"ms");*/

    }
}
