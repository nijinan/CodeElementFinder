package cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity;

public enum CodeType {
    JavaClass("JavaClass"),
    JavaInterface("JavaInterface"),
    JavaMethod("JavaMethod"),
    JavaEnumClass("JavaEnumClass"),
    JavaPackage("JavaPackage");
    private String name;
    CodeType(String name){
    	this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }    
}
