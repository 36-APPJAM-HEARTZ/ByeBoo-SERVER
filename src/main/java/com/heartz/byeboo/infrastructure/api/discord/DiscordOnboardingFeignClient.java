package com.heartz.byeboo.infrastructure.api.discord;

import com.heartz.byeboo.config.FeignConfig;
import com.heartz.byeboo.infrastructure.dto.discord.DiscordMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "discord-onboarding-client",
        url = "${logging.discord.onboarding-webhook-url}",
        configuration = FeignConfig.class)
public interface DiscordOnboardingFeignClient {

    @PostMapping()
    void sendAlarm(@RequestBody DiscordMessageDto message);
}
