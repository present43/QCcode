package com.aliyun.demo;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//阿里云图片上传  代码测试
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        test1();
    }

    public static void test1() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FxhKudN6ZRGiPV1iUGF";
        String accessKeySecret = "LokWsRgYxT5Owuko1rTfuNpjecRq34";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("E:\\Game\\steamapps\\workshop\\content\\431960\\875617215\\preview.jpg");
        ossClient.putObject("qingchenglz", "abc.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //此方法用于上传字节流 不要用来传图片
    private void test2() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FxhKudN6ZRGiPV1iUGF";
        String accessKeySecret = "LokWsRgYxT5Owuko1rTfuNpjecRq34";

        String bucketName = "qingchenglz";
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "E:\\Game\\steamapps\\workshop\\content\\431960\\875617215\\preview.jpg";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "abc.jpg";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
