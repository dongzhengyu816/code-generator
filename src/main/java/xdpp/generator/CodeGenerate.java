package xdpp.generator;

import lombok.extern.slf4j.Slf4j;
import xdpp.entity.MessageConstruct;
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
@Slf4j
public class CodeGenerate implements ApplicationRunner {
    @Autowired
    MessageConstruct messageConstruct;

    private static final String CONSTANCE_TEMPLATE_PATH = "src/main/java/xdpp/template";
    private static final String CLASS_PATH = "src/main/java/xdpp/protocol";

    public static final String CONSTANT_DIR_NAME = "/constant/";

    public static final String DECODE_DIR_NAME = "/decode/";

    public static final String ENTITY_DIR_NAME = "/entity/";


    public void outputFile(String templateName, String outputFileName, String templatePath, String outputFilePath, Map<String, Object> dataMap) throws TemplateException, IOException {
        // step2 获取模版路径
        Writer out;
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // step4 加载模版文件
        Template template = configuration.getTemplate(templateName);
        // step5 生成数据
        File docFile = new File(outputFilePath + outputFileName);
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
        // step6 输出文件
        template.process(dataMap, out);
        out.flush();
        out.close();
        System.out.println(outputFileName + "文件创建成功 !");
    }

    /***
     * 判断文件夹是否存在，不存在则生成文件夹
     * @param dirPath 文件夹路径
     * @param dirName 文件夹名字
     */
    private void createDir(String dirPath, String dirName) {
        File folder = new File(dirPath +"/"+ dirName);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            log.info("{}文件夹创建成功", dirName);
        } else {
            log.info("{}文件夹已存在", dirName);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // step1 创建freeMarker配置实例
        String PACKAGE_DIR = "/"+messageConstruct.getPackagePath();
        try {
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("packagePath", messageConstruct.getPackagePath());
            dataMap.put("enumName", messageConstruct.getEntityName() + "Field");
            dataMap.put("entityName", messageConstruct.getEntityName() + "Entity");
            dataMap.put("decodeName", messageConstruct.getEntityName() + "Decode");
            dataMap.put("fieldGroupList", messageConstruct.getList());

            //生成常量类代码
            createDir(CLASS_PATH + PACKAGE_DIR, CONSTANT_DIR_NAME);
            outputFile("Constants.ftl",
                    messageConstruct.getEntityName() + "Field" + ".java",
                    CONSTANCE_TEMPLATE_PATH,
                    CLASS_PATH + PACKAGE_DIR + CONSTANT_DIR_NAME,
                    dataMap);


            //生成枚举类代码
            createDir(CLASS_PATH + PACKAGE_DIR, CONSTANT_DIR_NAME);
            outputFile("DFType.ftl",
                    "DFType.java",
                    CONSTANCE_TEMPLATE_PATH,
                    CLASS_PATH + PACKAGE_DIR + CONSTANT_DIR_NAME,
                    dataMap);

            //生成实体类代码
            createDir(CLASS_PATH + PACKAGE_DIR, ENTITY_DIR_NAME);
            outputFile("Entity.ftl",
                    messageConstruct.getEntityName() + "Entity" + ".java",
                    CONSTANCE_TEMPLATE_PATH,
                    CLASS_PATH + PACKAGE_DIR + ENTITY_DIR_NAME,
                    dataMap);

            //高字节先传，生成decode代码
            createDir(CLASS_PATH + PACKAGE_DIR, DECODE_DIR_NAME);
            if (messageConstruct.isHighByteFirst() == true) {
                outputFile("DecodeHighByteFirst.ftl",
                        messageConstruct.getEntityName() + "Decode" + ".java",
                        CONSTANCE_TEMPLATE_PATH,
                        CLASS_PATH + PACKAGE_DIR + DECODE_DIR_NAME,
                        dataMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

