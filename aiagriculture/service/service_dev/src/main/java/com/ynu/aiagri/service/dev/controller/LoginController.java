package com.ynu.aiagri.service.dev.controller;


import com.ynu.aiagri.base.result.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class LoginController {
    //登录
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    //获取用户信息
    @GetMapping("info")
    public R info() {
        return R.ok()
                .data("name", "admin")
                .data("roles", "[admin]")
                .data("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    //退出
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
