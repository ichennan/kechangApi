package com.nglar.kechang.repository;

import com.nglar.kechang.entity.VipInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipInfoRepository extends JpaRepository<VipInfo, Integer> {
    VipInfo getByPhone(String phone);
}
