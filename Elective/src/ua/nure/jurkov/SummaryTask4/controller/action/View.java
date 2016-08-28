package ua.nure.jurkov.SummaryTask4.controller.action;

/**
 * Class consist of name of view(jsp) and type dispatch(forward, send_redirect).
 * 
 * @author Eugene Jurkov
 *
 */
public class View {
	private String nameView;
	private TypeDispatch typeDispatch;
	
	public static enum TypeDispatch{
		FORWARD, SEND_REDIRECT
	}
	
	public View(){};
	
	public View(String nameView, TypeDispatch typeDispatch){
		this.nameView = nameView;
		this.typeDispatch = typeDispatch;
	}
	
	public String getNameView() {
		return nameView;
	}
	
	public void setNameView(String nameView) {
		this.nameView = nameView;
	}
	
	public TypeDispatch getTypeDispatch() {
		return typeDispatch;
	}
	
	public void setTypeDispatch(TypeDispatch type) {
		this.typeDispatch = type;
	}
	
}
