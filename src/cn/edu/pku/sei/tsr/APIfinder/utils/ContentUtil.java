package cn.edu.pku.sei.tsr.APIfinder.utils;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import cn.edu.pku.sei.tsr.APIfinder.content.entity.CodeLikeTermInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ParagraphInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.SentenceInfo;

public class ContentUtil {
	public static String parse2String(ContentInfo content){
		return Content2String.parse(content);
	}
	private static void textFromTerm(CodeLikeTermInfo term,List<Pair<String, String>> list){
		if (term.isLeaf()) {
			if (term.getRelevantCodeElement() != null){
				String name = term.getRelevantCodeElementFQN();
				int pos = name.indexOf("]");
				String s1 = name.substring(1, pos);
				String s2 = name.substring(pos + 2);
				list.add(new ImmutablePair(s1,s2));	
			}
			
		}
		for (CodeLikeTermInfo subterm : term.getCodeLikeTermList()){
			textFromTerm(subterm,list);
		}
	} 
	public static void parse(ContentInfo content,List<Pair<String, String>> list){
		for (ParagraphInfo para : content.getParagraphList()){
			for (SentenceInfo sent : para.getSentences()){
				for (CodeLikeTermInfo code : sent.getCodeLikeTerms()){
					textFromTerm(code,list);
				}
			}
		}
	}
	public static List<Pair<String, String>> parse2Pair(ContentInfo content){
		List<Pair<String, String>> list = new LinkedList<Pair<String,String>>();
		parse(content,list);
		return list;
	}
}
