package cn.edu.pku.sei.tsr.APIfinder.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.CodeParser;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.CodeParserByByte;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaProjectInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.CodeTermsParser;
import cn.edu.pku.sei.tsr.APIfinder.content.ContentParser;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ParagraphInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.SentenceInfo;

public class StringParser {
	private static JavaProjectInfo project;
	public static void codeParse(String projectName, String codeDir){
		project = CodeParser.parse(projectName, codeDir);
	}
	public static void codeParseByByte(String projectName, String codeDir){
		project = CodeParserByByte.parse(projectName, codeDir);
	}
	public static ContentInfo contentParse(String test, boolean isHtml){
		//content.setHTMLContent(isHtml);
		if (isHtml){
			test = test.replaceAll("<[^<>]*>", " ");
		}
		ContentInfo content = new ContentInfo(test);
		ContentParser.parseContent(content);
		for (ParagraphInfo paragraph : content.getParagraphList()){
			if (paragraph.isCodeFragment()) continue;
			for (SentenceInfo sentence : paragraph.getSentences()){
				ContentParser.replaceCodeLikeTerms(sentence);
			}
		}

		CodeTermsParser parser = new CodeTermsParser(project);
		parser.parseRelativeCodeTerms(content);
		return content;
	}
	public static ContentInfo contentParse(File file, boolean isHtml){
		Scanner sc;
		String sent = "";
		try {
			sc = new Scanner(file);
			while (sc.hasNext()) {
				sent = sent + sc.nextLine() + "\n";
			}					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return contentParse(sent, isHtml);
	}
	public static void main(String args[]){
		StringParser.codeParseByByte("Lu",  "D:\\Codes\\lucene");
		ContentInfo content = StringParser.contentParse(new File("testdata\\txt\\a.txt"),false);
		System.out.println(ContentUtil.parse2String(content));
		List<Pair<String, String>> list = ContentUtil.parse2Pair(content);
		for (Pair<String, String> p : list){
			System.out.println(p.getLeft() + " " + p.getRight());
		}
	}
}
