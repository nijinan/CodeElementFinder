package cn.edu.pku.sei.tsr.APIfinder.utils;

import cn.edu.pku.sei.tsr.APIfinder.content.entity.CodeLikeTermInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ContentInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.ParagraphInfo;
import cn.edu.pku.sei.tsr.APIfinder.content.entity.SentenceInfo;

public class Content2String {
	private static String textFromTerm(CodeLikeTermInfo term){
		String ret = "";
		if (term.isLeaf()) ret += term.getRelevantCodeElementFQN() ;
		for (CodeLikeTermInfo subterm : term.getCodeLikeTermList()){
			ret += " [" + subterm.getContent() + " : " + textFromTerm(subterm) + "] ";
		}
		return ret;
	} 
	public static String parse(ContentInfo content){
		String ret = "";
		for (ParagraphInfo para : content.getParagraphList()){
			for (SentenceInfo sent : para.getSentences()){
				int cnt = 0;
				String senttext = sent.getContent();
				for (CodeLikeTermInfo code : sent.getCodeLikeTerms()){
					String replacement = " <<<< " + code.getContent() + " : ";
					replacement += textFromTerm(code);
					replacement += " >>>> ";
					senttext = senttext.replace("CODE"+cnt, replacement);
					cnt ++;
				}
				ret += senttext + " ";
			}
			ret += "\n";
		}
		return ret;
	}
}
