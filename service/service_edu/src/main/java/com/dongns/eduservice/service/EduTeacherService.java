package com.dongns.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongns.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongns.eduservice.vo.TeacherVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author dongns
 * @since 2021-01-03
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherVo teacherVo);
}
