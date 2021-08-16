package com.atguigu.crowd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装给前端的信息(返回统一类型json数据
 *
 * @author guyao
 * @create 2021-08-01 19:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    /**
     * 封装当前请求结果
     */
    private String result;

    /**
     * 失败信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回成功无数据的信息
     *
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<E>(SUCCESS, null, null);

    }

    /**
     * 返回成功含数据的信息
     *
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, null, data);

    }


    /**
     * 请求返回失败信息
     *
     * @param message 失败的错误消息
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> fail(String message) {
        return new ResultEntity<E>(FAIL, message, null);

    }
}
