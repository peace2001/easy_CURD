package com.gxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxh.config.Result;
import com.gxh.entity.User;
import com.gxh.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    public UserMapper userMapper;

    //全部查询
    @GetMapping("/findUser")
    public Result findUser(){
        List<User> userList = userMapper.selectList(null);
        return Result.ok().data("success",userList);
    }

    //新增用户
    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user){
        int re = userMapper.insert(user);
        return Result.ok().data("msg",re);
    }

    //修改用户
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        int re = userMapper.updateById(user);
        return Result.ok().data("msg",re);
    }

    //条件查询
    @GetMapping("findByName")
    public Result findByName(@RequestBody User user){
        HashMap<String,Object> map = new HashMap<>();
        String username  = user.getUsername();
        map.put("username",username);
        List<User> userList = userMapper.selectByMap(map);
        return Result.ok().data("msg",userList);

    }

    //分页查询
    @GetMapping("findByPage")
    //参数一 当前页；参数二 查询条数
    public Result fundByPage(@Param("current") int current,@Param("size") int size){
        Page<User> page = new Page<>(current,size);
        userMapper.selectPage(page, null);
        return Result.ok().data("msg",page.getRecords());
    }

    //删除用户
    @DeleteMapping("delUser")
    public Result delUserByName(){
        userMapper.deleteById(4);
        return Result.ok().data("msg","success");
    }

    //条件查询wrapper
    @GetMapping("findByWrapper")
    public Result findByWrapper(@RequestBody User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String name = user.getUsername();
        wrapper
//                .like("username",name)
                //降序排列
                .orderByDesc("id");
//                .in("permission",1);
        List<Map<String,Object>> maps = userMapper.selectMaps(wrapper);
//        List<User> userList = userMapper.selectList(wrapper);
        return Result.ok().data("msg",maps);
    }
}
