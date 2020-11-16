package com.fsnip.yaml.service;

import java.util.Map;

/**
 * yaml转换服务
 */
public interface YamlService {

    Map yamlToMap(String yaml_url);

    Map returnMapFromYaml(String yaml_url);

    String yamlToJson(String file);

    void createYaml(String generateYamlPath,String jsonStr);

    void createYamlFile(String generateYamlPath,Map map);

}
