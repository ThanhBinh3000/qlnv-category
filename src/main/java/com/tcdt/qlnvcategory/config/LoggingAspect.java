package com.tcdt.qlnvcategory.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tcdt.qlnvcategory.jwt.CustomUserDetails;
import com.tcdt.qlnvcategory.service.UserActivityService;
import com.tcdt.qlnvcategory.service.UserActivitySettingService;
import com.tcdt.qlnvcategory.table.UserActivity;
import com.tcdt.qlnvcategory.table.UserActivitySetting;
import com.tcdt.qlnvcategory.util.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final static String SYSTEM = "category";

    @Autowired
    private Gson gson;
    @Autowired
    private UserActivityService userActivityService;
    @Autowired
    private UserActivitySettingService userActivitySettingService;

    @Pointcut("within(com.tcdt.qlnvcategory.controller.*) && bean(*Controller))")
    public void v3Controller() {
    }

    @Before("v3Controller()")
    public void logBefore(JoinPoint joinPoint) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String userAgent = request.getHeader("User-Agent");
            if (ObjectUtils.isEmpty(userAgent)) {
                userAgent = request.getHeader("user-agent");
            }
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(userAgent);
            if (m.find()) {
                userAgent = m.group(1);
            }
            if (!String.class.equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass())) {
                Boolean isAllow = getAllow(request);
                if (isAllow) {
                    CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    UserActivity entity = new UserActivity();
                    entity.setIp(BeanUtils.getClientIpAddress(request));
                    entity.setRequestMethod(request.getMethod());
                    entity.setRequestUrl(request.getRequestURI());
                    entity.setUserId(user.getUser().getId());
                    entity.setSystem(SYSTEM);
                    entity.setUserAgent(userAgent);
                    entity.setRequestBody(getBody(joinPoint.getArgs()));
                    entity.setUserName(user.getUser().getUsername());
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    if (parameterMap != null && !parameterMap.isEmpty()) {
                        entity.setRequestParameter(gson.toJson(parameterMap));
                    }
                    userActivityService.log(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getAllow(HttpServletRequest request) throws Exception {
        String type = "SERVICE";
        if ("/login".equalsIgnoreCase(request.getRequestURI())) {
            type = "LOGIN";
        }
        if ("/user-info/logout".equalsIgnoreCase(request.getRequestURI())) {
            type = "LOGOUT";
        }
        Boolean isAllow = false;
        UserActivitySetting setting = userActivitySettingService.getSetting();
        switch (type) {
            case "LOGIN":
                isAllow = setting.getWriteLogLogin();
                break;
            case "LOGOUT":
                isAllow = setting.getWriteLogLogout();
                break;
            case "SERVICE":
                isAllow = setting.getWriteLogUserActivity();
                break;
            default:
                break;
        }
        return isAllow;
    }

    public String getBody(Object[] args) {
        try {
            List<Object> lst = new ArrayList<>();
            for (Object a : args) {
                if (!(a instanceof HttpServletRequest) && !(a instanceof HttpServletResponse) && !(a instanceof CustomUserDetails)) {
                    lst.add(a);
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(lst);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
