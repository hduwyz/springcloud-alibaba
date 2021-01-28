package com.user.provider.api;


import com.user.provider.model.UserInfoDTO;

public interface UserInfoProvider {
    UserInfoDTO getUserInfo(String userId);
}
