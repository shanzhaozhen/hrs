package com.hbjs.hrsapi.controller;

import com.hbjs.hrscommon.dto.FileDTO;
import com.hbjs.hrsservice.service.FileService;
import com.hbjs.hrsservice.service.WechatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Tag(name = "wechat", description = "微信接口")
@Slf4j
@RestController("wechat")
@RequiredArgsConstructor
public class WechatController {

    private final WechatService wechatService;


    private final FileService fileService;

    @Operation(summary = "通过网页授权换取code再获取openId")
    @GetMapping("/authorize")
    public String authorizeUser(@RequestParam("targetUrl") String targetUrl,
                                @RequestParam(name = "code", required = false) String code,
                                @RequestParam(name = "checkFollow", required = false) boolean checkFollow,
                                RedirectAttributes redirectAttributes) {
//        if (!StringUtils.hasText(code)) {
//            String getCodeUrl = wechatServiceProvider.getCodeUrl(targetUrl);
////            response.sendRedirect("some-url");
//            return "redirect:" + getCodeUrl;
//        }
//
//        String openid = wechatServiceProvider.getOpenidByCode(code);
//        if (StringUtils.hasText(openid)) {
//            CookiesUtils.setCookie("openid", openid, null);
//            redirectAttributes.addAttribute("openid", openid);
//            log.info("用户openid获取成功：{}", openid);
//        }
//
//        //如果需要辨别该openid是否关注本微信公众号的话
//        if (checkFollow) {
//            wechatService.checkFollow(openid);
//        }

        return "redirect:/" + targetUrl;
    }


    @GetMapping("/token")
    @ResponseBody
    public void checkToken(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//        if (signature != null && WechatUtil.checkSignature(signature, timestamp, nonce)) {
//            try {
//                PrintWriter print = response.getWriter();
//                print.write(echostr);
//                print.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @PostMapping("/upload1")
    @ResponseBody
    public List<FileDTO> upload(@RequestParam("files") MultipartFile[] files) {
        return fileService.saveFile(files);
    }

    @GetMapping("/refresh-token")
    @ResponseBody
    public String refreshToken() {
//        return wechatService.getAccessToken();
        return null;
    }

}
