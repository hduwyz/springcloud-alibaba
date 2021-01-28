package com.zeus.usercenter.service;

import com.user.provider.api.UserInfoProvider;
import com.user.provider.model.UserInfoDTO;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class UserInfoProviderImpl implements UserInfoProvider {
    @Override
    public UserInfoDTO getUserInfo(String userId) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUserId(userId);
        dto.setUserCode("100001");
        dto.setUserEmail("me@example.com");
        dto.setUserMobile("18888888888");
        dto.setPassword("$2a$10$b378DVDwH8gxS6NNq1smGuxjRNkugvow0.hHM.zSgOtIWqnfh0MLC");
        dto.setUserStatus("1");
        return dto;
    }
}
