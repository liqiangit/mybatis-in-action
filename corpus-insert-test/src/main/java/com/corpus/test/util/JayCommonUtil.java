package com.corpus.test.util;

/**
 * Created by liutingna on 2017/11/28.
 *
 * @author liutingna
 */


import com.corpus.test.po.CorpusBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用工具类
 * @author hetiewei(贺铁伟)
 *
 */
public class JayCommonUtil {


    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param list
     * @param len
     * @return
     */
    public static List<List<CorpusBean>> splitList(List<CorpusBean> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }

        List<List<CorpusBean>> result = new ArrayList<List<CorpusBean>>();


        int size = list.size();
        int count = (size + len - 1) / len;


        for (int i = 0; i < count; i++) {
            List<CorpusBean> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static void main(String[] args) {
        String path = "D:\\testData\\nlp\\0-DICT-CORE.TXT";
        List<CorpusBean> corpusBeans =null;
        try {
            corpusBeans = ReadCorpusUtil.read(path);
        } catch (Exception e) {
            System.out.println("Read file error: "+e);
        }

        List<List<CorpusBean>> listList = splitList(corpusBeans, 10000);
        for (List<CorpusBean> corpusBeans1 : listList) {
            System.out.println(corpusBeans1.size());
        }
    }

}
