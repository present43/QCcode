package com.qingcheng.controller.file;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//文件上传控制器
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private HttpServletRequest request;

    //注入OSSClient 类的对象
    @Autowired
    private OSSClient ossClient;

    //本地上传 (以弃用)   图片上传到web服务器里了 单点部署，数量不多 可以用本地上传       如果图片数量过多，且是集群部署，则需要云存储。
    @PostMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file) {
        //保存地址  获得其绝对目录
        String Path = request.getSession().getServletContext().getRealPath("img");
        //获得目录加文件名
        String filePath = Path + "/" + file.getOriginalFilename();
        //构建文件对象
        File desFile = new File(filePath);
        //判断filePath 是否存在
        if (!desFile.getParentFile().exists()) {
            //如果不存在 就逐层去创建
            desFile.mkdirs();
        }

        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "http://localhost:9101/img/" + file.getOriginalFilename();
    }


    //阿里云对象存储上传OSS @RequestParam("file") 将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
    @PostMapping("/oss")
    public String sooUpload(@RequestParam("file") MultipartFile file,String folder) {
        //OSS存储空间桶名
        String bucketName = "qingchenglz";
        //获得原始的文件名  为了保证不重复(重复会导致文件覆盖)UUID.randomUUID()  folder+"/" 文件目录
        String filename = folder+"/"+ UUID.randomUUID() + file.getOriginalFilename();

        try {
            //oss上传Object（对象）的方法
            ossClient.putObject(bucketName, filename, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回文件URL
        return "http://" + bucketName + ".oss-cn-beijing.aliyuncs.com/" + filename;
    }
}
