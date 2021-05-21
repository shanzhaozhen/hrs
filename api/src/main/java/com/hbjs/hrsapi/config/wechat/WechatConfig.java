package com.hbjs.hrsapi.config.wechat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "wechat")
@Configuration
@Getter
@Setter
public class WechatConfig {

    public static final String auth = "https://open.weixin.qq.com/connect/oauth2/authorize";

    public static final String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";

    public static final String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    public static final String accessTokenClientUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    public static final String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    public static final String userInfoClientUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    public static final String sendMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    public static final String apiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=wx_card";

    public static final String uploadImageUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

    public static final String baseScope = "snsapi_base";

    public static final String userinfoScope = "snsapi_userinfo";

    public static final String homeUrl = "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MjM5NzU5NDM3MA==#wechat_redirect";

    private String appId;

    private String appSecret;

    private String projectUrl;

}
