package com.zeus.gatewaycenter;

import com.alibaba.fastjson.JSONObject;
import com.user.provider.api.UserInfoProvider;
import com.user.provider.model.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCenterApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

//        @NacosValue(value = "${gateway.name}", autoRefreshed = true)
        @Value("${gateway.name}")
        private String testValue;
        @Autowired
        LoadBalancerClient loadBalancerClient;

        @DubboReference
        private UserInfoProvider userInfoProvider;

        @GetMapping("/test")
        public String test() {
            // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
            System.out.println("vaule:{}" + testValue);
            ServiceInstance serviceInstance = loadBalancerClient.choose("gateway");
            String url = serviceInstance.getUri() + "/hello?name=" + "didi";
            RestTemplate restTemplate = new RestTemplate();
//            String result = restTemplate.getForObject(url, String.class);
            UserInfoDTO userInfoDTO = userInfoProvider.getUserInfo("test");
            System.out.println("结果：" + JSONObject.toJSONString(userInfoDTO));
            return "Invoke : " + url + ", return : " + testValue;
        }
    }


}
