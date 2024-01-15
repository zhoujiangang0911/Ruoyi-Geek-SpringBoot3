package com.ruoyi.third.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.third.constant.WxH5Constant;
import com.ruoyi.third.constant.WxMiniAppConstant;
import com.ruoyi.third.service.Impl.WxLoginServiceImpl;;

@RestController
@RequestMapping("/wx")
public class WxLoginService extends BaseController {
    @Autowired
    public WxH5Constant wxH5AppConstant;

    @Autowired
    public WxMiniAppConstant wxMiniAppConstant;

    @Autowired
    WxLoginServiceImpl wxLoginServiceImpl;

    @Anonymous
    @GetMapping("/miniapp/{code}")
    public AjaxResult loginMiniApp(@PathVariable("code") String code) {
        return success(wxLoginServiceImpl.doLoginMiniApp(code));
    }

    @Anonymous
    @GetMapping("/h5/{code}")
    public AjaxResult loginH5App(@PathVariable("code") String code) {
        return success(wxLoginServiceImpl.doLoginMiniApp(code));
    }

}
