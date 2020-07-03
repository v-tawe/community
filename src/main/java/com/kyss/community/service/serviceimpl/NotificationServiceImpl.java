package com.kyss.community.service.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.NotificationDTO;
import com.kyss.community.enums.NotificationStatusEnum;
import com.kyss.community.enums.NotificationTypeEnum;
import com.kyss.community.exception.CustomizeException;
import com.kyss.community.generator.dao.NotificationMapper;
import com.kyss.community.generator.dao.QuestionMapper;
import com.kyss.community.generator.dao.UserMapper;
import com.kyss.community.generator.model.Notification;
import com.kyss.community.generator.model.NotificationExample;
import com.kyss.community.generator.model.Question;
import com.kyss.community.generator.model.User;
import com.kyss.community.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName NotificationServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/29/2020 4:29 PM
 * @Version 1.0
 **/

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageInfo<NotificationDTO> listAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.setOrderByClause("gmt_create desc");
        PageInfo<Notification> notificationPageInfo = new PageInfo<> (notificationMapper.selectByExample(notificationExample));
        PageInfo<NotificationDTO> notificationDTOPageInfo = new PageInfo<>(new Page<>());
        BeanUtils.copyProperties(notificationPageInfo, notificationDTOPageInfo, "list");
        List<NotificationDTO> notificationDTOs = notificationPageInfo.getList().stream().map(notification -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            // query notifier name
            User notifier = userMapper.selectByPrimaryKey(notificationDTO.getNotifier());
            // query receiver name
            User receiver = userMapper.selectByPrimaryKey(notificationDTO.getReceiver());
            // query question title
            Question question = questionMapper.selectByPrimaryKey(notificationDTO.getOuterId());
            notificationDTO.setTitle(question.getTitle());
            notificationDTO.setNotifierName(notifier.getName());
            notificationDTO.setReceiverName(receiver.getName());
            notificationDTO.setIsRead(NotificationStatusEnum.isRead(notificationDTO.getStatus()));
            notificationDTO.setTypeName(NotificationTypeEnum.getNameByType(notificationDTO.getType()));
            return notificationDTO;
        }).collect(Collectors.toList());
        notificationDTOPageInfo.setList(notificationDTOs);
        return notificationDTOPageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int read(Long id) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        return notificationMapper.updateByPrimaryKey(notification);
    }

    @Override
    public Notification selectByPrimaryKey(Long id) {
        return notificationMapper.selectByPrimaryKey(id);
    }
}
