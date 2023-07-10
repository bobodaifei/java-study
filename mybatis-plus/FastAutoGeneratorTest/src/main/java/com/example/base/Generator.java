package com.example.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.System.out;

public class Generator {
  /**
   * 当前要生成的模块
   */
  private static final ModuleEnum TO_GENERATE_MODULE = ModuleEnum.OTHER;
  /**
   * 当前要生成的表
   */
  private static final String[] TO_GENERATE_TABLE_NAME = {"wxpay"};

  public static void main(String[] args) {

    FastAutoGenerator
            .create("jdbc:mysql://localhost:3300/gateway?useUnicode=true;characterEncoding=utf8;serverTimezone=Asia/Shanghai;useTimezone=true", "root", "123456")
            .globalConfig(builder ->
                    builder
                            .outputDir(System.getProperty("user.dir") + "/src/main/java") //输出到哪个目录
                            .author("bobodaifei")
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK))
            .packageConfig(builder ->
                    builder
                            .parent(TO_GENERATE_MODULE.modulePackage) // 父包名
                            //  .moduleName("sys")   // 父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
            ).templateConfig(builder ->
                    builder
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .xml("/templates/mapper.xml")
                            .controller("/templates/controller.java")
            ).injectionConfig(builder ->
                    builder
                            .beforeOutputFile((tableInfo, objectMap) -> {
                              out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                            })
                            .customFile(new CustomFile.Builder().fileName("DTO.java").filePath(System.getProperty("user.dir") +  "/src/main/java").enableFileOverride().templatePath("templates/dto.java.ftl").packageName(TO_GENERATE_MODULE.modulePackage + "/pojo/dto").build())
                            .customFile(new CustomFile.Builder().fileName("VO.java").filePath(System.getProperty("user.dir") +  "/src/main/java").enableFileOverride().templatePath("templates/vo.java.ftl").packageName(TO_GENERATE_MODULE.modulePackage + "/pojo/vo").build())
                            .customFile(new CustomFile.Builder().fileName("Query.java").filePath(System.getProperty("user.dir") +  "/src/main/java").enableFileOverride().templatePath("templates/paramQuery.java.ftl").packageName(TO_GENERATE_MODULE.modulePackage + "/pojo/query").build())
                            .customFile(new CustomFile.Builder().fileName("Convert.java").filePath(System.getProperty("user.dir") +  "/src/main/java").templatePath("templates/convert.java.ftl").packageName(TO_GENERATE_MODULE.modulePackage + "/component/convert").build())

            ).strategyConfig(builder ->
                    // 策略配置
                    builder
                            .enableCapitalMode()  // 开启大写命名
                            .addInclude(TO_GENERATE_TABLE_NAME)   // 需要加载的表
            ).strategyConfig(builder ->
                    builder
                            .entityBuilder()
                            .enableFileOverride()  // 覆盖已生成文件
                            .enableChainModel()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .versionPropertyName("version")  // 乐观锁字段  生效要把乐观锁拦截器注入
                            .logicDeletePropertyName("deleted")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            .idType(IdType.ASSIGN_ID)   // 主键的ID类型
            ).strategyConfig(builder ->
                    builder
                            .controllerBuilder()
                            .enableFileOverride()  //  覆盖已生成文件	默认值:false
                            .enableHyphenStyle()
                            .enableRestStyle()
            ).strategyConfig(builder ->
                    builder.
                            serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
            ).templateEngine(new FreemarkerTemplateEngine())
            .execute();

  }


  @AllArgsConstructor
  enum ModuleEnum {

    /**
     * module
     */

//    OTHER("/admin", "com.example", "/common/common-tool"),
    OTHER("com.example"),
    ;

    //    @Getter
//    private String modulePath;
    @Getter
    private String modulePackage;
//    @Getter
//    private String moduleService;

  }

}