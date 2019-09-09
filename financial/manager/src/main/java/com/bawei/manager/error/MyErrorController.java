package com.bawei.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyErrorController extends BasicErrorController {
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }


    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
            //删除不需要的属性
            Map<String, Object> attrs = super.getErrorAttributes(request, includeStackTrace);
            attrs.remove("timestamp");
            attrs.remove("status");
            attrs.remove("error");
            attrs.remove("exception");
            attrs.remove("path");
            String errorCode = (String) attrs.get("message");
            ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
            attrs.put("message", errorEnum.getMessage());
            attrs.put("code", errorEnum.getCode());
            attrs.put("canRetry", errorEnum.isCanRetry());

        return attrs;
    }
}
