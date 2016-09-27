package cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity;


import cn.edu.pku.sei.tsr.APIfinder.codeparser.visitor.projectvisitor.JavaProjectVisitor;

public class JavaInterfaceInfo extends JavaTypeInfo {
	public JavaInterfaceInfo(String name, JavaPackageInfo javaPackage, JavaTypeInfo outerType) {
		super(name, javaPackage, outerType);
	}

	@Override
	public String toString() {
		return String.format("[%s] %s",CodeType.JavaInterface, getFullyQualifiedName());
	}

	public <R, A> void accept(JavaProjectVisitor<R, A> visitor, A arg) {
		visitor.visit(this, arg);
		super.accept(visitor, arg);
	}
}
