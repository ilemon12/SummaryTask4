package ua.nure.jurkov.SummaryTask4.domain.course;

/**
 * Class represent field of edit that will edit.
 * 
 * @author Eugene Jurkov
 *
 */
public class EditField {
	private String nameOfField;
	private String valueOfField;
	
	public EditField(String nameOfField, String valueOfField) {
		this.nameOfField = nameOfField;
		this.valueOfField = valueOfField;
	}
	
	public String getNameOfField() {
		return nameOfField;
	}
	
	public void setNameOfField(String nameOfField) {
		this.nameOfField = nameOfField;
	}

	public String getValueOfField() {
		return valueOfField;
	}

	public void setValueOfField(String valueOfField) {
		this.valueOfField = valueOfField;
	}
	
	public String toString(){
		return "name: " + nameOfField + ", value: " + valueOfField;
	}
}
