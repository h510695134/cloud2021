package com.wanghh.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.atguigu.springcloud.entities.AliPayDTO;
import com.atguigu.springcloud.entities.AliPayRefundDTO;
import com.atguigu.springcloud.entities.AliPayVerifyDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author wanghh
 * @date 2021-04-29
 */
@Component
public class AlipayUtils {

    /**
     * 从文件中读取私钥
     * <p>
     * 注意：实际开发过程中，请务必注意不要将私钥信息配置在源码中（比如配置为常量或储存在配置文件的某个字段中等），因为私钥的保密等级往往比源码高得多，将会增加私钥泄露的风险。推荐将私钥信息储存在专用的私钥文件中，
     * 将私钥文件通过安全的流程分发到服务器的安全储存区域上，仅供自己的应用运行时读取。
     * <p>
     * 此处为了单元测试执行的环境普适性，私钥文件配置在resources资源下，实际过程中请不要这样做。
     *
     * @param appId 私钥对应的APP_ID
     * @return 私钥字符串
     */
    private static String getPrivateKey(String appId) {
        InputStream stream = AlipayUtils.class.getResourceAsStream("/fixture/privateKey.json");
        Map<String, String> result = new Gson().fromJson(new InputStreamReader(stream), new TypeToken<Map<String, String>>() {
        }.getType());
        return result.get(appId);
    }

    /**
     * 沙箱app测试账号
     */
    public static class Mini {
        public static final Config CONFIG = getConfig();

        public static Config getConfig() {
            Config config = new Config();
            //协议
            config.protocol = "https";
            //地址
//            config.gatewayHost = "openapi.alipay.com";
            config.gatewayHost = "openapi.alipaydev.com/gateway.do";
            //APP_ID
            config.appId = "2021000117607413";
            //加密方式
            config.signType = "RSA2";
            //支付宝公钥
            config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkMYOA/aGd9/bM+q02I+T4CxlNJPHDqsN9Xb0LvRaw4x+UMI7VTRCe+YlIYzpHBmprJYmNepg8LtwXr5VTcZ6PpA3WVDs3phik16l+3G3FRBX8d5D1L0ZLtGqAZc5I9ZMF38aMaCm1fttT8U8LJp1ZQRmBGRIqliduPFNVKPBtfyLSJxuZlCabz2eSJfMiCTncLqr2SAho92DKk9g2rMGVdwZoNgwMNSZUwhjlxvXON7ARLOc6IurAzwjStBlNGa7xak+Sib41rk6nw5VkbF3COGgtdg0k/4+IfeDnw1Su8KvoL9ZrowTLFeOf6FPakWk0+PPnUz3MfxQ5XokKikZEwIDAQAB";
            // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
            config.merchantPrivateKey = getPrivateKey(config.appId);
            //可设置异步通知接收服务地址（可选）
            config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";
            return config;
        }
    }

    /**
     * 商家版支付宝账号
     */
    public static class App2B{
        public final static  Config CONFIG = getOptions();

        private static Config getOptions() {
            Config config = new Config();
            // 协议
            config.protocol = "https";
            // 地址
            config.gatewayHost = "openapi.alipay.com";
            // 加密方式
            config.signType = "RSA2";
            // appID
            config.appId = "2021002126651117";

            // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
            config.merchantPrivateKey = getPrivateKey(config.appId);

            //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
            /*config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
            config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
            config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
*/
            //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
            config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOeZzES5pIvr1vdVkMrkvatJZfv0HiuY+gwIBILAprpqYrlwuYeK2rhbm+j7031G6RsESwGHEpdzT/n+nwupHSAWaW0aaU9rGDhFPOPVJPDa985oIRbKiXpiUnDJKf17RBb7rSP7cwf4Y0gV9XDxBMriu5tX4XdxwEO5O50Qhfx6knn45NnzGRFKBg/L4TyoRTIX/gMpm2fLaFMtxL37+Krm4Gloxk6VJgSmY3yvyY/yvGHqsijwhtO5cIcUUQPr+9BQMPwqkztK7DtlWd8Oyt/BpPGhEbyppG4nYpGzkwZGKyERiGaGGBlgalXEkfNCPwL9JXxtiy2du1d+UZ67wwIDAQAB";

            //可设置异步通知接收服务地址（可选）
            config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";
            return config;
        }
    }

    /**
     * C端支付宝账号
     */
    public static class App2C{
        private static Config getOptions() {
            Config config = new Config();
            // 协议
            config.protocol = "https";
            // 地址
            config.gatewayHost = "openapi.alipay.com";
            // 加密方式
            config.signType = "RSA2";
            // appID
            config.appId = "2021002125695267";

            // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
            config.merchantPrivateKey = getPrivateKey(config.appId);

            //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
            /*config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
            config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
            config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
*/
            //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
            config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOeZzES5pIvr1vdVkMrkvatJZfv0HiuY+gwIBILAprpqYrlwuYeK2rhbm+j7031G6RsESwGHEpdzT/n+nwupHSAWaW0aaU9rGDhFPOPVJPDa985oIRbKiXpiUnDJKf17RBb7rSP7cwf4Y0gV9XDxBMriu5tX4XdxwEO5O50Qhfx6knn45NnzGRFKBg/L4TyoRTIX/gMpm2fLaFMtxL37+Krm4Gloxk6VJgSmY3yvyY/yvGHqsijwhtO5cIcUUQPr+9BQMPwqkztK7DtlWd8Oyt/BpPGhEbyppG4nYpGzkwZGKyERiGaGGBlgalXEkfNCPwL9JXxtiy2du1d+UZ67wwIDAQAB";

            //可设置异步通知接收服务地址（可选）
            config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";
            return config;
        }
    }

    /**
     * Factory.Payment.App.pay：原生手机APP支付测试（外部商户APP唤起快捷SDK创建订单并支付）
     *
     * @param aliPay 支付参数
     * @return 带有签名的订单支付信息
     */
    public String aliPayTradeAppPay(AliPayDTO aliPay,Config config){
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(config);
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradeAppPayResponse response = Factory.Payment.App()
                    .pay(aliPay.getSubject(), aliPay.getOutTradeNo(), aliPay.getTotalAmount());
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功");
                return response.getBody();
            } else {
                System.err.println("调用失败，原因：" + response.getBody());
                return "调用失败";
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Factory.Payment.FaceToFace：原生手机APP支付测试（生成交易付款码，待用户扫码付款）
     *
     * @param aliPayDto 支付参数
     * @return 带有签名的订单支付信息
     */
    public String aliPayTradePreCreate(AliPayDTO aliPayDto, Config config) {
        try {
            // 1. 设置参数（全局只需设置一次）
            Factory.setOptions(config);
            // 2. 发起API调用（二维码收款，待用户用支付宝扫一扫）
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace()
                    .preCreate(aliPayDto.getSubject(), aliPayDto.getOutTradeNo(), aliPayDto.getTotalAmount());
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功" + response.getQrCode());
                return response.getQrCode();
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "," + response.getSubMsg());
                return "调用失败";
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Factory.Payment.Common().query：交易查询
     * @param outTradeNo 订单编号
     * @param config 配置信息
     * @return 支付宝订单状态
     */
    public String aliPayTradeQuery(String outTradeNo,Config config){
        try {
            // 1. 设置参数（全局只需设置一次）
            Factory.setOptions(config);
            // 2. 发起API调用（根据订单编号查询交易记录）
            AlipayTradeQueryResponse response = Factory.Payment.Common().query(outTradeNo);
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功" + response.getBody());
                return response.getTradeStatus();
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "," + response.getSubMsg());
                return "调用失败";
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Factory.Payment.Common().verifyNotify：支付宝支付完异步通知
     *
     * @param response 请求
     * @param request  请求
     * @return 支付宝订单支付状态
     */
    public String aliPayResultCheck(HttpServletResponse response, HttpServletRequest request, AliPayVerifyDTO aliPayVerifyDTO) throws Exception {
        //获取支付宝POST过来反馈信息
        Map < String , String >   params   =   new HashMap< String , String >();
        Map   requestParams   =   request . getParameterMap ();
        for  (Iterator iter = requestParams . keySet (). iterator (); iter . hasNext ();) {
            String   name   =  ( String )  iter . next ();
            String []  values   =  ( String [])  requestParams . get ( name );
            String   valueStr   =   "" ;
            for  ( int   i   =   0 ;  i   <   values . length ;  i ++ ) {
                valueStr   =  ( i   ==   values . length   -   1 )  ?   valueStr   +   values [ i ]
                        :  valueStr   +   values [ i ]  +   "," ;
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params . put ( name ,  valueStr );
        }

        //打印数据看看
        System.out.println("支付宝回调参数：" + JSON.toJSONString(params));
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(aliPayVerifyDTO.getConfig());

        // 2. 发起API调用（异步通知验签）
        if (Factory.Payment.Common().verifyNotify(params)) {
            System.out.println("验签成功");
            //验证订单编码和金额是否正确
            if (new BigDecimal(params.get("total_amount")).compareTo(new BigDecimal(aliPayVerifyDTO.getTotalAmount())) == 0 && params.get("seller_id").equals(aliPayVerifyDTO.getSellerId()) && params.get("app_id").equals(aliPayVerifyDTO.getConfig().appId)) {
                System.out.println("验签成功,商户id，订单编码，订单金额、appId校验成功");
                response.getWriter().write("success");
                response.getWriter().close();
                if (params.get("trade_status").equals("TRADE_SUCCESS")) {
                    //todo 付款成功，保存交易流水

                }
                //返回支付状态
                return params.get("trade_status");
            } else {
                System.out.println("验签失败,订单编码：{},商户id{}，订单金额{},appId{}校验失败");
                response.getWriter().write("failure");
                response.getWriter().close();
                return "校验失败";
            }
        } else {
            System.out.println("验签失败");
            response.getWriter().write("failure");
            response.getWriter().close();
            return "校验失败";
        }
    }

    /**
     * Factory.Payment.Common().query：交易退款
     *
     * @param aliPayRefund 商家订单编码
     * @return 支付宝订单支付状态
     */
    public Boolean aliPayTradeRefund(AliPayRefundDTO aliPayRefund) {
        try {
            // 1. 设置参数（全局只需设置一次）
            Factory.setOptions(aliPayRefund.getConfig());
            // 2. 发起API调用（通用退款）
            AlipayTradeRefundResponse response = Factory.Payment.Common()
                    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
                    .optional("out_request_no",aliPayRefund.getOutRequestNo())
                    .optional("refund_reason", "正常退款")
                    .refund(aliPayRefund.getOutTradeNo(), aliPayRefund.getRefundAmount());

            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功" + response.getTradeNo());
                // 支付宝退款成功编码10000
                if ("10000".equals(response.getCode())){
                    //todo 退款成功，保存交易流水

                    return Boolean.TRUE;
                }
                System.out.println("退款失败，code原因：" +response.getCode()+ response.getMsg()+",详细code原因："+response.getSubCode()+response.getSubMsg());
            } else {
                System.out.println("调用失败订单号{}，原因：{}" + response.getTradeNo() + response.getMsg());
                System.out.println("调用失败，原因：" + response.getMsg());
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            //throw new Ic2cloudCustomException(e.getMessage(), e);
        }
        return false;
    }

}
