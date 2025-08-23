package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.TokenRedisEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.TokenRedisRepository;
import com.heartz.byeboo.application.port.out.token.CreateTokenPort;
import com.heartz.byeboo.application.port.out.token.DeleteTokenPort;
import com.heartz.byeboo.application.port.out.token.RetrieveTokenPort;
import com.heartz.byeboo.application.port.out.token.UpdateTokenPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.AuthErrorCode;
import com.heartz.byeboo.domain.model.Token;
import com.heartz.byeboo.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRedisAdapter implements CreateTokenPort, UpdateTokenPort, DeleteTokenPort, RetrieveTokenPort {
    private final TokenRedisRepository tokenRedisRepository;
    @Override
    public void createToken(Token token){
        TokenRedisEntity tokenRedisEntity = TokenRedisEntity.create(token.getId(), token.getRefreshToken());
        tokenRedisRepository.save(tokenRedisEntity);
    }

    @Override
    public void updateToken(Token token){
        TokenRedisEntity tokenRedisEntity = TokenRedisEntity.create(token.getId(), token.getRefreshToken());
        tokenRedisRepository.save(tokenRedisEntity);
    }

    @Override
    public void deleteToken(Long userId){
        tokenRedisRepository.deleteById(userId);
    }

    @Override
    public Token retrieveTokenById(Long userId){
        TokenRedisEntity tokenRedisEntity = tokenRedisRepository.findById(userId)
                .orElseThrow(() -> new CustomException(AuthErrorCode.NOT_FOUND_REFRESH_TOKEN));
        return TokenMapper.toDomain(tokenRedisEntity);
    }
}
