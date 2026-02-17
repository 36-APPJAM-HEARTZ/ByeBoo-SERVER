package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.entity.CommonQuestEntity;
import com.heartz.byeboo.adapter.out.persistence.repository.CommonQuestRepository;
import com.heartz.byeboo.application.port.out.commonquest.RetrieveCommonQuestPort;
import com.heartz.byeboo.core.exception.CustomException;
import com.heartz.byeboo.domain.exception.CommonQuestErrorCode;
import com.heartz.byeboo.domain.model.CommonQuest;
import com.heartz.byeboo.mapper.CommonQuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommonQuestPersistenceAdapter implements RetrieveCommonQuestPort {

    private final CommonQuestRepository commonQuestRepository;

    @Override
    public CommonQuest getCommonQuestById(Long commonQuestId) {
        CommonQuestEntity commonQuestEntity = commonQuestRepository.findById(commonQuestId)
                .orElseThrow(() -> new CustomException(CommonQuestErrorCode.COMMON_QUEST_NOT_FOUND));

        return CommonQuestMapper.toDomain(commonQuestEntity);
    }
}
