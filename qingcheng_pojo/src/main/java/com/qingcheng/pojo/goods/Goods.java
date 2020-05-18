package com.qingcheng.pojo.goods;

import java.io.Serializable;
import java.util.List;

//组合实体类
public class Goods implements Serializable {

    //一个商品信息
    private Spu spu;

    //多个商品 规格参数 信息
    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
