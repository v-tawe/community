package com.kyss.community.service;

import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.NotificationDTO;

/**
 * @ClassName NotificationService
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 4:29 PM
 * @Version 1.0
 **/
public interface NotificationService {
    PageInfo<NotificationDTO> listAll(int pageNo, int pageSize);
}
