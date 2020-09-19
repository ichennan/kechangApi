package com.nglar.kechang.repository;

import com.nglar.kechang.entity.VipThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VipThirdPartyRepository extends JpaRepository<VipThirdParty, Integer> {
    VipThirdParty getByThirdPartyNameAndThirdPartyId(String thirdPartyName, String thirdPartyId);
    List<VipThirdParty> findByVipId(Integer vipId);
}
