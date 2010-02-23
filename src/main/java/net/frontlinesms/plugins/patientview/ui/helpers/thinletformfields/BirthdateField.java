package net.frontlinesms.plugins.patientview.ui.helpers.thinletformfields;

import java.util.Date;

import net.frontlinesms.ui.ExtendedThinlet;

public class BirthdateField extends DateField{
	
	protected boolean hasChanged;
	public static final String NAME = "birthDatefield";
	
	public BirthdateField(ExtendedThinlet thinlet, Date initialDate) {
		super(thinlet, "Birthdate:", NAME);
		hasChanged = false;		
		if(initialDate != null){
			String initialText = df.format(initialDate);
			thinlet.setText(textBox, initialText);
		}
		thinlet.setAction(textBox, "textChanged(this.text)", null, this);
		thinlet.setAttachedObject(mainPanel, this);
	}
	
	public void textChanged(String text){
		hasChanged = true;
	}
	
	public boolean hasChanged(){
		return hasChanged;
	}
	
	@Override
	public void showDateSelector(){
		hasChanged = true;
		super.showDateSelector();
	}

}