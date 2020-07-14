package com.ding.study.util;

import com.google.gson.annotations.Expose;

/**
 * @author daniel 2020-7-14 0014.
 */
public class GsonBean {

    /**
     * 是否可被序列化(Bean>Json)和是否可被反序列化(Json>Bean)
     */
    @Expose(serialize = false, deserialize = false)
    private String serialize;


    private String name;

    private Long productId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSerialize() {
        return serialize;
    }

    public void setSerialize(String serialize) {
        this.serialize = serialize;
    }
}
