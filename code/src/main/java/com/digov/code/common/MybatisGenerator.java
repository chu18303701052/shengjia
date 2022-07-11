package com.digov.code.common;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器演示
 */
public class MybatisGenerator {
    public String TEMPLATE_PRIFIX = "common";

    //数据库信息-连接地址
    private static String DB_URL = "jdbc:mysql://digov:3306/yh_talent_ehome?useUnicode=true&characterEncoding=utf8";
    //数据库信息-用户名
    private static String DB_USER = "root";
    //数据库信息-密码
    private static String DB_PASSWORD = "digov@2019";

    //项目根路径
    private  static final String PROJECT_PATH = System.getProperty("user.dir");
    private  static final String ENTITY_PROJECT_PATH = PROJECT_PATH.concat("/entity");
    private  static final String REPOSITORY_PROJECT_PATH = PROJECT_PATH.concat("/repository");
    private  static final String SERVICE_PROJECT_PATH = PROJECT_PATH.concat("/service");
    private  static final String WEB_PROJECT_PATH = PROJECT_PATH.concat("/api");

    private static final String PACKAGE_PARENT = "com.digov";
    //顶级包名
    //  private static final String PROJECT_PATH = "D://digov_project/urban-brain";
    //表名
    private String TABLE_NAME;
    //模块名字
    private String MODULE_PACKAGE;
    //模块名字
    private String MODULE_PATH;
    //模块名字
    private String MODULE_NAME0;
    //模块名字
//    private String MODULE_NAME;


    //DaoImpl包名
    private String PACKAGE_MAPPER_IMPL;
    //参数的包名
    private String PACKAGE_PARAM;
    //返回结果的包名
    private String PACKAGE_RESULT;

    //返回结果的包名
    private String PACKAGE_ENTITY;
    //返回结果的包名
    private String PACKAGE_REPOSITORY;
    //返回结果的包名
    private String PACKAGE_SERVICE;
    //返回结果的包名
    private String PACKAGE_SERVICE_IMPL;
    //返回结果的包名
    private String PACKAGE_CONTROLLER;


    //mapper 的xml输入路径前缀
    private String XML_PATH_PREFIX;
    //DaoImpl 的输出路径前缀
    private String REPOSITORY_IMPL_PATH_PREFIX;
    //param 的输入路径前缀
    private String PARAM_PATH_PREFIX;
    //result 的输入路径前缀
    private String RESULT_PATH_PREFIX;


    //entity 的输入路径前缀
    private String ENTITY_PATH_PREFIX;
    //repository 的输入路径前缀
    private String REPOSITORY_PATH_PREFIX;
    //service 的输入路径前缀
    private String SERVICE_PATH_PREFIX;
    //serviceImpl 的输入路径前缀
    private String SERVICE_IMPL_PATH_PREFIX;
    //controller 的输入路径前缀
    private String CONTROLLER_PATH_PREFIX;



    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    public MybatisGenerator(String tableName, String templatePrefix) {
        TEMPLATE_PRIFIX = templatePrefix;
        TABLE_NAME = tableName;
        //模块名字
        MODULE_NAME0 = TABLE_NAME.split("_")[0];
        MODULE_PACKAGE = TABLE_NAME.split("_")[0] + "." + TABLE_NAME.split("_")[1] + "." + TABLE_NAME.split("_")[2];
        MODULE_PATH = TABLE_NAME.split("_")[0] + "/" + TABLE_NAME.split("_")[1] + "/" + TABLE_NAME.split("_")[2];
        //DaoImpl包名
        PACKAGE_MAPPER_IMPL = PACKAGE_PARENT.concat(".repository.").concat(MODULE_PACKAGE).concat(".impl");
        //参数的包名
        PACKAGE_PARAM = PACKAGE_PARENT.concat(".param.").concat(MODULE_PACKAGE);
        //返回结果的包名
        PACKAGE_RESULT = PACKAGE_PARENT.concat(".result.").concat(MODULE_PACKAGE);
        PACKAGE_ENTITY = PACKAGE_PARENT.concat(".entity.").concat(MODULE_PACKAGE);
        PACKAGE_REPOSITORY= PACKAGE_PARENT.concat(".repository.").concat(MODULE_PACKAGE);
        PACKAGE_SERVICE = PACKAGE_PARENT.concat(".service.").concat(MODULE_PACKAGE);
        PACKAGE_SERVICE_IMPL= PACKAGE_PARENT.concat(".service.").concat(MODULE_PACKAGE).concat(".impl");
        PACKAGE_CONTROLLER = PACKAGE_PARENT.concat(".api.controller.").concat(MODULE_PACKAGE);


        //mapper 的xml输入路径前缀
        XML_PATH_PREFIX = REPOSITORY_PROJECT_PATH + "/src/main/resources/mapper/".concat(MODULE_PATH).concat("/");
        //RepositoryImpl 的输出路径前缀
        REPOSITORY_IMPL_PATH_PREFIX = REPOSITORY_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_MAPPER_IMPL.replaceAll("\\.", "\\/")).concat("/");
        //param 的输入路径前缀
        PARAM_PATH_PREFIX = ENTITY_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_PARAM.replaceAll("\\.", "\\/")).concat("/");
        //result 的输入路径前缀
        RESULT_PATH_PREFIX = ENTITY_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_RESULT.replaceAll("\\.", "\\/")).concat("/");

        //entity 实体类
        ENTITY_PATH_PREFIX = ENTITY_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_ENTITY.replaceAll("\\.", "\\/")).concat("/");

        REPOSITORY_PATH_PREFIX = REPOSITORY_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_REPOSITORY.replaceAll("\\.", "\\/")).concat("/");

        SERVICE_PATH_PREFIX = SERVICE_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_SERVICE.replaceAll("\\.", "\\/")).concat("/");

        SERVICE_IMPL_PATH_PREFIX = SERVICE_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_SERVICE_IMPL.replaceAll("\\.", "\\/")).concat("/");

        CONTROLLER_PATH_PREFIX = WEB_PROJECT_PATH + "/src/main/java/".concat(PACKAGE_CONTROLLER.replaceAll("\\.", "\\/")).concat("/");
    }

    /**
     * 全局配置
     */
    private void initGlobalConfig(){
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //设置代码生成的位置
        gc.setOutputDir(PROJECT_PATH + "/src/main/java");
        gc.setAuthor("template_system");
        gc.setEntityName("%s");
        gc.setMapperName("%sRepository");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        //设置覆盖已存在文件
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
        pc.setParent(PACKAGE_PARENT);
        pc.setEntity("entity".concat(".").concat(MODULE_PACKAGE));
        pc.setMapper("repository".concat(".").concat(MODULE_PACKAGE));
        pc.setService("service".concat(".").concat(MODULE_PACKAGE));
        pc.setServiceImpl("service".concat(".").concat(MODULE_PACKAGE).concat(".").concat("impl"));
        pc.setController("api.controller".concat(".").concat(MODULE_PACKAGE));
        mpg.setPackageInfo(pc);
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


        // 如果模板引擎是 freemarker
        String templatePath = TEMPLATE_PRIFIX + "/base/entityMapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return  XML_PATH_PREFIX + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //自定义dao实现类模板
        String mapperImplJava = TEMPLATE_PRIFIX + "/base/entityRepositoryImpl.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(mapperImplJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return REPOSITORY_IMPL_PATH_PREFIX + tableInfo.getEntityName() + "RepositoryImpl" + StringPool.DOT_JAVA;
            }
        });

        //自定义新增参数类模板
        String entityAddParamJava = TEMPLATE_PRIFIX + "/base/entityAddParam.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityAddParamJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PARAM_PATH_PREFIX + tableInfo.getEntityName() + "AddParam" + StringPool.DOT_JAVA;
            }
        });

        String entityIdParamJava = TEMPLATE_PRIFIX + "/base/entityIdParam.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityIdParamJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PARAM_PATH_PREFIX + tableInfo.getEntityName() + "IdParam" + StringPool.DOT_JAVA;
            }
        });

        if(TEMPLATE_PRIFIX.equals("common_excel")){
            String entityAddBatchParamJava = TEMPLATE_PRIFIX + "/base/entityAddBatchParam.java.ftl";
            // 自定义输出配置
            focList.add(new FileOutConfig(entityAddBatchParamJava) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return PARAM_PATH_PREFIX + tableInfo.getEntityName() + "AddBatchParam" + StringPool.DOT_JAVA;
                }
            });

            String entityExcelListResultJava = TEMPLATE_PRIFIX + "/list/entityExcelListResult.java.ftl";
            // 自定义输出配置
            focList.add(new FileOutConfig(entityExcelListResultJava) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "ExcelListResult" + StringPool.DOT_JAVA;
                }
            });
        }

        //单条返回结果
        String entityIdResultJava = TEMPLATE_PRIFIX + "/base/entityIdResult.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityIdResultJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "IdResult" + StringPool.DOT_JAVA;
            }
        });

        String entityJava = TEMPLATE_PRIFIX + "/base/entity.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return ENTITY_PATH_PREFIX + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });

        String repositoryJava = TEMPLATE_PRIFIX + "/base/entityRepository.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(repositoryJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return REPOSITORY_PATH_PREFIX + tableInfo.getEntityName() + "Repository" + StringPool.DOT_JAVA;
            }
        });

        String serviceJava = TEMPLATE_PRIFIX + "/base/entityService.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(serviceJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return SERVICE_PATH_PREFIX + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });

        String serviceImplJava = TEMPLATE_PRIFIX + "/base/entityServiceImpl.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(serviceImplJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return SERVICE_IMPL_PATH_PREFIX + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });


        //可选模板
        String entityResultJava = TEMPLATE_PRIFIX + "/list/entityResult.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityResultJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "Result" + StringPool.DOT_JAVA;
            }
        });

        String entityListResultJava = TEMPLATE_PRIFIX + "/list/entityListResult.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityListResultJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "ListResult" + StringPool.DOT_JAVA;
            }
        });

        String entityListParamJava = TEMPLATE_PRIFIX + "/list/entityListParam.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityListParamJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PARAM_PATH_PREFIX + tableInfo.getEntityName() + "ListParam" + StringPool.DOT_JAVA;
            }
        });


        String entityChartParam = TEMPLATE_PRIFIX + "/chart/entityChartParam.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityChartParam) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return PARAM_PATH_PREFIX + tableInfo.getEntityName() + "ChartParam" + StringPool.DOT_JAVA;
            }
        });

        String entityChartResult = TEMPLATE_PRIFIX + "/chart/entityChartResult.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityChartResult) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "ChartResult" + StringPool.DOT_JAVA;
            }
        });

        String entityChartListResult = TEMPLATE_PRIFIX + "/chart/entityChartListResult.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(entityChartListResult) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return RESULT_PATH_PREFIX + tableInfo.getEntityName() + "ChartListResult" + StringPool.DOT_JAVA;
            }
        });


        String controllerJava = TEMPLATE_PRIFIX + "/base/entityController.java.ftl";
        // 自定义输出配置
        focList.add(new FileOutConfig(controllerJava) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return CONTROLLER_PATH_PREFIX + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });

        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */



        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
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
        strategy.setInclude(TABLE_NAME);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(MODULE_NAME0 + "_");
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
    }

    private void run(){
        initGlobalConfig();
        initDataSourceConfig();
        initPackageConfig();
        initCustomTemplateConfig();
        initTemplateConfig();
        initStrategyConfig();
        mpg.execute();
    }

    public void craeteCode(){
        run();
    }
}
