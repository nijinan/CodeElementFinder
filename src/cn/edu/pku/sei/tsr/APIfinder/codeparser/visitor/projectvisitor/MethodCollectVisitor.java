package cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.projectvisitor;


import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.*;

import java.util.List;

public class MethodCollectVisitor extends JavaProjectVisitor<Object, List<JavaMethodInfo>> {
	@Override
	public Object visit(JavaBaseInfo javaBaseInfo, List<JavaMethodInfo> arg) {
		return null;
	}

	@Override
	public Object visit(JavaProjectInfo javaProject, List<JavaMethodInfo> arg) {
		return null;
	}

	@Override
	public Object visit(JavaPackageInfo javaPackage, List<JavaMethodInfo> arg) {
		return null;
	}

	@Override
	public Object visit(JavaClassInfo javaClassInfo, List<JavaMethodInfo> arg) {
		arg.addAll(javaClassInfo.getMethods());
		return null;
	}

	@Override
	public Object visit(JavaInterfaceInfo javaInterface, List<JavaMethodInfo> arg) {
		arg.addAll(javaInterface.getMethods());
		return null;
	}

	@Override
	public Object visit(JavaMethodInfo javaMethod, List<JavaMethodInfo> arg) {
		return null;
	}

	@Override
	public Object visit(JavaVariableInfo javaVariable, List<JavaMethodInfo> arg) {
		return null;
	}
}
