package org.leve.web.ui.tags;

@SuppressWarnings("serial")
public class FormColDefTag extends LeveBaseTag {

	private int cols = 2;

	@Override
	protected String writeBeforeBody() {
		return NULL_RETURN;
	}

	@Override
	protected String writeAfterBody() {
		return NULL_RETURN;
	}
	
	@Override
	public void release() {
		super.release();
		this.cols = 2;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.cols = 2;
	}
	
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

}
