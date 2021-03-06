package com.den.sdsdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * mybatis-plus mapper、service、serviceImpl 代码生成
 * @author fatKarin
 * @date 2019/10/24 16:34
 */
public class CodeGenerator {

    // 项目路径
    private static final String projectPath = System.getProperty("user.dir");

    private static final String srcPath = "/src/main/java";

    private static final String packagePath = "/com/den/sdsdemo";

    private static final String mapperPath = "/mapper";

    private static final String servicePath = "/service";

    private static final String serviceImplPath = "/service/impl";

    private static final String classSuffix = ".java";

    private static final String xmlSuffix = ".xml";


    public static void main(String[]  args) {
        // 实体类名称
        String entityName = "Table4";
        // 模块名称，代码文件生成地
        String moduleName = "/sharddingsphere-demo";

        String mapperTemplate = "package com.den.sdsdemo.mapper;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import com.den.sdsdemo.entity." +entityName+ ";" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " *  " + entityName + "Mapper 接口\n" +
                " * </p>\n" +
                " *\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate()+"\n" +
                " */\n" +
                "\n" +
                "public interface " + entityName + "Mapper extends BaseMapper<" + entityName + "> {\n" +
                "\n" +
                "}";

        String serviceTemplate ="package com.den.sdsdemo.service;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.extension.service.IService;\n" +
                "import com.den.sdsdemo.entity." + entityName + ";\n" +
                "\n" +
                "/**\n" +
                " * <p>\n" +
                " *  服务类\n" +
                " * </p>\n" +
                " *\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate() + "\n" +
                " */\n" +
                "public interface I" + entityName +"Service extends IService<" + entityName + "> {\n" +
                "\n" +
                "}";

        String serviceImplTemplate = "package com.den.sdsdemo.service.impl;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n" +
                "import com.den.sdsdemo.entity." + entityName+ ";\n" +
                "import com.den.sdsdemo.mapper." + entityName+ "Mapper;\n" +
                "import com.den.sdsdemo.service.I" + entityName+ "Service;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "/**\n" +
                " * @author fatKarin\n" +
                " * @since " + DateUtil.getDate() + "\n" +
                " */\n" +
                "@Service\n" +
                "public class " + entityName+ "ServiceImpl extends ServiceImpl<" + entityName+ "Mapper, " + entityName+ "> implements I" + entityName+ "Service {\n" +
                "\n" +
                "}";

        String xmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                "<mapper namespace=\"com.den.we.mapper." + entityName+ "Mapper\">\n" +
                "</mapper>";

        String mapperFilePath = projectPath + moduleName + srcPath + packagePath + mapperPath + "/" + entityName+ "Mapper" + classSuffix;

        String serviceFilePath = projectPath + moduleName + srcPath + packagePath + servicePath + "/I" + entityName+ "Service" + classSuffix;

        String serviceImplFilePath = projectPath + moduleName + srcPath + packagePath + serviceImplPath + "/" + entityName+ "ServiceImpl" + classSuffix;

        String xmlFilePath = projectPath + moduleName + srcPath + packagePath + mapperPath + "/" + entityName+ "Mapper" + xmlSuffix;

        try {
            File file = new File(mapperFilePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(mapperTemplate);// 往文件里写入字符串

            file = new File(serviceFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(serviceTemplate);// 往文件里写入字符串

            file = new File(serviceImplFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(serviceImplTemplate);// 往文件里写入字符串

            file = new File(xmlFilePath);
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(xmlTemplate);// 往文件里写入字符串

            ps.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(System.getProperty("user.dir"));
    }
}
