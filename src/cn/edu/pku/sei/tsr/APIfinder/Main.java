package cn.edu.pku.sei.tsr.APIfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.CodeType;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.utils.ContentUtil;
import cn.edu.pku.sei.tsr.APIfinder.utils.StringParser;

public class Main {
	public static void main(String args[]){
		String codePath =  "D:\\Codes\\lucene-5.2.1";
		String projectName = "Lucene";
		StringParser.codeParse(projectName,  codePath);
		System.out.println("*********");
		for (int i = 0; i < 1; i++){
			String filePath = "a.txt";
			File contentFile = new File(filePath);
			Scanner sc;
			String sent = "";
			try {
				sc = new Scanner(contentFile);
				while (sc.hasNext()) {
					sent = sent + sc.nextLine() + "\n";
				}					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}			
			sent = sent.replaceAll("<[^<>]*>", " ");
			ContentInfo content = StringParser.contentParse(sent,false);
			//ContentInfo content = StringParser.contentParseByByte(contentFile);
			String outStr = ContentUtil.parse2String(content);
			System.out.println(outStr);
			List<Pair<String, String>> list = ContentUtil.parse2Pair(content);
			for (Pair<String, String> p : list){
				System.out.println(p.getLeft() + " " + p.getRight());
			}
			System.out.println(CodeType.JavaClass);
		}
		//System.out.println(CodeType.JavaMethod);
//		content = StringParser.contentParse(new File("a.txt"));
//		System.out.println(Content2String.parse(content));	
	}
}

