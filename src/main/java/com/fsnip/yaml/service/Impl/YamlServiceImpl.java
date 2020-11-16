package com.fsnip.yaml.service.Impl;

import com.fsnip.yaml.service.YamlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class YamlServiceImpl implements YamlService {

    /**
     * 读取yaml文件生成Map返回
     * @param yaml_url
     * @return
     */
    public Map yamlToMap(String yaml_url) {
        return null;
    }

    /**
     * 读取yaml文件并转换为Map
     * @param yaml_url
     * @return
     */
    public Map returnMapFromYaml(String yaml_url) {
        return null;
    }

    /**
     * 读取yaml文件生成json返回
     * @param file
     * @return
     */
    public String yamlToJson(String file) {
        return null;
    }

    /**
     * 将json转换为yaml格式并生成Yaml文件
     * @param generateYamlPath
     * @param jsonStr
     */
    public void createYaml(String generateYamlPath, String jsonStr) {

    }

    /**
     * 将数据写入ymal文件
     * @param generateYamlPath
     * @param map
     */
    public void createYamlFile(String generateYamlPath, Map map) {

    }
}
