package cn.edu.pku.sei.tsr.APIfinder.codeparser.code;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaClassInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaEnumClassInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaInterfaceInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaMethodInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaPackageInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaPrimitiveTypeInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaProjectInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaTypeInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaVariableInfo;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor.ClassBuiltVisitor;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor.JavaASTVisitor;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor.SymbolBuiltVisitor;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.projectvisitor.MethodAnalyzeVisitor;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;

public class CodeParserByByte {
	static ClassPool cp;
	static List<ClassFile> classes;
	public static JavaProjectInfo parse(String projectName, String projectPath) {
		JavaProjectInfo project = new JavaProjectInfo(projectName);
		parseDir(project, new File(projectPath));
		parsePool(project);
		parseMethod(project);
		//project.accept(new MethodAnalyzeVisitor(), null);
		return project;
	}

	private static CtClass getct(String name){
		CtClass ct = null;
		try {
			ct = cp.getCtClass(name);
		} catch (NotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return ct;
	}
	
	private static void parsePool(JavaProjectInfo project){
		for (ClassFile cf : classes){
			CtClass ctClass = getct(cf.getName());
			JavaTypeInfo type = null;
			if (!ctClass.isInterface()){
				JavaClassInfo clazz;
				JavaPackageInfo pack = project.getPackage(ctClass.getPackageName(),true);
				if (ctClass.isEnum()){
					clazz = new JavaEnumClassInfo(ctClass.getSimpleName(), pack, null);
				}else clazz = new JavaClassInfo(ctClass.getSimpleName(), pack, null);
				pack.addClass(clazz);
				project.addClass(clazz);
				type = clazz;
			}else{
				JavaInterfaceInfo clazz;
				JavaPackageInfo pack = project.getPackage(ctClass.getPackageName(),true);
				clazz = new JavaInterfaceInfo(ctClass.getSimpleName(),pack,null);
				pack.addInterface(clazz);
				project.addInterface(clazz);
				type = clazz;
			}
		}
		
	}
	
	private static JavaTypeInfo getType(JavaProjectInfo project, CtClass ctClass){
		JavaTypeInfo type = null;
		if (!ctClass.isInterface()){
			JavaClassInfo clazz;
			JavaPackageInfo pack = project.getPackage(ctClass.getPackageName(),true);
			if (pack == null) return null;
			clazz = pack.getClass(ctClass.getSimpleName());
			type = clazz;
		}else{
			JavaInterfaceInfo clazz;
			JavaPackageInfo pack = project.getPackage(ctClass.getPackageName(),true);
			if (pack == null) return null;
			clazz = pack.getInterface(ctClass.getSimpleName());
			type = clazz;
		}
		return type;
	}
	
	private static void parseMethod(JavaProjectInfo project){
		for (ClassFile cf : classes){
			CtClass ctClass = getct(cf.getName());
			JavaTypeInfo type = null;
			type = getType(project,ctClass);
			if (type == null) continue;
			for (CtMethod ctMethod : ctClass.getDeclaredMethods()){
				JavaMethodInfo method = new JavaMethodInfo(ctMethod.getName(),type,null,null); 
				type.addMethod(method);
				try {
					for (CtClass arg : ctMethod.getParameterTypes()){
						if (arg.isPrimitive()) {
							method.addArg(new JavaVariableInfo("",JavaPrimitiveTypeInfo.PRIMITIVE_TYPES_STRING.get(arg.getName())));
						}else{
							JavaTypeInfo argtype = new JavaTypeInfo(arg.getName(),null,null);
							method.addArg(new JavaVariableInfo("", argtype));
						}
					}
				} catch (NotFoundException e) {
					//e.printStackTrace();
				}
				
				
			}
		}
	}
	
	private static void parseDir(JavaProjectInfo project, File dir) {
		cp = ClassPool.getDefault();
		classes = new ArrayList<ClassFile>();

		String[] extensions = { "class" };
		Collection<File> files = FileUtils.listFiles(dir, extensions, true);

		for (File file : files)
			try
			{
				ClassFile cf = new ClassFile(new DataInputStream(new FileInputStream(file))); 
				classes.add(cf);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for (File file : files)
			try
			{
				cp.makeClass(new FileInputStream(file));
			}
			catch (IOException | RuntimeException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	public static void main(String[] args) {
//		parse("lucene", "D:\\Dragon Project\\subjects\\lucene-5.2.1\\core\\src\\java").printAllClassesAndInterfaces();
		JavaProjectInfo project = parse("lucene", "D:\\Codes\\lucene-core-5.2.1");
//		JavaProject project = parse("gson", "/home/woooking/java/projects/gson/gson/src/main/java");
//		project.accept(new OutputVisitor(), null);
//		List<JavaMethod> methods = new ArrayList<>();
//		project.accept(new MethodCollectVisitor(), methods);
//		List<JavaVariable> fields = new ArrayList<>();
//		project.accept(new FieldCollectVisitor(), fields);
//		parse("jena", "/home/woooking/java/projects/apache-jena-3.0.0/javadoc-core").printAllClassesAndInterfaces();
//		JavaProject project = parse("codeparser", "/home/woooking/java/CodeParser/src");
//		System.out.println(project);
//		String jdkPath = "/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/";
//		String classPath = "bin" +
//			":" + "/home/woooking/java/projects/lucene-5.2.1/core/src/java" +
//			":" + jdkPath + "rt.jar" +
//			":" + jdkPath + "jce.jar" +
//			":" + jdkPath + "jsse.jar";
//
//		PackManager.v().getPack("wjtp").add(new Transform("wjtp.showclasses", new SceneTransformer() {
//			@Override
//			protected void internalTransform(String s, Map map) {
//				for (SootClass sootClass : Scene.v().getApplicationClasses()) {
//					System.out.println(sootClass);
//				}
//			}
//		}));
//
//		soot.Main.main(new String[]{
//			"-app",
//			"-allow-phantom-refs",
//			"-soot-class-path", classPath,
//			"-process-dir", "/home/woooking/java/projects/lucene-5.2.1/core/src/java"
//		});
	}

}

