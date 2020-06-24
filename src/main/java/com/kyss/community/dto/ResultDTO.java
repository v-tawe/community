package com.kyss.community.dto;

import lombok.Data;

/**
 * @ClassName ResultDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/24/2020 5:16 PM
 * @Version 1.0
 **/

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public ResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultDTO successCode() {
        return new ResultDTO(200, "success");
    }

    public static ResultDTO errorCode() {
        return new ResultDTO(500, "error");
    }
}
