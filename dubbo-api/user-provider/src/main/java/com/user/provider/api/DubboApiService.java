package com.user.provider.api;

public interface DubboApiService {

    /**
     * 获取名称
     * @return
     */
    String getUserName();

    /**
     * 添加用户
     * @param userName
     * @return
     */
    Integer addUser(String userName);
}
