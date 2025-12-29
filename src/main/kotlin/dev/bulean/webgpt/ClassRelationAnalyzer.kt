//package dev.bulean.webgpt
//
//import com.intellij.ide.highlighter.JavaFileType
//import com.intellij.openapi.project.Project
//import com.intellij.openapi.vfs.VirtualFile
//import com.intellij.psi.PsiJavaFile
//import com.intellij.psi.PsiManager
//import com.intellij.psi.search.FileTypeIndex
//import com.intellij.psi.search.GlobalSearchScope
//import org.jetbrains.kotlin.idea.KotlinFileType
//import org.jetbrains.kotlin.psi.KtClassOrObject
//import org.jetbrains.kotlin.psi.KtFile
//import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
//import com.intellij.ide.highlighter.JavaFileType
//import org.jetbrains.kotlin.idea.KotlinFileType
//
//
//object ClassRelationAnalyzer {
//    fun getClassRelations(project: Project): List<String> {
//        val result = mutableListOf<String>()
//        val psiManager = PsiManager.getInstance(project)
//        val scope = GlobalSearchScope.projectScope(project)
//
//        FileTypeIndex.getFiles(JavaFileType.INSTANCE, scope).forEach { vf: VirtualFile ->
//            (psiManager.findFile(vf) as? PsiJavaFile)?.let { javaFile ->
//                javaFile.classes.forEach { cls ->
//                    val name = cls.name ?: return@forEach
//                    val superName = cls.superClass?.name
//                    val interfaces = cls.interfaces.mapNotNull { it.name }
//                    result.add(buildRelationString(name, superName, interfaces))
//                }
//            }
//        }
//
//        // --- CORRECCIÓN: Usar KotlinFileType.INSTANCE y las clases del PSI de Kotlin ---
//        FileTypeIndex.getFiles(KotlinFileType.INSTANCE, scope).forEach { vf: VirtualFile ->
//            (psiManager.findFile(vf) as? KtFile)?.let { ktFile ->
//                ktFile.declarations.filterIsInstance<KtClassOrObject>().forEach { ktClass ->
//                    val name = ktClass.name ?: return@forEach
//
//                    // --- MEJORA: Distinguir entre superclase e interfaces en Kotlin ---
//                    var superClassName: String? = null
//                    val interfaceNames = mutableListOf<String>()
//
//                    ktClass.superTypeListEntries.forEach { superTypeEntry ->
//                        // Una superclase generalmente se invoca con un constructor (p. ej., "ClaseBase()")
//                        if (superTypeEntry is KtSuperTypeCallEntry) {
//                            superClassName = superTypeEntry.typeReference?.text
//                        } else {
//                            // Las interfaces simplemente se listan por su tipo
//                            superTypeEntry.typeReference?.text?.let { interfaceNames.add(it) }
//                        }
//                    }
//                    result.add(buildRelationString(name, superClassName, interfaceNames))
//                }
//            }
//        }
//
//        return result
//    }
//
//    private fun buildRelationString(className: String, superClass: String?, interfaces: List<String>): String {
//        return buildString {
//            append("Clase: $className")
//            if (!superClass.isNullOrBlank()) {
//                append(" → hereda de $superClass")
//            }
//            if (interfaces.isNotEmpty()) {
//                append(" → implementa ${interfaces.joinToString(", ")}")
//            }
//        }
//    }
//}