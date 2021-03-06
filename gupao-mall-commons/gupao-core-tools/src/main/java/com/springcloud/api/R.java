package com.springcloud.api;

/**
 * 返回对象
 * 2020-07-28
 */
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R() {
    }

    public R(Builder<T> builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Builder<T>{
        private int code;
        private String msg;
        private T data;

        public Builder() {
        }

        public R buildCustomize(int code,String msg){
            this.code = code;
            this.msg = msg;
            return new R(this);
        }

        public R buildCustomize(String msg){
            this.code = -1;
            this.msg = msg;
            return new R(this);
        }

        public R buildOk(){
            this.code = 200;
            this.msg = "success";
            return new R(this);
        }

        public R buildFail(){
            this.code = -1;
            this.msg = "failed";
            return new R(this);
        }

        public Builder setData(T data){
            this.data = data;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
