package com.ruoyi.pay.alipay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pay.alipay.service.IAliPayService;
import com.ruoyi.pay.domain.PayOrder;
import com.ruoyi.pay.service.IPayOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author zlh
 */
@RestController
@RequestMapping("/pay/alipay")
@ConditionalOnProperty(prefix = "pay.alipay", name = "enabled", havingValue = "true")
@Tag(name = "【支付宝】管理")
public class AliPayController extends BaseController {

    @Autowired
    private IPayOrderService payOrderService;

    @Autowired(required = false)
    private IAliPayService aliPayService;

    @Anonymous
    @Operation(summary = "支付宝支付")
    @Parameters({
            @Parameter(name = "orderId", description = "订单号", required = true)
    })
    @GetMapping("/pay/{orderNumber}")
    public AjaxResult pay(@PathVariable(name = "orderNumber") String orderNumber) {
        AlipayTradePagePayResponse response;
        PayOrder aliPay = payOrderService.selectPayOrderByOrderNumber(orderNumber);
        try {
            // 发起API调用（以创建当面付收款二维码为例）
            response = Factory.Payment.Page().pay(
                    aliPay.getOrderContent(),
                    aliPay.getOrderNumber(),
                    aliPay.getActualAmount(),
                    "");
        } catch (Exception e) {
            return error(e.getMessage());
        }
        return success(response.getBody());
    }

    @Anonymous
    @Operation(summary = "支付宝支付回调")
    @Transactional
    @PostMapping("/notify")
    public AjaxResult payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String orderNumber = params.get("out_trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // // 更新订单未已支付
                payOrderService.updateStatus(orderNumber, "已支付");
                if (aliPayService != null) {
                    aliPayService.callback(params);
                }
            }
        }
        return success("success");
    }
}
