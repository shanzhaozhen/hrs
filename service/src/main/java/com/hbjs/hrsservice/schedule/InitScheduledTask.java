package com.hbjs.hrsservice.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.hbjs.hrsservice.service.TaskService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitScheduledTask implements ApplicationRunner {

    private final TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            taskService.initTask();
        } catch (Exception e) {
            log.error("定时任务初始化出现异常");
        }
    }

}
