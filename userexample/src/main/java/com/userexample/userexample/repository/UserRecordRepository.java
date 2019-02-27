package com.userexample.userexample.repository;

import com.userexample.userexample.bean.News;
import com.userexample.userexample.bean.URClass;
import com.userexample.userexample.bean.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

public interface UserRecordRepository extends JpaRepository<UserRecord, URClass> {
    @Query(value = "select * from UserRecord where name = ?1 order by time desc", nativeQuery = true)
    List<UserRecord> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into UserRecord(name, newsID, news, time) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addUserRecord(String name, Integer newsID, String news, String time);

    @Query(value = "select * from UserRecord where name = ?1 and news = ?2", nativeQuery = true)
    List<UserRecord> findByID(String name, String news);

    @Query(value = "select count(*) from UserRecord where name = ?1", nativeQuery = true)
    Integer getColumn(String name);

    @Query(value = "select newsID from UserRecord where name = ?1", nativeQuery = true)
    List<Integer> getRecord(String name);
}
