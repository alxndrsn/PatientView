package net.frontlinesms.plugins.medic.ui.helpers.thinletformfields;

import net.frontlinesms.plugins.medic.data.domain.people.Person.Gender;
import net.frontlinesms.ui.ExtendedThinlet;

public class GenderComboBox extends ThinletFormField<Gender>{

	protected Object comboBox;
	protected boolean hasChanged;
	public static final String NAME = "genderComboBox";
	
	public GenderComboBox(ExtendedThinlet thinlet, Gender gender) {
		super(thinlet, "Gender:",NAME);
		hasChanged = false;
		comboBox = thinlet.create("combobox");
		thinlet.add(comboBox,thinlet.createComboboxChoice("Male", Gender.MALE));
		thinlet.add(comboBox,thinlet.createComboboxChoice("Female", Gender.FEMALE));
		thinlet.add(comboBox,thinlet.createComboboxChoice("Trans-gender", Gender.TRANSGENDER));
		thinlet.setAction(comboBox, "selectionChanged(this.selected)", null, this);
		thinlet.add(mainPanel,comboBox);
		thinlet.setInteger(comboBox, "weightx", 5);
		//initialize the comboBox
		setRawResponse(gender);
		thinlet.setAttachedObject(mainPanel, this);
	}

	public void selectionChanged(int index){
		if(index >=0){
			hasChanged = true;
		}
	}

	@Override
	public boolean isValid() {
		return hasResponse();
	}
	
	public boolean hasChanged(){		
		return hasChanged;
	}
	
	@Override
	public Gender getRawResponse() {
		return (Gender) thinlet.getAttachedObject(thinlet.getSelectedItem(comboBox));
	}
	
	@Override
	public void setRawResponse(Gender s) {
		thinlet.setText(comboBox, Gender.getGenderName(s));
		thinlet.setSelectedIndex(comboBox, s == Gender.MALE? 0: s == Gender.FEMALE? 1:2);
	}
	
	@Override
	public String getResponse() {
		return Gender.getGenderName(getRawResponse());
	}

	@Override
	public void setResponse(String response) {
		if(Gender.getGenderForName(response) != null)
			setRawResponse(Gender.getGenderForName(response));
	}
}
