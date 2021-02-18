package com.zeus.core;

import com.zeus.core.utils.Builder;
import lombok.Data;

import java.util.*;

@Data
public class GirlFriendBuildTest {

    private String name;
    private int age;
    private int bust;
    private int waist;
    private int hips;
    private List<String> hobby;
    private String birthday;
    private String address;
    private String mobile;
    private String email;
    private String hairColor;
    private Map<String, String> gift;

    public void addHobby(String hobby) {
        this.hobby = Optional.ofNullable(this.hobby).orElse(new ArrayList<>());
        this.hobby.add(hobby);
    }
    public void addGift(String day, String gift) {
        this.gift = Optional.ofNullable(this.gift).orElse(new HashMap<>());
        this.gift.put(day, gift);
    }
    public void setVitalStatistics(int bust, int waist, int hips) {
        this.bust = bust;
        this.waist = waist;
        this.hips = hips;
    }
    public static void main(String[] args) {
        GirlFriendBuildTest myGirlFriend = Builder.of(GirlFriendBuildTest::new)
                .with(GirlFriendBuildTest::setName, "小美")
                .with(GirlFriendBuildTest::setAge, 18)
                .with(GirlFriendBuildTest::setVitalStatistics, 33, 23, 33)
                .with(GirlFriendBuildTest::setBirthday, "2001-10-26")
                .with(GirlFriendBuildTest::setAddress, "上海浦东")
                .with(GirlFriendBuildTest::setMobile, "18688888888")
                .with(GirlFriendBuildTest::setEmail, "pretty-xiaomei@qq.com")
                .with(GirlFriendBuildTest::setHairColor, "浅棕色带点微卷")
                .with(GirlFriendBuildTest::addHobby, "逛街")
                .with(GirlFriendBuildTest::addHobby, "购物")
                .with(GirlFriendBuildTest::addHobby, "买东西")
                .with(GirlFriendBuildTest::addGift, "情人节礼物", "LBR 1912女王时代")
                .with(GirlFriendBuildTest::addGift, "生日礼物", "迪奥烈焰蓝金")
                .with(GirlFriendBuildTest::addGift, "纪念日礼物", "阿玛尼红管唇釉")
                .build();
        System.out.println(myGirlFriend.toString());
    }
}
