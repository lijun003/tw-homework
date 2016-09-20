package com.ifox.domain;

import java.util.List;

public class RequestBody {
    private String id;
    private List<Data> datas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }
}
