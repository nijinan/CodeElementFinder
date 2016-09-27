package cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity;


public class JavaEnumClassInfo extends JavaClassInfo {
	public JavaEnumClassInfo(String name, JavaPackageInfo javaPackage, JavaTypeInfo outerClass) {
		super(name, javaPackage, outerClass);
	}

	@Override
	public String toString() {
		return String.format(
			"[%s] %s.%s",CodeType.JavaEnumClass, getJavaPackage().getFullyQualifiedName(), getNameWithOuterType()
		);
	}

}
