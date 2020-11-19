package com.ding.controller;

import com.ding.bean.SendReq;
import com.google.gson.Gson;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author daniel 2020-11-19 0019.
 */

@RestController
@RequestMapping(value = "/vv")
public class ValidController {
    @PostMapping("/bbb/aaa")
    public String riskAssessShowQuestion(@Valid @RequestBody SendReq req, BindingResult bindingResult)   {
        // 基础数据验证
        if (null != bindingResult && bindingResult.hasErrors()) {
            List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();
            System.out.println(ValidController.getRequestErrorMessage(fieldErrorsList.get(0)));
        }
        return "";
    }
    public static String getRequestErrorMessage(FieldError error) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(error.getField());
            sb.append(error.getDefaultMessage());
        } catch (Exception e) {

        }
        return sb.toString();
    }

}
