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


    @Override
    public void checkFollow(String openid) {
        logger.warn("用户：{}，尚未关注公众号", openid);
    }

}
