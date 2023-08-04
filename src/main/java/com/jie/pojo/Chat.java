package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : jie
 * @create 2023/8/3 9:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String text;
    private String url;
    private MultipartFile image;
    private String time;
    private String belong;
    private String account;
    private String toPeo;

    public String toJsonString() {
        return "{\"text\":\"" + text + '\"' +
                ", \"url\":\"" + url + '\"' +
                ", \"image\":\"" + image + '\"'  +
                ", \"time\":\"" + time + '\"' +
                ", \"belong\":\"" + belong + '\"' +
                ", \"account\":\"" + account + '\"' +
                ", \"toPeo\":\"" + toPeo + '\"' +
                '}';
    }
}
