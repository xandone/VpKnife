package com.test.vpknife;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * @author: Admin
 * created on: 2021/9/10 10:36
 * description:
 */
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class VpProcessor extends AbstractProcessor {
    private Messager messager;
    private Elements elementUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        messager = processingEnvironment.getMessager();
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindVp.class.getCanonicalName());
//        messager.printMessage(Diagnostic.Kind.NOTE, "BindVp=" + BindVp.class.getCanonicalName());
        return annotationTypes;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindVp.class);
        String clazzName = "Adapter" + "$Creater";
        for (Element element : elements) {
            System.out.println("next()=" + element.asType().toString());
            MethodSpec.Builder method1 = MethodSpec.methodBuilder("getItem")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(int.class, "position")
                    .addStatement("return null")
                    .returns(ClassName.get("androidx.fragment.app", "Fragment"));

            MethodSpec.Builder method2 = MethodSpec.methodBuilder("getCount")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("return 0")
                    .returns(int.class);

            MethodSpec.Builder constructorMethods = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(ClassName.get("androidx.fragment.app", "FragmentManager"), "fm")
                    .addParameter(int.class, "behavior")
                    .addStatement("super(fm,behavior)");


            TypeSpec clazz = TypeSpec.classBuilder(clazzName)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(ClassName.get(element.asType()))
                    .addMethod(constructorMethods.build())
                    .addMethod(method1.build())
                    .addMethod(method2.build())
                    .build();
            JavaFile javaFile = JavaFile.builder(elementUtils.getPackageOf(element).getQualifiedName().toString(), clazz)
                    .build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }


        return true;
    }

}
