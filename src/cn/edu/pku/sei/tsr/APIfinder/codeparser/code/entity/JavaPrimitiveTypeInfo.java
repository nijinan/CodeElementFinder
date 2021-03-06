package cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity;

import org.eclipse.jdt.core.dom.PrimitiveType;

import java.util.HashMap;
import java.util.Map;

public class JavaPrimitiveTypeInfo extends JavaTypeInfo {
	private static final JavaPrimitiveTypeInfo VOID_TYPE = new JavaPrimitiveTypeInfo("void");
	private static final JavaPrimitiveTypeInfo BOOLEAN_TYPE = new JavaPrimitiveTypeInfo("boolean");
	private static final JavaPrimitiveTypeInfo CHAR_TYPE = new JavaPrimitiveTypeInfo("char");
	private static final JavaPrimitiveTypeInfo BYTE_TYPE = new JavaPrimitiveTypeInfo("byte");
	private static final JavaPrimitiveTypeInfo SHORT_TYPE = new JavaPrimitiveTypeInfo("short");
	private static final JavaPrimitiveTypeInfo INT_TYPE = new JavaPrimitiveTypeInfo("int");
	private static final JavaPrimitiveTypeInfo LONG_TYPE = new JavaPrimitiveTypeInfo("long");
	private static final JavaPrimitiveTypeInfo FLOAT_TYPE = new JavaPrimitiveTypeInfo("float");
	private static final JavaPrimitiveTypeInfo DOUBLE_TYPE = new JavaPrimitiveTypeInfo("double");

	private static final long serialVersionUID = 3794012485697605313L;
	public static final Map<PrimitiveType.Code, JavaPrimitiveTypeInfo> PRIMITIVE_TYPES = new HashMap<>();
	public static final Map<String, JavaPrimitiveTypeInfo> PRIMITIVE_TYPES_STRING = new HashMap<>();	

	static {
		PRIMITIVE_TYPES.put(PrimitiveType.VOID, VOID_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.BOOLEAN, BOOLEAN_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.CHAR, CHAR_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.BYTE, BYTE_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.SHORT, SHORT_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.INT, INT_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.LONG, LONG_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.FLOAT, FLOAT_TYPE);
		PRIMITIVE_TYPES.put(PrimitiveType.DOUBLE, DOUBLE_TYPE);
		PRIMITIVE_TYPES_STRING.put("void", VOID_TYPE);
		PRIMITIVE_TYPES_STRING.put("boolean", BOOLEAN_TYPE);
		PRIMITIVE_TYPES_STRING.put("char", CHAR_TYPE);
		PRIMITIVE_TYPES_STRING.put("byte", BYTE_TYPE);
		PRIMITIVE_TYPES_STRING.put("short", SHORT_TYPE);
		PRIMITIVE_TYPES_STRING.put("int", INT_TYPE);
		PRIMITIVE_TYPES_STRING.put("long", LONG_TYPE);
		PRIMITIVE_TYPES_STRING.put("float", FLOAT_TYPE);
		PRIMITIVE_TYPES_STRING.put("double", DOUBLE_TYPE);
	}

	private JavaPrimitiveTypeInfo(String name) {
		super(name, null, null);
	}

	public String getFullyQualifiedName() {
		return String.format("%s", getName());
	}
}
