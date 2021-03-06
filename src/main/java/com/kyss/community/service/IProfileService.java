package com.kyss.community.service;

import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.QuestionDTO;

/**
 * @ClassName ProfileService
 * @Description TODO
 * @Author davidt
 * @Date 6/12/2020 3:07 PM
 * @Version 1.0
 **/
public interface IProfileService {
    PageInfo<QuestionDTO> listMyQuesitons(Long userId, int pageNo, int pageSize);

    PageInfo<QuestionDTO> listAllOrderByViews(int pageNo, int pageSize);

    Long countAllQuestions();

    Long countMyQuestions(Long userId);
}
