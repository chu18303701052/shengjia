package com.digov.code.common;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.digov.code.config.DBConfig;
import com.digov.code.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 代码生成器演示
 */
public class CustomGenerator {

    //项目根路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    //数据库信息-连接地址
    private String DB_URL = DBConfig.DB_URL;
    //数据库信息-用户名
    private String DB_USER = DBConfig.DB_USERNAME;
    //数据库信息-密码
    private String DB_PASSWORD = DBConfig.DB_PASSWORD;

    private static final String PACKAGE_PARENT = "com.digov.api";

    //表名
    private String tableName;
    //模板名字
    private String templateName;
    //模板目录
    private String templatePath;

    private String modulePackage;
    private String modulePath;

    //DaoImpl包名
    private String PACKAGE_MAPPER_IMPL;
    //参数的包名
    private String PACKAGE_PARAM;
    //返回结果的包名
    private String PACKAGE_RESULT;
    //模块名字
    private String MODULE_NAME0;

    // 代码生成器
    private AutoGenerator mpg;

    public CustomGenerator(String tableName, String templateName) {
        this.tableName = tableName;
        this.templateName = templateName;
        //模板目录
        templatePath = PROJECT_PATH.concat("/code/src/main/resources").concat("/").concat(templateName);


        modulePackage = tableName.replaceAll("_", "\\.");
        modulePath = tableName.replaceAll("_", Matcher.quoteReplacement(File.separator));

        //DaoImpl包名
        PACKAGE_MAPPER_IMPL = PACKAGE_PARENT.concat(".repository.").concat(modulePackage).concat(".impl");
        //参数的包名
        PACKAGE_PARAM = PACKAGE_PARENT.concat(".entity.param.").concat(modulePackage);
        //返回结果的包名
        PACKAGE_RESULT = PACKAGE_PARENT.concat(".entity.result.").concat(modulePackage);


        //模块名字
        MODULE_NAME0 = tableName.split("_")[0];

        //创建代码生成器
        mpg = new AutoGenerator();

        //全局模板配置
        initGlobalConfig();
        //数据库配置
        initDataSourceConfig();
        //基本包配置
        initPackageConfig();
        //添加自定义模板属性，添加输出模板
        initCustomTemplateConfig();

        initTemplateConfig();
        initStrategyConfig();

    }
    /**
     * 全局配置
     */
    private void initGlobalConfig(){
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //设置代码生成的位置
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");

        gc.setAuthor("system_code");
        gc.setEntityName("%s");
        gc.setMapperName("%sRepository");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        //设置覆盖已存在文件
//        gc.setFileOverride(true);
        gc.setFileOverride(false);
        //是否打开生成代码所在位置的文件夹

        gc.setOpen(false);
        //是否需要需要ActiveRecord特性
        gc.setActiveRecord(false);
        //xml 二级缓存
        gc.setEnableCache(false);
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(false);
        mpg.setGlobalConfig(gc);
        //选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    /**
     * 标准模板配置模板
     */
    private void initTemplateConfig(){
        // 包配置
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        templateConfig.setEntity("common/entity.java");
//        templateConfig.setMapper("common/mapper.java");
//        templateConfig.setService("common/service.java");
//        templateConfig.setServiceImpl("common/serviceImpl.java");
//        templateConfig.setController("common/controller.java");
        templateConfig.setEntity(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        //忽略xml文件配置
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
    }

    /**
     * 策略配置
     */
    private void initStrategyConfig(){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(MODULE_NAME0 + "_");
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
    }


    /**
     * 自定义模板配置
     */
    private void initCustomTemplateConfig(){
        // 自定义配置 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("packageParent", PACKAGE_PARENT);
                map.put("packageMapperImpl", PACKAGE_MAPPER_IMPL);
                map.put("packageParam", PACKAGE_PARAM);
                map.put("packageResult", PACKAGE_RESULT);
                this.setMap(map);
            }
        };
        //设置代码模板
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        //获取模板文件夹下的所有子文件


        //获取模板文件夹下的所有子文件

        List<String> filePaths = FileUtil.getFilePathList(templatePath);
        //如果模板引擎是 freemarker
        for (String filePath : filePaths) {

            String filePath1 = filePath.replaceAll(Matcher.quoteReplacement(File.separator) + Matcher.quoteReplacement(File.separator), "/");
            int template_demo = filePath1.indexOf(templateName);
            filePath1 = filePath1.substring(template_demo, filePath.length());
//            System.out.println(filePath1);
            FileOutConfig fileOutConfig = new FileOutConfig(filePath1) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String[] split = filePath.split(templateName);
                    String targetOutputPath = PROJECT_PATH.concat(split[1]);
                    int i = targetOutputPath.lastIndexOf(File.separator);

                    //对于实现类的特殊处理
                    if(targetOutputPath.contains(File.separator + "impl" + File.separator)){
                        String before1 = targetOutputPath.substring(0, i);
                        i = before1.lastIndexOf(File.separator);
                    }

                    String before = targetOutputPath.substring(0, i+1);
                    System.out.println(before);
                    String after = targetOutputPath.substring(i);

                    after = modulePath.concat(after);

                    after = after.replaceAll("\\.ftl", "");
                    after = after.replaceAll("entity", tableInfo.getEntityName());
                    String concat = before.concat(after);
                    System.out.println(concat);
                    return concat;

                }
            };
            focList.add(fileOutConfig);
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }






    /**
     * 数据源配置
     */
    private void initDataSourceConfig(){
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(DB_URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(DB_USER);
        dsc.setPassword(DB_PASSWORD);
        mpg.setDataSource(dsc);
    }
    /**
     * 包配置
     */
    private void initPackageConfig(){
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        //com.digov
        pc.setParent(PACKAGE_PARENT);
        pc.setEntity("entity.db".concat(".").concat(modulePackage));
        pc.setMapper("repository".concat(".").concat(modulePackage));
        pc.setService("service".concat(".").concat(modulePackage));
        pc.setServiceImpl("service".concat(".").concat(modulePackage).concat(".").concat("impl"));
        pc.setController("controller".concat(".").concat(modulePackage));
        mpg.setPackageInfo(pc);
    }


    /**
     * 执行自动代码生成
     */
    public void createCode() {
//        //获取模板文件夹下的所有子文件

        //进行代码生成
        mpg.execute();
    }


}
