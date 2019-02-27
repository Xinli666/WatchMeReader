package com.userexample.userexample.repository;

import com.userexample.userexample.bean.Word;
import com.userexample.userexample.bean.WordPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, WordPK> {

    //得到所有文档列表
    @Query(value = "select distinct newsID from news_keywords",nativeQuery = true)
    List<Integer> finAllDoc();

    //得到所有分词列表
    @Query(value = "select distinct keywords from news_keywords",nativeQuery = true)
    List<String> findAllWord();
}
