package com.ding.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author daniel 2021-5-31 0031.
 *//*
@Configuration
public class SentinelConfig   implements BlockExceptionHandler {
    *//**
     * 全局异常时，临时使用
     * @param request
     * @param response
     * @param e
     * @throws Exception
     *//*
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        BaseResponse errorResponse = new BaseResponse();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
        } else if (e instanceof DegradeException) {
            errorResponse.setMsg("服务降级了");
            errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
        } else if (e instanceof ParamFlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
        } else if (e instanceof SystemBlockException) {
            errorResponse.setMsg("被系统保护了");
            errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
        } else if (e instanceof AuthorityException) {
            errorResponse.setMsg("授权被限制");
            errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
        }
        response.setStatus(200);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(new Gson().toJson(errorResponse));
    }
}
*/