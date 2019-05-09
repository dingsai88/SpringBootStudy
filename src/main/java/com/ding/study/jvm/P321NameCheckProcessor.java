package com.ding.study.jvm;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 *
 E:\DingSai\DingProjectAs_new\SpringBootStudy\src\main\java> javac -encoding UTF-8  com/ding/study/jvm/P321NameChecker.java

 E:\DingSai\DingProjectAs_new\SpringBootStudy\src\main\java> javac -encoding UTF-8  com/ding/study/jvm/P321NameCheckProcessor.java

 * E:\DingSai\DingProjectAs_new\SpringBootStudy\src\main\java>javac -processor com.ding.study.jvm.P321NameCheckProcessor com/ding/study/jvm/P321BADLY_NAMED_CODE.java

 *
 * @author daniel 2019-5-9 0009.
 */

/**
 * 可以用*表示支持所有annotaion
 */
@SupportedAnnotationTypes("*")
/**
 * 只支持jdk1.6
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class P321NameCheckProcessor extends AbstractProcessor {


    private P321NameChecker nameChecker;

    /**
     * 初始化名称检查插件
     */
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new P321NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个节点进行进行名称检查
     */


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if(!roundEnv.processingOver()){
            System.out.println("P321NameCheckProcessor.processingOver:"+roundEnv.getRootElements());
            for (Element element:roundEnv.getRootElements()){
                System.out.println("P321NameCheckProcessor.getRootElements");
                nameChecker.checkNames(element);
            }
        }
        return false;
    }

}
