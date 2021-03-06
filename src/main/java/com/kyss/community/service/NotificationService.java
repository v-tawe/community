package com.kyss.community.service;

import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.NotificationDTO;
import com.kyss.community.generator.model.Notification;

/**
 * @ClassName NotificationService
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 4:29 PM
 * @Version 1.0
 **/
public interface NotificationService {
    PageInfo<NotificationDTO> listAll(int pageNo, int pageSize);

    int read(Long id);

    Notification selectByPrimaryKey(Long id);
}
