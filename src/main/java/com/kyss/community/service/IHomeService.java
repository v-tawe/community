package com.kyss.community.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.kyss.community.dto.QuestionDTO;

import java.util.List;

/**
 * @ClassName HomeService
 * @Description TODO
 * @Author davidt
 * @Date 6/11/2020 3:43 PM
 * @Version 1.0
 **/

public interface IHomeService {
    List<QuestionDTO> listAll();

    PageInfo<QuestionDTO> listAll(int pageNo, int pageSize);
}
