package com.dongns.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongns.commonutils.Result;
import com.dongns.eduservice.entity.EduTeacher;
import com.dongns.eduservice.service.EduTeacherService;
import com.dongns.eduservice.vo.TeacherVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author dongns
 * @since 2021-01-03
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findList")
    public Result findList() {
        List<EduTeacher> list = teacherService.list(null);
        int i=1/0;
        return Result.ok().data("list", list);
    }


    @PostMapping("insert")
    public Result insert(@RequestBody EduTeacher eduTeacher) {
        Result r = null;
        boolean saveflag = teacherService.save(eduTeacher);
        System.out.println(saveflag);
        if (saveflag) {
            r = Result.ok();
        } else {
            r = Result.error();
        }
        return r;
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id)
    {
//        if(teacherService.removeById(id))
//        {
//            return Result.ok();
//        }else
//        {
//            return Result.error();
//        }
      return   teacherService.removeById(id)?Result.ok():Result.error();
    }

    @PostMapping("update")
    public Result update(@RequestBody EduTeacher teacher)
    {
        return teacherService.updateById(teacher)?Result.ok():Result.error();
    }

    @GetMapping("findById/{id}")
    public Result findById(@PathVariable String id)
    {
     return Result.ok().data("list",teacherService.getById(id));
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
                    TeacherVo teacherVo){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherVo);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  Result.ok().data("total", total).data("rows", records);
    }



}

