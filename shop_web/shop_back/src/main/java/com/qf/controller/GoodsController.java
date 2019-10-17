package com.qf.controller;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.Goods;
import com.qf.feign.GoodsFeign;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goodsManage")
public class GoodsController {


    @Autowired
    private GoodsFeign goodsFeign;


    private String uploadPath = "D:\\imgs";

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    @RequestMapping("/list")
    public String s(Model model) {

        List<Goods> goodsList = goodsFeign.s();

        model.addAttribute("goodsList", goodsList);

        return "goodslist";
    }


    @RequestMapping("/insert")
    public String input13321(Goods goods) {
        boolean b = goodsFeign.goodsInsert(goods);

        return b ? "redirect:http://localhost:12345/back/goodsManage/list" : "error";
    }


    @RequestMapping("uploader")
    @ResponseBody
    public String uploader(MultipartFile file) {

        //上传的位置为D://imgs
        File outfile = new File(uploadPath);

        if (!outfile.exists()) {
            boolean mkdirs = outfile.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("上传路径为空，且无法创建");
            }
        }

        //处理文件
        String filename = UUID.randomUUID().toString();
        //表示在D：（imgages）outfile 下存放图片
        outfile = new File(outfile, filename);

        //开始上传
        String uploadpath = null;

        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(),
                    "jpg",
                    null
            );

            uploadpath = storePath.getFullPath();

        } catch (IOException e) {
            e.printStackTrace();
        }

       /* try ( InputStream in = file.getInputStream();
              FileOutputStream out = new FileOutputStream(outfile);
              ){

            *//*文件的上传*//*
            IOUtils.copy(in,out);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        uploadpath = "http://192.168.68.200:8080/" + uploadpath;
        System.out.println("上传后的路径" + uploadpath);

        return "{\"filename\":\"" + uploadpath + "\"}";
    }


    @RequestMapping("showImg")
    public void showImg(String filename, HttpServletResponse response) {
        //通过文件名字获得图片
        File imgFile = new File(uploadPath + "\\" + filename);

        try (
                FileInputStream in = new FileInputStream(imgFile);

             //在这里用的输出流时response的，直接就响应给img这个发出请求的
             OutputStream out = response.getOutputStream();
             )
        {
            IOUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
