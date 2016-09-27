package cn.edu.pku.sei.tsr.APIfinder.utils;

import java.io.File;
import java.util.List;

public class Utils {

	public static String getLineSeparator() {
		return System.getProperty("line.separator");
	}

	public static String wordsConjuction(List<String> words) {
		StringBuilder conj = new StringBuilder();
		for (int i = 0; i < words.size(); i++) {
			String keyword = words.get(i);
			if (i > 0)
				conj.append("|");
			conj.append(keyword);
		}
		return conj.toString();
	}

	public static String wordsConjuction(String words[]) {
		StringBuilder conj = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String keyword = words[i];
			if (i > 0)
				conj.append("|");
			conj.append(keyword);
		}
		return conj.toString();
	}

	public static void main(String args[]) {
		System.out.println(getLineSeparator());
		System.out.println(File.separator);
		// Tree tree = StanfordParser
		// .parseTree("I've (call) <him> {cute} \"boy\", that cannot be terrible whole/parts; the Smiths' invited us to go, wasn't [a] 'you'?!!!...");
		// tree.pennPrint();
		// System.out.println(TreeUtils.interpretTreeToString(tree));
	}

}
