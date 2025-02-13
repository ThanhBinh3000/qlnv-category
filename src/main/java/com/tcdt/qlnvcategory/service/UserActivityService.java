package com.tcdt.qlnvcategory.service;

import com.tcdt.qlnvcategory.repository.UserActivityRepository;
import com.tcdt.qlnvcategory.table.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserActivityService {
    @Autowired
    private UserActivityRepository userActivityRepository;

    private static Logger log = LoggerFactory.getLogger(UserActivityService.class);

    public UserActivity log(UserActivity activity) {
        try {
            activity.setNgayTao(new Date());
            return userActivityRepository.save(activity);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }
}