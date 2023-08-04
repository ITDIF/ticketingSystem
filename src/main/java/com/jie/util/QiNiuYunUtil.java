package com.jie.util;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author jie
 */
@Component
public class QiNiuYunUtil {
    private static String accessKey = "27ovtGosJGti8dewx3yxcINBSwYqNEoBXrtdgTuO";
    private static String accessSecretKey = "eKOz9fcLlCbfkolS0aJRV3abTd_q5b60vhRaTt0O";
    private static String bucketName = "image-bucket1";
    private static String path = "http://ryrlaksut.hn-bkt.clouddn.com/";
    private static String documentName = "serviceImage/";


    /**
     * @param file 前端传来的图片
     * @return 图片的访问路径
     */
    public static String upload(MultipartFile file){
        // 生成文件名
        String fileName = getRandomImgName(file.getOriginalFilename());
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucketName);
            Response response = uploadManager.put(uploadBytes, documentName+fileName , upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return path+documentName+fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 生成唯一图片名称
     * @Param: fileName
     * @return: 云服务器fileName
     */
    public static String getRandomImgName(String fileName) {
        int index = fileName.lastIndexOf(".");

        if (fileName.isEmpty() || index == -1){
            throw new IllegalArgumentException();
        }
        // 获取文件后缀
        String suffix = fileName.substring(index).toLowerCase();
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 拼接新的名称
        return uuid + suffix;
    }

    public static void main(String[] args) {
        System.out.println(accessKey+" "+accessSecretKey);

    }

}
