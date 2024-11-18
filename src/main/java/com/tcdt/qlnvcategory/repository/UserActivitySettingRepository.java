package com.tcdt.qlnvcategory.repository;

import com.tcdt.qlnvcategory.table.UserActivitySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivitySettingRepository extends JpaRepository<UserActivitySetting, Long> {
}
