package com.peam.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.peam.springsecurity.model.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = "from Notice n where CURDATE() BETWEEN n.noticeBegDt AND n.noticeEndDt")
    List<Notice> findAllActiveNotices();

}
