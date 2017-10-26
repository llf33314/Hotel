package com.gt.hotel.generator;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author zhangmz
 * @create 2017/7/9
 */
public class MpGenerator {

    /**
     * 项目的绝对路径
     */
    private static final String   OUT_PUT_BASE_DIR      = "/Users/zhangmz/work/GT/duofee/hotel/hotel-generator/";
    /**
     * 生成*.java 的文件路径
     */
    private static final String   OUT_PUT_JAVA_MODEL    = OUT_PUT_BASE_DIR + "src/main/java";
    /**
     * 需要生成的表
     */
    private static final String[] GENERATOR_TABLE_NAME  = new String[]{"t_hotel", "t_room_category", "t_room", "t_file_record", "sys_dictionary_type", "sys_dictionary", "t_infrastructure_relation",
            "t_hotel_setting", "t_infrastructure", "t_activity", "t_activity_detail", "t_activity_room", "t_room_calendar", "t_order", "t_order_room", "t_hotel_member_setting", "t_room_permanent", "t_authorization",
            "t_food", "t_order_room_customer", "t_order_food", "t_order_food_detail", "sys_operation_log", "t_order_coupons"};
    /**
     * 作者
     */
    private static final String   AUTHOR                = "zhangmz";
    /**
     * 去除表前缀 例如：bus_user 填入bus 生成的 user
     */
    private static final String[] TABLE_PREFIX          = new String[]{};
    /**
     * 生成的包路径
     */
    private static final String   PACKAGE_PATH          = "com.gt.hotel";
    /**
     * s
     */
    private static final String   MODULE_NAME           = "";
    /**
     * 生成mapper.xml 文件路径
     */
    private static final String   GENERATOR_XML_PATH    = OUT_PUT_BASE_DIR + "src/main/java/com/gt/hotel/xml/";
    /**
     * Mapper.java Mapper 后缀修改 DAO
     */
    private static final String   SET_MAPPER_NAME       = "%sDAO";
    /**
     * Mapper.xml Mapper 后缀修改 DAO
     */
    private static final String   SET_XML_NAME          = "%sDAO";
    /**
     * Service.java
     */
    private static final String   SET_SERVICE_NAME      = "%sService";
    /**
     * ServiceImpl.java
     */
    private static final String   SET_SERVICE_IMPL_NAME = "%sServiceImpl";
    /**
     * 数据库方言
     */
    private static final DbType   DB_TYPE               = DbType.MYSQL;
    /**
     * 驱动
     */
    private static final String   DRIVER_NAME           = "com.mysql.jdbc.Driver";
    /**
     * url
     */
    private static final String   URL                   = "jdbc:mysql://113.106.202.51:3306/gt_hotel?characterEncoding=utf8";
    /**
     * 数据库用户名
     */
    private static final String   USER                  = "gt_hotel";
    /**
     * 密码
     */
    private static final String   PASSWORD              = "gt123456";
    /**
     * BaseService
     */
    private static final String   BASE_SERVICE          = "com.gt.hotel.base.BaseService";
    /**
     * BaseServiceImpl
     */
    private static final String   BASE_SERVICE_IMPL     = "com.gt.hotel.base.BaseServiceImpl";
    /**
     * BaseController
     */
    private static final String   BASE_CONTROLLER       = "com.gt.hotel.base.BaseController";

    private static Logger logger = LoggerFactory.getLogger(MpGenerator.class);

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                //输出目录
                new GlobalConfig().setOutputDir(OUT_PUT_JAVA_MODEL)
                        // 是否覆盖文件
                        .setFileOverride(true)
                        // 开启 activeRecord 模式
                        .setActiveRecord(true)
                        // XML 二级缓存
                        .setEnableCache(false)
                        // XML ResultMap
                        .setBaseResultMap(true)
                        // XML columList
                        .setBaseColumnList(true)
                        .setAuthor(AUTHOR)
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName(SET_MAPPER_NAME).setXmlName(SET_XML_NAME).setServiceName(SET_SERVICE_NAME).setServiceImplName(SET_SERVICE_IMPL_NAME)
                // .setControllerName("%sAction")
        ).setDataSource(
                // 数据源配置
                // 数据库类型
                new DataSourceConfig().setDbType(DB_TYPE)
                        .setTypeConvert(new MySqlTypeConvert() {
                            // 自定义数据库表字段类型转换【可选】
                            @Override
                            public DbColumnType processTypeConvert(String fieldType) {
                                return super.processTypeConvert(fieldType);
                            }
                        }).setDriverName(DRIVER_NAME).setUsername(USER).setPassword(PASSWORD).setUrl(URL)).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //全局下划线命名
                        .setDbColumnUnderline(true)
                        // 此处可以修改为您的表前缀
                        .setTablePrefix(TABLE_PREFIX)
                        // 表名生成策略
                        .setNaming(NamingStrategy.underline_to_camel)
                        // 需要生成的表
                        .setInclude(GENERATOR_TABLE_NAME)
                        // .setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        .setSuperEntityColumns(new String[]{}).setTableFillList(tableFillList)
                        // 自定义 mapper 父类
                        //					.setSuperMapperClass( "com.baomidou.mybatisplus.mapper.BaseMapper" )
                        // 自定义 service 父类
                        .setSuperServiceClass(BASE_SERVICE)
                        // 自定义 service 实现类父类
                        .setSuperServiceImplClass(BASE_SERVICE_IMPL)
                        // 自定义 controller 父类
                        .setSuperControllerClass(BASE_CONTROLLER)
                        // 【实体】是否生成字段常量（默认 false）
                        // private static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // private User setName(String name) {this.name = name; return this;}
                        //					.setEntityBuilderModel( true )
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
                // 自定义包路径
                new PackageConfig().setModuleName(MODULE_NAME).setParent(PACKAGE_PATH)
        ).setCfg(
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Arrays.<FileOutConfig>asList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return GENERATOR_XML_PATH + tableInfo.getXmlName() + ".xml";
                    }
                }))).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );
        mpg.execute();
        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        logger.info("输出：{}", mpg.getCfg().getMap().get("abc"));
    }
}
