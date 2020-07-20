package com.cb.annoprocess

import com.cb.anno.MyAnnotation
import java.io.File
import java.lang.Exception
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import javax.tools.StandardLocation

/**
 * author chengbo
 * email chengbo@cloudwalk.cn
 * createTime 2020/7/20
 */
//@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class MyAnnotationProcess : AbstractProcessor() {
    private var messager: Messager? = null
    private var filer: Filer? = null
    private val fileName = "aa.json"

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
        p0?.let {
            messager = it.messager
            filer = it.filer
        }
        messager?.printMessage(Diagnostic.Kind.NOTE, "<------------init")
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        val defaultFilePath = filer?.getResource(
            StandardLocation.CLASS_OUTPUT,
            "",
            fileName
        )?.toUri()?.path
        System.out.println("<<<<<<<<<<"+defaultFilePath)
        val tempDirPath = defaultFilePath?.substring(0, defaultFilePath.indexOf("app") + 4)
        val dirPath = tempDirPath + "src/main/assets"
        System.out.println(dirPath)
        val dirFile = File(dirPath)
        if (!dirFile.exists()) {
            File(dirPath).mkdirs()
        }
        return false
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        val types = LinkedHashSet<String>()
        types.add(MyAnnotation::class.java.canonicalName)
        return types
    }
}