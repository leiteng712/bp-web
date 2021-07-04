package com.example.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * @description:
 * @author: leiteng
 * @email: leiteng712@gmail.com
 * @since: 2021/6/2 4:27 下午
 */
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws IOException {
        // 数据库配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 全局配置
        GlobalConfig globalConfig = GeneratorBuilder.globalConfigBuilder()
                .openDir(true)
                .outputDir("/opt/baomidou")
                .author("leiteng")
                .dateType(DateType.TIME_PACK)
                .enableSwagger()
                .commentDate("yyyy-MM-dd")
                .build();

        /**
         * 包配置(PackageConfig)
         * 方法	说明	示例
         * parent	父包名	默认值:com.baomidou
         * moduleName	父包模块名	sys 默认值:无
         * entity	Entity包名	默认值:entity
         * service	Service包名	默认值:service
         * serviceImpl	Service Impl包名	默认值:service.impl
         * mapper	Mapper包名	默认值:mapper
         * xml	Mapper XML包名	默认值:mapper.xml
         * controller	Controller包名	默认值:controller
         */
        PackageConfig packageConfig = new PackageConfig.Builder().build();

        /**
         *
         */
        TemplateConfig templateConfig = new TemplateConfig.Builder()

                .build();//激活所有默认模板

        /**
         * Entity配置
         */
        // todo

        /**
         * 策略
         */
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableSkipView()
                .enableCapitalMode()
                .build();

        /**
         * 代码生成器
         */
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .packageInfo(packageConfig)
                .template(templateConfig)
                .strategy(strategyConfig);

        autoGenerator.execute();

    }

    private static DataSourceConfig getDataSourceConfig() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResource("generator.properties").openStream();
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            return new DataSourceConfig.Builder(url, username, password).build();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
