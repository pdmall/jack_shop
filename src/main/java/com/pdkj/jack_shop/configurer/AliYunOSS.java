package com.pdkj.jack_shop.configurer;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectResult;
import com.pdkj.jack_shop.core.CustomException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This sample demonstrates how to delete objects under specfied bucket
 * from Aliyun OSS using the OSS SDK for Java.
 */
public class AliYunOSS {

        private static String endpoint = "oss-cn-beijing.aliyuncs.com";

    private static String accessKeyId = "LTAIDWdxnQoZi0hN";
    private static String accessKeySecret = "wrZon4HUxqrfxBhQ1AdTUX8gmtT5M4";

    private static String bucketName = "pdkj";

    private static String subFileKey(String fileUrl){
        return fileUrl.substring(fileUrl.indexOf("aliyuncs.com")+13,fileUrl.length());
    }
    public static String addFile(InputStream inputStream,String phone){
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String imgAddress ="code/" + phone + ".jpg";
        try {
            client.putObject(bucketName, imgAddress, inputStream);
        } catch (OSSException oe) {
            throw new CustomException("Error Code:       " + oe.getErrorCode()+"Error Message: " + oe.getErrorMessage());
        } catch (ClientException ce) {
            throw new CustomException("Error Message: " + ce.getMessage());
        } finally {
            client.shutdown();
        }
        imgAddress = "https://pdkj.oss-cn-beijing.aliyuncs.com/"+ imgAddress;
        return imgAddress;
    }
    public static void deleteFile(String fileUrl){
        String key = subFileKey(fileUrl);
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            List<String> keys = new ArrayList();
            keys.add(key);
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
            List<String> deletedObjects =  deleteObjectsResult.getDeletedObjects();
        } catch (OSSException oe) {
            throw new CustomException("Error Code:       " + oe.getErrorCode()+"Error Message: " + oe.getErrorMessage());
        } catch (ClientException ce) {
            throw new CustomException("Error Message: " + ce.getMessage());
        } finally {
            client.shutdown();
        }
    }

    public static void deleteFileS(List<String> fileUrls){
        for(int i=0; i<fileUrls.size(); i++){
            fileUrls.set(i,subFileKey(fileUrls.get(i)));
        }


        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucketName).withKeys(fileUrls));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        } catch (OSSException oe) {
            throw new CustomException("Error Code:       " + oe.getErrorCode()+"Error Message: " + oe.getErrorMessage());
        } catch (ClientException ce) {
            throw new CustomException("Error Message: " + ce.getMessage());
        } finally {
            client.shutdown();
        }
    }



}
