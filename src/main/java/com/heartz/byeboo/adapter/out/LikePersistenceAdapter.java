package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.LikeEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.LikeRepository;
import com.heartz.byeboo.application.port.out.like.CreateLikePort;
import com.heartz.byeboo.application.port.out.like.DeleteLikePort;
import com.heartz.byeboo.application.port.out.like.RetrieveLikePort;
import com.heartz.byeboo.domain.model.Like;
import com.heartz.byeboo.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikePersistenceAdapter implements CreateLikePort, DeleteLikePort, RetrieveLikePort {

    private final LikeRepository likeRepository;

    @Override
    public void createLike(Like like) {
        LikeEntity likeEntity = LikeMapper.toEntity(like);
        likeRepository.save(likeEntity);
    }

    @Override
    public void deleteByUserIdAndUserCommonQuestId(
            Long userId,
            Long userCommonQuestId
    ) {
        likeRepository.deleteByUserIdAndUserCommonQuestId(userId, userCommonQuestId);
    }

    @Override
    public boolean existsByUserIdAndUserCommonQuestId(
            Long userId,
            Long userCommonQuestId
    ) {
        return likeRepository.existsByUserIdAndUserCommonQuestId(userId, userCommonQuestId);
    }

    @Override
    public int countByUserCommonQuestId(Long userCommonQuestId) {
        return likeRepository.countByUserCommonQuestId(userCommonQuestId);
    }
}
