package com.wanghh.demo.utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.springcloud.entities.*;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

import org.apache.commons.io.FileUtils;


/**
 * @author wanghh
 * @date 2021-04-30
 */
@Slf4j
@Component
public class WechatPayUtils {

    /**
     * 商户号
     */
    private static String mchId = "1608859166";
    /**
     * 商户证书序列号
     */
    private static String mchSerialNo = "2CA7972EEBE92F129D68E319380A9C8CA9690938";
    /**
     * api密钥
     */
    private static String apiV3Key = "d2h3mq5m6jz8dj5ge09ngy86k4hkd8vn";
    /**
     * httpClient
     */
    private CloseableHttpClient httpClient;
    /**
     * 使用自动更新的签名验证器
     */
    private AutoUpdateCertificatesVerifier verifier;

    /**
     * appId2C
     */
    private static String appId2C = "wxa30396f01f1699a8";
    /**
     * appId2C
     */
    private static String appId2B = "wxc7388d92d83021cf";

    /**
     * 你的商户私钥
     */
    private static String privateKey;

    static {
        try {
            privateKey = FileUtils.readFileToString(ResourceUtils.getFile(WechatPayUtils.class.getResource("/wechatpay/1608859166/apiclient_key.pem").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() throws IOException {
        // 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ByteArrayInputStream(privateKey.getBytes("utf-8")));

        //使用自动更新的签名验证器，不需要传入证书mchId：商户号,mchSerialNo：商户证书序列号,merchantPrivateKey：字符串格式的商户私钥，apiV3key：V3密钥）
        verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes("utf-8"));

        // 初始化httpClient,通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }

    /**
     * 解密工具
     */
    private static AesUtil aesUtil = new AesUtil(apiV3Key.getBytes());

    /**
     * 联优汇微信账号
     */
    public static class App2C {
        public static WechatPayDTO WECHATPAY = getConfig();

        public static WechatPayDTO getConfig() {
            return WechatPayDTO.builder()
                    .appid(appId2C)
                    .mchid(mchId)
                    .notify_url("http://192.168.0.131:9999/order/tOrder/vip/wechatPayApp/receiveSignNotify")
                    .build();
        }
    }

    /**
     * 联优汇商家版微信账号
     */
    public static class App2B {
        public static WechatPayDTO WECHATPAY = getConfig();

        public static WechatPayDTO getConfig() {
            return WechatPayDTO.builder()
                    .appid(appId2B)
                    .mchid(mchId)
                    .notify_url("http://192.168.0.131:9999/order/tOrder/vip/wechatPayApp/receiveSignNotify")
                    .build();
        }
    }

    /**
     * APP支付统一下单
     * https://api.mch.weixin.qq.com/v3/pay/transactions/app
     *
     * @param param 支付参数
     * @return 带有签名的订单支付信息
     */
    public String wechatPayTradeAppPay(WechatPayDTO param) throws IOException {
        //请求URL
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/app");
        // 请求body参数
        String reqdata = JSONObject.toJSONString(param);
        StringEntity entity = new StringEntity(reqdata);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //处理成功
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
            } else if (statusCode == 204) {
                //处理成功，无返回Body
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return "";
    }
    /**
     * 微信支付成功回调
     *
     * @param response           响应
     * @param request            请求参数
     * @param wechatPayNotifyDTO 返回数据
     * @return 应答信息
     * @throws Exception 异常
     */
    public JSONObject wechatPayResultCheck(HttpServletResponse response, HttpServletRequest request, WechatPayNotifyDTO wechatPayNotifyDTO) throws Exception {
        String body = JSON.toJSONString(wechatPayNotifyDTO);
        //打印数据看看
        log.info("微信支付回调参数：" + body);
        //返回通知的应答报文，code(32)：SUCCESS为清算机构接收成功；message(64)：错误原因
        JSONObject responseJson = new JSONObject();
        responseJson.put("code", "FAIL");
        //支付通知http应答码为200或204才会当作正常接收，当回调处理异常时，应答的HTTP状态码应为500，或者4xx。
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        //回调签名
        String signature = request.getHeader("Wechatpay-Signature");
        //平台证书序列号
        String serial = request.getHeader("Wechatpay-Serial");
        //应答时间戳
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        //应答随机串
        String nonce = request.getHeader("Wechatpay-Nonce");
        if (ObjectUtil.isEmpty(signature) || ObjectUtil.isEmpty(serial)
                || ObjectUtil.isEmpty(timestamp) || ObjectUtil.isEmpty(nonce)) {
            log.info("微信支付回调请求header缺失");
            responseJson.put("message", "回调请求header缺失");
            return responseJson;
        }
        //获取签名串，验签
        String srcData = timestamp + "\n" + nonce + "\n" + body + "\n";
        if (autoUpdateVerifier(serial, srcData, signature)) {
            log.info("验签失败:" + srcData);
            responseJson.put("message", "验签失败");
            return responseJson;
        }
        //通知资源数据
        WechatPayResourceDTO wechatPayResourceDTO = wechatPayNotifyDTO.getResource();
        //对密文串进行解密
        String verify = aesUtil.decryptToString(wechatPayResourceDTO.getAssociated_data().getBytes("utf-8"), wechatPayResourceDTO.getNonce().getBytes("utf-8"), wechatPayResourceDTO.getCiphertext());
        WechatPayNotifyDataDTO wechatPayNotifyDataDTO = JSONObject.parseObject(verify, WechatPayNotifyDataDTO.class);
        if (ObjectUtil.isEmpty(wechatPayNotifyDataDTO.getTransaction_id())) {
            responseJson.put("message", "微信支付成功，未接受到微信订单号");
            return responseJson;
        }
        if (ObjectUtil.isEmpty(wechatPayNotifyDataDTO.getOut_trade_no())) {
            responseJson.put("message", "微信支付成功，未接受到商户订单号");
            return responseJson;
        }
        // todo 获取订单相关信息
        /*Order order = commonService.findOrderByForderCode(wechatPayNotifyDataDTO.getOut_trade_no());
        if (ObjectUtil.isEmpty(order)) {
            responseJson.put("message", "操作失败，商户系统未查询到商户订单号");
            return responseJson;
        }*/
        //支付失败
        if (!wechatPayNotifyDTO.getEvent_type().equals(Constans.WechatPayEventType)) {
            log.info("微信支付结果通知：支付失败，处理成功。订单号为：" + wechatPayNotifyDataDTO.getOut_trade_no());
            response.setStatus(HttpServletResponse.SC_OK);
            responseJson.put("code", "SUCCESS");
            responseJson.put("message", "微信支付失败，商户处理成功");
            return responseJson;
        }
        //todo 保存交易流水
        /*orderPayService.saveOrderPay(OrderPaySaveDTO.builder()
                .forderPayInnerSerialNumber(wechatPayNotifyDataDTO.getOut_trade_no())
                .forderPayPrice(new BigDecimal(wechatPayNotifyDataDTO.getAmount().getTotal()).divide(new BigDecimal(100)))
                .forderPayStatus(wechatPayNotifyDataDTO.getTrade_state())
                .forderPayThirdSerialNumber(wechatPayNotifyDataDTO.getTransaction_id())
                .forderPayThirdCompany(OrderPayThirdCompanyEnum.WECHAT)
                .forderPayType(OrderPayTypeEnum.PAY).build());*/
        //todo 支付成功，更改订单相关信息
        //Boolean result = orderBaseService.changeOrderPayStatus(wechatPayNotifyDataDTO.getOut_trade_no());
        if (true) {
            log.info("微信支付结果通知：支付成功，处理成功。订单号为：" + wechatPayNotifyDataDTO.getOut_trade_no());
            response.setStatus(HttpServletResponse.SC_OK);
            responseJson.put("code", "SUCCESS");
            responseJson.put("message", "微信支付成功，商户处理成功");
        } else {
            log.info("微信支付结果通知：支付成功，处理失败。订单号为：" + wechatPayNotifyDataDTO.getOut_trade_no());
            responseJson.put("message", "微信支付成功，商户处理失败");
        }
        return responseJson;
    }

    public void QueryOrder() throws Exception {

        //请求URL
        URIBuilder uriBuilder = new URIBuilder("https://api.mch.weixin.qq.com/v3/pay/transactions/id/4200000745202011093730578574");
        uriBuilder.setParameter("mchid", mchId);

        //完成签名并执行请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));
            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } finally {
            response.close();
        }
    }

    /**
     * 微信回调验签
     *
     * @param reqData            待签名签名串
     * @param wechatPaySignature 微信回调签名
     * @return 是与否
     * @throws IOException 异常
     */
    public Boolean autoUpdateVerifier(String serialNumber, String reqData, String wechatPaySignature) throws IOException {
        //初始化httpClient
        setup();
        //自动更新证书验签
        return verifier.verify(serialNumber, reqData.getBytes("utf-8"), wechatPaySignature);
    }

    /**
     * app支付计算签名值
     *
     * @param wechatPaySignDTO 支付参数
     * @return 签名值
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchAlgorithmException     异常
     * @throws InvalidKeyException          异常
     * @throws SignatureException           异常
     */
    public String getSign(WechatPaySignDTO wechatPaySignDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String message = buildMessage(wechatPaySignDTO.getAppId(), wechatPaySignDTO.getTimestamp(), wechatPaySignDTO.getNonceStr(), wechatPaySignDTO.getPrepayId());
        //计算签名串
        return sign(message.getBytes("utf-8"));
    }

    /**
     * 使用商户私钥对待签名串进行SHA256 with RSA签名，并对签名结果进行Base64编码得到签名值
     *
     * @param message 签名串
     * @return 签名值
     * @throws NoSuchAlgorithmException     异常
     * @throws UnsupportedEncodingException 异常
     * @throws InvalidKeyException          异常
     * @throws SignatureException           异常
     */
    private String sign(byte[] message) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withRSA");
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ByteArrayInputStream(privateKey.getBytes("utf-8")));
        sign.initSign(merchantPrivateKey);
        sign.update(message);
        return Base64.getEncoder().encodeToString(sign.sign());
    }

    /**
     * app支付-构建签名串
     *
     * @param appId     应用id
     * @param timestamp 时间戳
     * @param nonceStr  随机字符串
     * @param prepayId  预支付交易会话ID
     * @return 构造的签名串
     */
    private String buildMessage(String appId, long timestamp, String nonceStr, String prepayId) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + prepayId + "\n";
    }
}
