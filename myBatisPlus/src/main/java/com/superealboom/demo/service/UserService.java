package com.superealboom.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superealboom.demo.entity.User;
import com.superealboom.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
