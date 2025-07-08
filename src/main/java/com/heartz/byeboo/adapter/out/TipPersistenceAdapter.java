package com.heartz.byeboo.adapter.out;

import com.heartz.byeboo.adapter.out.persistence.repository.TipRepository;
import com.heartz.byeboo.application.port.out.RetrieveTipPort;
import com.heartz.byeboo.domain.model.Quest;
import com.heartz.byeboo.domain.model.Tip;
import com.heartz.byeboo.mapper.TipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TipPersistenceAdapter implements RetrieveTipPort {

    private final TipRepository tipRepository;

    @Override
    public List<Tip> getTipsByQuestId(Long questId, Quest quest) {
        return tipRepository.findAllByQuestId(questId).stream()
                .map(tipEntity -> TipMapper.toDomain(tipEntity, quest))
                .toList();
    }
}
