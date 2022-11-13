package demo;

import java.util.ArrayList;
import java.util.List;

import htmlBuilder.Table;

public class MainDemo {
	public static void main(String args[]){
		System.out.println("Demo");
		
		List <String> titleRow=new ArrayList();
		titleRow.add("Volts");
		titleRow.add("R1");
		titleRow.add("R2");
		titleRow.add("R3");
		titleRow.add("R4");
		titleRow.add("Error margin");
	
		Table t=new Table("Divider",titleRow);
		
		List <String> row1=new ArrayList();
		row1.add("1.1");
		row1.add("30Ohm");
		//row1.add("30Ohm");
		//row1.add("30Ohm");
		//row1.add("30Ohm");
		t.addDataRow(row1);
		
		String csv=t.createCsv(";","No Data");	
		System.out.println(csv);
		
		String html=t.createHtmlTable("-");
		System.out.println(html);
		
	}

}
