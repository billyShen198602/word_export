package com.xinyin.common.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author billy
 * token属性封装
 */

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String clientId;

    private String base64Secret;

    private String name;

    private int expiresSecond;

}
