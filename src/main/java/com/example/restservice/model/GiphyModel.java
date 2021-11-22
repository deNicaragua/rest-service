package com.example.restservice.model;

public class GiphyModel {
     private Meta meta;
     private Data data;

    public GiphyModel(Meta meta) {
        this.meta = meta;
    }

    public GiphyModel() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }



    public static class Meta {

        private String msg;
        private Integer status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }

    public static class Data {
        private String type;
        private String id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return "https://media0.giphy.com/media/" + id + "/giphy.gif";
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

