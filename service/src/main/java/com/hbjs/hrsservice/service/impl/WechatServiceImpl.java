package com.hbjs.hrsservice.service.impl;

import com.hbjs.hrsservice.service.WechatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WechatServiceImpl implements WechatService {

    private final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    private final WechatServiceProvider wechatServiceProvider;

    @Override
    public void checkFollow(String openid) {
        boolean isFollow = wechatServiceProvider.checkFollowByAccessTokenOpenid(openid);
        /*
         * 如果还没有关注公众号，跳转到关注页面
         */
        if (!isFollow) {
            logger.warn("用户：{}，尚未关注公众号", openid);
//            throw new WechatErrorException(WechatErrorCode.NONFOLLOW.getCode(), "尚未关注", "未关注公众号的用户将不能收到HR的回复");
        }
    }

}
