package com.sbgs.hrscommon.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.sbgs.hrscommon.enums.JwtErrorConst;
import com.sbgs.hrscommon.enums.ResultType;

import java.util.function.Function;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "API公共返回对象")
@Slf4j
public class ResultBody<T> {

    @Schema(name = "code", title = "业务状态码")
    private Integer code;

    @Schema(name = "message", title = "返回的信息")
    private String message;

    @Schema(name = "data", title = "返回的数据")
    private T data;

    @Schema(name = "timestamp", title = "请求完成的时间")
    private long timestamp = System.currentTimeMillis();

    public ResultBody(Integer code) {
        this.code = code;
    }

    public ResultBody(String msg) {
        this.message = msg;
    }

    public ResultBody(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResultBody(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultBody(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResultBody(JwtErrorConst jwtErrorConst) {
        this.code = jwtErrorConst.getCode();
        this.message = jwtErrorConst.getReason();
    }

    public static <T> ResultBody<T> build(T data) {
        ResultBody<T> result = new ResultBody<>();
        result.setCode(ResultType.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> ResultBody<T> build(Supplier<T> s) {
        ResultBody<T> result = new ResultBody<>();
        T data = s.get();
        result.setData(data);
        result.setCode(ResultType.SUCCESS);
        return result;
    }


    public static <T> ResultBody<T> build(Function<ResultBody<T>, T> s) {
        ResultBody<T> result = new ResultBody<>();
        T data = s.apply(result);
        if (result.getMessage() == null) {
            result.setData(data);
            result.setCode(ResultType.SUCCESS);
        }
        return result;
    }

}
