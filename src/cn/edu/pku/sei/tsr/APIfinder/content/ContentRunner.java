package cn.edu.pku.sei.tsr.APIfinder.content;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.CodeParser;
import cn.edu.pku.sei.tsr.APIfinder.codeparser.code.entity.JavaProjectInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.CodeLikeTermInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ParagraphInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.SentenceInfo;


public class ContentRunner implements Runnable{
	private static List<File> contentFiles = new ArrayList<File>();
	private static List<ContentInfo> contentList = new ArrayList<ContentInfo>();
	private static int readIndex = 0;
	private static JavaProjectInfo project = CodeParser.parse("lucene", "D:\\Codes\\lucene-5.2.1");
	@Override
	public void run() {
		File nextContentFile;
		while ((nextContentFile = getNextContentFile()) != null) {
			Scanner sc;
			String sent = "";
			try {
				sc = new Scanner(nextContentFile);
				while (sc.hasNext()) {
					sent = sent + sc.nextLine() + "\n";
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			ContentInfo content = new ContentInfo(sent);
			contentList.add(content);
			content.setHTMLContent(false);
			ContentParser.parseContent(content);
			for (ParagraphInfo paragraph : content.getParagraphList()){
				if (paragraph.isCodeFragment()) continue;
				for (SentenceInfo sentence : paragraph.getSentences()){
					ContentParser.replaceCodeLikeTerms(sentence);
				}
			}
			CodeTermsParser parser = new CodeTermsParser(project);
			parser.parseRelativeCodeTerms(content);
			for (CodeLikeTermInfo cc : content.getCodeLikeTerms()){
				System.out.println(cc.getContent() + " : " + cc.getRelevantCodeElementFQN());
			}
		}
	}
	
	private static void addContent(ContentInfo content){
		contentList.add(content);
	}
	
	private static File getNextContentFile() {
		if (contentFiles == null)
			return null;
		File nextContentFile = null;
		try {
			if (readIndex >= contentFiles.size())
				return null;

			nextContentFile = contentFiles.get(readIndex);
			readIndex++;
		}
		catch (Exception e) {
			// if (!(e instanceof IndexOutOfBoundsException))
			e.printStackTrace();
		}
		return nextContentFile;
	}
	
	public static void main(String[] args) {

//		File existedFileDir = new File("D:\\codes\\APIfinder\\testdata\\exist\\");
//		if (existedFileDir.exists() && existedFileDir.listFiles() != null) {
//			File[] existedSubDirs = existedFileDir.listFiles();
//			for (File file : existedSubDirs) {
//				if (file.isDirectory()) {
//					File[] existedLibFiles = file.listFiles();
//					for (File existedFile : existedLibFiles) {
//						existedFileNames.add(existedFile.getName());
//					}
//				}
//			}
//		}

		File dataObjDirectory = new File("D:\\Codes\\APIfinder\\testdata\\txt");

		File[] subdirs = dataObjDirectory.listFiles();
		for (File file : subdirs) {
			if (file.isDirectory()) {
				File[] libContents = file.listFiles();
				for (File content : libContents) {
					contentFiles.add(content);
				}
			}else contentFiles.add(file);
		}
		System.out.println("asd");

		for (int i = 0; i < 4; i++) {
			ContentRunner contentRunner = new ContentRunner();// 从pool目录解析已有的content
			Thread thread = new Thread(contentRunner, "content-" + i);
			thread.start();
		}
		while (contentList.size() < 2){
			System.out.println(contentList.size());
		}
	}


}
