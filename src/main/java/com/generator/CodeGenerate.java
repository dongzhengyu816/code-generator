package com.generator;

import com.entity.MessageConstruct;
import com.entity.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: template-test
 * @description: 代码生成
 * @author: Zhengyu Dong
 * @create: 2022-12-16 20:31
 **/
@Component
public class CodeGenerate implements ApplicationRunner {
    @Autowired
    MessageConstruct messageConstruct;

    @Autowired
    private Config config;
    private static final String CONSTANCE_TEMPLATE_PATH = "src/main/java/com/template";
    private static final String CLASS_PATH = "src/main/java/com/result";


    public void outputFile(String templateName,String outputFileName,String templatePath,String outputFilePath,Map<String, Object> dataMap) throws TemplateException, IOException {
        // step2 获取模版路径
        Writer out;
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // step4 加载模版文件
        Template template = configuration.getTemplate(templateName);
        // step5 生成数据
        File docFile = new File(outputFilePath + "/" +outputFileName);
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        // step6 输出文件
        template.process(dataMap, out);
        out.flush();
        out.close();
        System.out.println(outputFileName+"文件创建成功 !");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // step1 创建freeMarker配置实例

        try {
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("packagePath", config.getPackagePath());
            dataMap.put("enumName", config.getEntityName()+"Field");
            dataMap.put("entityName",config.getEntityName()+"Entity");
            dataMap.put("decodeName",config.getEntityName()+"Decode");
            dataMap.put("fieldGroupList", messageConstruct.getList());

            //生成常量类代码
            outputFile("Constants.ftl",config.getEntityName()+"Field"+".java",CONSTANCE_TEMPLATE_PATH,CLASS_PATH,dataMap);
            //生成枚举类代码
            outputFile("DFType.ftl","DFType.java",CONSTANCE_TEMPLATE_PATH,CLASS_PATH,dataMap);
            outputFile("Entity.ftl",config.getEntityName()+"Entity"+".java",CONSTANCE_TEMPLATE_PATH,CLASS_PATH,dataMap);
            if(config.getHighByteFirst() == 1){
                outputFile("DecodeHighByteFirst.ftl",config.getEntityName()+"Decode"+".java",CONSTANCE_TEMPLATE_PATH,CLASS_PATH,dataMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

