package com.pragna.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SpeechSuperApi {

	public static final String baseUrl = "https://api.speechsuper.com/";
    public static final String appKey = "16901639580001a0";
    public static final String secretKey = "92a000daa6724d9dcf200da81a744f18";

    public String HttpAPI(MultipartFile file, String audioPath, String audioType, String audioSampleRate, String refText, String coreType) {
        String url = baseUrl + coreType;
        //userId is randomly generated because the userId cannot be same during concurrent access
        String userId = getRandomString(5);
        String res = null;
        @SuppressWarnings("deprecation")
		CloseableHttpClient httpclient = new DefaultHttpClient();
        String params = buildParam(appKey, secretKey, userId, audioType, audioSampleRate, refText, coreType);
        try {

            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Request-Index", "0");

            StringBody comment = new StringBody(params, ContentType.APPLICATION_JSON);
            
            File tempFile = File.createTempFile("temp", null);
            file.transferTo(tempFile);
            
            
            AudioFormat fmt;
			try {
				fmt = AudioSystem.getAudioFileFormat(tempFile).getFormat();
				System.out.println(fmt);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            FileBody bin = new FileBody(tempFile);
            
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("text", comment).addPart("audio", bin).build();
            httppost.setEntity(reqEntity);

            CloseableHttpResponse response = httpclient.execute(httppost);

            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    res = EntityUtils.toString(resEntity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println(res);
        return res;
    }

    private String buildParam(String appkey, String secretKey, String userId, String audioType, String audioSampleRate, String refText, String coreType) {

        MessageDigest digest = DigestUtils.getSha1Digest();

        long timeReqMillis = System.currentTimeMillis();
        String connectSigStr = appkey + timeReqMillis + secretKey;
        String connectSig = Hex.encodeHexString(digest.digest(connectSigStr.getBytes()));

        long timeStartMillis = System.currentTimeMillis();
        String startSigStr = appkey + timeStartMillis + userId + secretKey;
        String startSig = Hex.encodeHexString(digest.digest(startSigStr.getBytes()));
        //request param
        String params = "{"
                + "\"connect\":{"
                + "\"cmd\":\"connect\","
                + "\"param\":{"
                + "\"sdk\":{"
                + "\"protocol\":2,"
                + "\"version\":16777472,"
                + "\"source\":9"
                + "},"
                + "\"app\":{"
                + "\"applicationId\":\"" + appkey + "\","
                + "\"sig\":\"" + connectSig + "\","
                + "\"timestamp\":\"" + timeReqMillis + "\""
                + "}"
                + "}"
                + "},"
                + "\"start\":{"
                + "\"cmd\":\"start\","
                + "\"param\":{"
                + "\"app\":{"
                + "\"applicationId\":\"" + appkey + "\","
                + "\"timestamp\":\"" + timeStartMillis + "\","
                + "\"sig\":\"" + startSig + "\","
                + "\"userId\":\"" + userId + "\""
                + "},"
                + "\"audio\":{"
                + "\"sampleBytes\":2,"
                + "\"channel\":1,"
                + "\"sampleRate\":" + audioSampleRate + ","
                + "\"audioType\":\"" + audioType + "\""
                + "},"
                + "\"request\":{"
                + "\"tokenId\":\"tokenId\","
                + "\"refText\":\"" + refText + "\","
                + "\"coreType\":\"" + coreType + "\""
                + "}"
                + "}"
                + "}"
                + "}";
        return params;
    }


    private int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    private String charString = "abcdefghijklmnopqrstuvwxyz123456789";

    private String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = charString.length();
        for (int i = 0; i < length; i++) {
            sb.append(charString.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }
    
  
   

}
