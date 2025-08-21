package com.heartz.byeboo.application.port.out.userinfo;

import com.heartz.byeboo.domain.type.EPlatform;

public interface UnlinkUserInfoPort {
    void revoke(final EPlatform platform, final String code, final String serialId);
}
