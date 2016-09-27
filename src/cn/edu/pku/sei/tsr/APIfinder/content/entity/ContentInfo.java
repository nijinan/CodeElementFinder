package cn.edu.pku.sei.tsr.APIfinder.content.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.edu.pku.sei.tsr.APIfinder.utils.UUIDInterface;

public class ContentInfo implements UUIDInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2784454898082557916L;

	private String					uuid;
	private String					content;
	private List<ParagraphInfo>		paragraphList;
	@Deprecated
	private Object					parent			= null;
	@Deprecated
	private transient List<CodeLikeTermInfo>	codeLikeTerms;
	private boolean					isHTMLContent	= false;
	private String					libraryName;
	// private boolean isFinalized = false;

	private String	parentUuid;
	private String	parentClassName;

	public ContentInfo(String _content) {
		setUuid(UUID.randomUUID().toString());
		paragraphList = new ArrayList<>();
		codeLikeTerms = new ArrayList<>();
		this.content = _content;
	}

	public boolean isHTMLContent() {
		return isHTMLContent;
	}

	public void replaceParentByUuid() {
		try {
			if (parent == null)
				return;
			UUIDInterface uuidObj = (UUIDInterface) parent;
			this.parentUuid = uuidObj.getUuid();
			this.parentClassName = parent.getClass().getName();
			this.parent = null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void finalize() throws Throwable {
		try {
			//freeMe();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
		finally {
			super.finalize();
		}
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ParagraphInfo> getParagraphList() {
		return paragraphList;
	}

	public void setParagraphList(List<ParagraphInfo> paragraphList) {
		this.paragraphList = paragraphList;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public List<CodeLikeTermInfo> getCodeLikeTerms() {
		return codeLikeTerms;
	}

	public void setCodeLikeTerms(List<CodeLikeTermInfo> codeTerms) {
		this.codeLikeTerms = codeTerms;
	}

	@Override
	public String toString() {
		return content;
	}

	public void setHTMLContent(boolean isHTMLContent) {
		this.isHTMLContent = isHTMLContent;
	}

	public String getClassOfParent() {
		return parent.getClass().getSimpleName();
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}
	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois) throws Exception {
		ois.defaultReadObject();
	}
}
