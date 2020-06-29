package com.kyss.community.dto;

import lombok.Data;

/**
 * @ClassName NotificatonDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 4:31 PM
 * @Version 1.0
 **/

@Data
public class NotificationDTO {

    private Long id;
    private Long notifier;
    private String notifierName;
    private Long receiver;
    private String receiverName;
    private Long outerId;
    private String title;
    private Integer type;
    private String typeName;
    private Long gmtCreate;
    private Integer status;
    private Boolean isRead;

}
