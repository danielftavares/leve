package org.leve.web.ui.tags;


@SuppressWarnings("serial")
public class CollumnTag extends FilterTag { 

	private Integer width;
	private boolean filtrable = true;
	
	@Override
	protected String writeBeforeBody() {
		
		StringBuilder sb = new StringBuilder();

		if(this.isFiltrable()){
			sb.append(super.writeBeforeBody());
			
			populateFilterInput(sb);
		}
		
		sb.append("<script type=\"text/javascript\">collumns_"+getFormTag().getId() +".push({property: '"+getAttribute()+"',label: '"+getMessage(resolveLabel())+"',width: "+getWidth()+", filtrable: "+filtrable+"});</script>");
		
		return sb.toString();
	}

	@Override
	protected String writeAfterBody() {
		StringBuilder sb = new StringBuilder();
		if(this.isFiltrable()){
			sb.append(super.writeAfterBody());
		}
		return sb.toString();
	}
	@Override
	public void release() {
		super.release();
		width = null;
		filtrable = true;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		width = null;
		filtrable = true;
	}
	
	private void populateFilterInput(StringBuilder sb) {
		if(!hasCustomFilter()){
			this.getInput().print(sb);
		}
	}

	private boolean hasCustomFilter() {
		for (LeveBaseTag tag : childs) {
			if(tag instanceof FilterTag){
				return true;
			}
		}
		return false;
	}

	protected CollumnTag getCollumnTag(){
		return this;
	}
	
	public boolean isFiltrable() {
		return filtrable;
	}

	public void setFiltrable(boolean filtrable) {
		this.filtrable = filtrable;
	}
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
}
