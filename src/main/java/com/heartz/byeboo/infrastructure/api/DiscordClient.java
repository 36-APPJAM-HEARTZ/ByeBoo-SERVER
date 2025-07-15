package com.heartz.byeboo.infrastructure.api;

import com.heartz.byeboo.config.DiscordFeignConfig;
import com.heartz.byeboo.infrastructure.dto.DiscordMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discord-client",
        url = "${logging.discord.webhook-url}",
        configuration = DiscordFeignConfig.class)
public interface DiscordClient {

    @PostMapping()
    void sendAlarm(@RequestBody DiscordMessageDto message);
}
