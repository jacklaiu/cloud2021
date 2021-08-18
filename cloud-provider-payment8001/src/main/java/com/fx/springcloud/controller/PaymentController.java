package com.fx.springcloud.controller;

import com.fx.springcloud.entities.CommonResult;
import com.fx.springcloud.entities.Payment;
import com.fx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if(result > 0) {
            return new CommonResult<Integer>(200, "插入数据库成功，serverPort："+serverPort, result);
        }else {
            return new CommonResult<Integer>(500, "插入数据库失败", -1);
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment p = paymentService.getPaymentById(id);
        int age = 10/2;
        if(p != null) {
            return new CommonResult<Payment>(200, "查询成功，serverPort：" + serverPort, p);
        }else {
            return new CommonResult<Payment>(500, "没有对应记录，查询id：" + id, null);
        }
    }

}
