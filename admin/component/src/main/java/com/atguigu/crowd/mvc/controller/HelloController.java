package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.pojo.Admin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guyao
 * @create 2021-08-01 15:55
 */

@Controller
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping("/text/ajax/async.html")
    public String testAsync() throws InterruptedException {
        Thread.sleep(2000);
        return "success";
    }

    @GetMapping(value = "/ka.html",produces = "text/html;charset=UTF-8")//produce防止乱码
    public String hello(Model model, HttpServletRequest request){

        model.addAttribute("list", adminService.getAll());

//        int i = 1/0;
//        String s = null;
//        boolean ccc = s.equals("ccc");
        logger.info(Boolean.valueOf(CrowdUtil.judgeRequestType(request)).toString());
        return "index";
    }
    @ResponseBody
    @PostMapping({"/send/array.html","/send/array/two.json"})
    public ResultEntity<List<Integer>> ReceiveArray(@RequestBody List<Integer> array, HttpServletRequest request){
//        for (Integer num:
//             array) {
//            logger.info(num.toString());
//        }
//        logger.info((Boolean.valueOf(CrowdUtil.judgeRequestType(request))).toString());

//        String s = null;
//        boolean ccc = s.equals("ccc");

        return ResultEntity.successWithData(array);
    }
}
