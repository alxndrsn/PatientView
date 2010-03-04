package net.frontlinesms.plugins.patientview.ui.helpers.thinletformfields;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.frontlinesms.plugins.patientview.ui.dialogs.DateSelectorDialog;
import net.frontlinesms.ui.ExtendedThinlet;

public class DateField extends TextBox {

	protected DateSelectorDialog ds;
	protected DateFormat df;
	Object btn;
	public static final String NAME = "dateField";

	public DateField(ExtendedThinlet thinlet, String label) {
		super(thinlet, label, NAME);
		btn = thinlet.create("button");
		thinlet.setIcon(btn, "/icons/date.png");
		thinlet.setAction(btn, "showDateSelector()", null, this);
		thinlet.add(mainPanel, btn);
		thinlet.setInteger(mainPanel, "columns", 3);
		ds = new DateSelectorDialog(thinlet, textBox);
		df =  DateFormat.getDateInstance(DateFormat.SHORT);
		thinlet.setAttachedObject(mainPanel, this);
	}

	protected DateField(ExtendedThinlet thinlet, String label, String name) {
		super(thinlet, label, name);
		btn = thinlet.create("button");
		thinlet.setIcon(btn, "/icons/date.png");
		thinlet.setAction(btn, "showDateSelector()", null, this);
		thinlet.add(mainPanel, btn);
		thinlet.setInteger(mainPanel, "columns", 3);
		df =  DateFormat.getDateInstance(DateFormat.SHORT);
		ds = new DateSelectorDialog(thinlet, textBox);
	}

	public Date getDateResponse() {
		try {
			return df.parse(getResponse());
		} catch (ParseException e) {
			return null;
		}
	}

	public void showDateSelector() {
		try {
			ds.showSelecter();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void setRawResponse(Date d){
		setResponse(df.format(d));
	}
	
	public void setDateButtonEnabled(boolean value){
		thinlet.setEnabled(btn, value);
	}

	public boolean isValid() {
		try {
			Date date = df.parse(this.getResponse());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
