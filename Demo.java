import java.io.File;

import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.utils.Content2String;
import cn.edu.pku.sei.tsr.APIfinder.utils.StringParser;


public class Test {
	public static void main(String args[]){
		String codePath =  "D:\\Codes\\lucene-5.2.1";
		String projectName = "Lucene";
		StringParser.codeParse(projectName,  codePath);
		String filePath = "a.txt";
		File contentFile = new File(filePath);
		ContentInfo content = StringParser.contentParse(contentFile);
		String outStr = Content2String.parse(content);
		System.out.println(outStr);
//		content = StringParser.contentParse(new File("a.txt"));
//		System.out.println(Content2String.parse(content));		
	}
}
