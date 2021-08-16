package com.atguigu.crowd.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.atguigu.crowd.constant.CrowdConstant.*;

/**
 * 尚筹网项目通用工具类
 *
 * @author guyao
 * @create 2021-08-01 20:40
 */
public class CrowdUtil {

    /***
     * 专门负责上传文件到 OSS 服务器的工具方法
     * @param endpoint OSS 参数 *
     * @param accessKeyId OSS 参数 *
     * @param accessKeySecret OSS 参数 *
     * @param inputStream 要上传的文件的输入流 *
     * @param bucketName OSS 参数 *
     * @param bucketDomain OSS 参数 *
     * @param originalName 要上传的文件的原始文件名 *
     * @return 包含上传结果以及上传的文件在 OSS 上的访问路径
     * */
    public static ResultEntity<String> uploadFileToOss(String endpoint, String accessKeyId, String accessKeySecret, InputStream inputStream, String bucketName, String bucketDomain, String originalName) {
        // 创建 OSSClient 实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 生成上传文件在 OSS 服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用 UUID 生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));
        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;
        try {
            // 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();
            // 根据响应状态码判断请求是否成功
            if (responseMessage == null) {
                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;
                // 当前方法返回成功
                return ResultEntity.successWithData(ossFileAccessPath);
            } else {
                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();
                // 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();
                // 当前方法返回失败
                return ResultEntity.fail(" 当 前 响 应 状 态 码 =" + statusCode + " 错 误 消 息 =" + errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 当前方法返回失败
            return ResultEntity.fail(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭 OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /**
     * 利用第三方发送短信验证码拿到返回值
     *
     * @param phoneNum 接收验证码的手机号
     * @param appcode  调用第三方的appCode
     * @param skin     模板
     * @param host     第三方接口域名
     * @param path     第三方接口路径
     * @param expireAt 有效时间
     * @return 成功：返回验证码
     * 失败：返回失败信息
     */
    public static ResultEntity<String> sendShortMessage(String phoneNum, String appcode, String skin, String
            host, String path, String expireAt) {
        // String host = "https://dfsns.market.alicloudapi.com";

        // String path = "/data/send_sms";
        String method = "POST";
        // String appcode = "ce553176a873421889b746b77040884a";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int random = (int) (Math.random() * 10);
            builder.append(random);

        }
        String code = String.valueOf(builder);

        // 验证码，时效（短信格式
        bodys.put("content", "code:" + code + ",expire_at:" + expireAt);
        // 手机号
        bodys.put("phone_number", phoneNum);
        // 样式
        bodys.put("template_id", skin);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            StatusLine statusLine = response.getStatusLine();
            // 状态码
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();
//            logger.info(String.valueOf(reasonPhrase));// ok
//            logger.info(String.valueOf(statusCode));// 200
//            logger.info(String.valueOf(response));// HTTP/1.1 200 OK [Date: Tue, 10 Aug 2021 11:38:00 GMT, Content-Type: applicatio...
            //获取response的body
            switch (statusCode) {
                case 200:
                    return ResultEntity.successWithData(code);
                case 400:
                    return ResultEntity.fail("请求参数错误！");
                case 401:
                    return ResultEntity.fail("未授权或授权失败");
                case 403:
                    return ResultEntity.fail("调用超出限额");
                case 404:
                    return ResultEntity.fail("请求路径错误");
                case 500:
                    return ResultEntity.fail("服务内部错误");
                case 512:
                    return ResultEntity.fail("短信通道暂不可用");
                default:
                    return ResultEntity.fail(reasonPhrase);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }

    }

    /**
     * md5加密处理
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        // 1.判断资源是否有效
        if (source == null || source.length() == 0) {
            throw new RuntimeException(MESSAGE_STRING_INVALIDATE.getStr());
        }
        // MessageDigest对象
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_ENCRYPTION_MOD.getStr());

            // 获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 执行加密
            byte[] output = messageDigest.digest(input);
            // 创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 按照16进制将其转化为字符串
            int radix = 16;
            String encode = bigInteger.toString(radix).toUpperCase();

            return encode;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前请求是否时ajax请求
     *
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {


        //获取请求头信息
        String acceptHeader = request.getHeader(ACCEPT.getStr());
        String xRequestHeader = request.getHeader(X_REQUESTED_WITH.getStr());
        //判断
        return (XML_JSON_HEADER.getStr().equals(xRequestHeader)
                || (acceptHeader != null && acceptHeader.contains(APPLICATION_JSON_HEADER.getStr())));
    }
}
