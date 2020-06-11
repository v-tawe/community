package com.kyss.community.service;

import com.kyss.community.dto.QuestionDTO;

import java.util.List;

/**
 * @ClassName HomeService
 * @Description TODO
 * @Author davidt
 * @Date 6/11/2020 3:43 PM
 * @Version 1.0
 **/

public interface HomeService {
    List<QuestionDTO> listAll();
}
