package com.ding.bean;

/**
 * @author daniel 2021-3-16 0016.
 */
public class MyResponse {
    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyResponse{");
        sb.append("status=").append(status);
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static MyResponse getSuccess(){
        MyResponse response=new MyResponse();
        response.setMsg("成功");
        response.setStatus(200);
        return response;
    }

    public static MyResponse getError(){
        MyResponse response=new MyResponse();
        response.setMsg("error");
        response.setStatus(500);
        return response;
    }
    public static MyResponse getException(){
        MyResponse response=new MyResponse();
        response.setMsg("Exception");
        response.setStatus(505);
        return response;
    }
}
