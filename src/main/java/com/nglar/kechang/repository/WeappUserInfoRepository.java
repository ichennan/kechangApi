package com.nglar.kechang.repository;

import com.nglar.kechang.entity.WeappUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeappUserInfoRepository extends JpaRepository<WeappUserInfo, Integer> {
    WeappUserInfo getByVipId(Integer vipId);
}
