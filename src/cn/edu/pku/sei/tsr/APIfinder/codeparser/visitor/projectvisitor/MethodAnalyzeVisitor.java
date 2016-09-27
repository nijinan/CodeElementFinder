package cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.projectvisitor;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.*;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor.FieldChangeVisitor;

import org.eclipse.jdt.core.dom.Block;

public class MethodAnalyzeVisitor extends JavaProjectVisitor<Object, Object> {
	@Override
	public Object visit(JavaBaseInfo javaBaseInfo, Object arg) {
		return null;
	}

	@Override
	public Object visit(JavaProjectInfo javaProject, Object arg) {
		return null;
	}

	@Override
	public Object visit(JavaPackageInfo javaPackage, Object arg) {
		return null;
	}

	@Override
	public Object visit(JavaClassInfo javaClassInfo, Object arg) {
		return null;
	}

	@Override
	public Object visit(JavaInterfaceInfo javaInterface, Object arg) {
		return null;
	}

	@Override
	public Object visit(JavaMethodInfo javaMethod, Object arg) {
		Block body = javaMethod.getBody();
		if (body != null) body.accept(new FieldChangeVisitor(javaMethod));
		return null;
	}

	@Override
	public Object visit(JavaVariableInfo javaVariable, Object arg) {
		return null;
	}
}
