package cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.astvisitor;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaProjectInfo;

import org.eclipse.jdt.core.dom.ASTVisitor;

public abstract class JavaASTVisitor extends ASTVisitor {
	protected JavaProjectInfo project = null;

	public JavaASTVisitor(JavaProjectInfo project) {
		this.project = project;
	}

	public abstract void reset();
}
