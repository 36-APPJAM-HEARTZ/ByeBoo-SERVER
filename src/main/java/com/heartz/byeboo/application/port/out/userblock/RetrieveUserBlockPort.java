package com.heartz.byeboo.application.port.out.userblock;

import com.heartz.byeboo.adapter.out.persistence.repository.projection.UserBlockInfoProjection;

import java.util.List;

public interface RetrieveUserBlockPort {
    List<UserBlockInfoProjection> getUserBlockInfo(Long userId);
}
