package com.joker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.joker.service.ISftpService;
import com.joker.utils.FileUtils;
import com.joker.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

import static com.joker.constant.Constant.DEL_SUCCESS;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Joker
 * @Date: 2023/01/05/20:49
 * @Description:
 */
@Validated
@RestController
@Slf4j
@RequestMapping("/api/daily/")
@ControllerAdvice
public class ImageUploadController {


    @Autowired
    ISftpService iSftpService;

    @PostMapping("uploadImage")
    public R uploadImage(
            @NotNull(message = "请选择文件")MultipartFile file) throws JSchException, SftpException, IOException {
        log.info(file.getOriginalFilename());

        FileUtils.uploadVerify(file);

        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String imagesUrl = iSftpService.put(inputStream,fileName);
        log.info("{{}}",imagesUrl);
        return R.ok().data("imagesUrl",imagesUrl);
    }

    @RequestMapping("delpic")
    public R delete(@RequestBody String[] picUrls){
        log.info("{{}}",picUrls);
        iSftpService.delete(picUrls);

        R ok = R.ok();
        ok.setMessage( DEL_SUCCESS.getMsg() );
        return ok;
    }
}
