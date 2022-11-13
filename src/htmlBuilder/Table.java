package htmlBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides methods to create a data set for a tables and to output this tables
 * in various formats like 'csv' or 'html'.
 * 
 * @author Berthold
 *
 */
public class Table {

	private String name;
	private List<String> titleRow;
	private List<String> dataRow;

	private List<List<String>> rows = new ArrayList<List<String>>();

	/**
	 * Creates a new table.
	 * 
	 * @param name
	 *            Tables name.
	 * @param titleRow
	 *            Column titles.
	 */
	public Table(String name, List<String> titleRow) {
		super();
		this.name = name;
		this.titleRow = titleRow;

		this.rows.add(titleRow);
	}

	/**
	 * Adds a new data row to this table.
	 * 
	 * @param d
	 *            A {@link List} of {@link String} objects containing this rows
	 *            data.
	 * 
	 * @return False if the number of columns of the row passed is greater than
	 *         the number of columns of this table. The row wont't be added in
	 *         that case in order to avoid data loss.
	 *         <p>
	 * 
	 *         True if the row has the same number or columns or less than this
	 *         table. Columns are filled from 'right' to 'left'. So, if the
	 *         table has 4 columns and 2 columns are passed, the last two
	 *         columns remain empty.
	 */
	public boolean addDataRow(List<String> d) {
		if (d.size() > this.getColumns())
			return false;
		else
			rows.add(d);
		return true;
	}

	/**
	 * Creates a csv- table.
	 * 
	 * @param divider
	 *            The cell divider (e.g. , ; etc....).
	 * 
	 * @param emptyCellDesignator
	 *            If the number of columns of the row passed is less than the
	 *            number of columns of this table, then the remaining cells will
	 *            be filled with this {@link String}.
	 * 
	 * @return A String containing the csv table.
	 */
	public String createCsv(String divider, String emptyCellDesignator) {

		StringBuilder csv = new StringBuilder();

		Iterator<String> rowData;

		// Get table row
		for (List<String> r : rows) {
			rowData = r.iterator();

			// Get cells and build a new row for this table
			while (rowData.hasNext()) {
				csv.append(rowData.next());

				if (rowData.hasNext())
					csv.append(divider);
			}

			// If # of elements in this row is less than the
			// number of cells in this table, then fill the
			// remaining cells with empty data....
			int numOfMissingCols = this.getColumns() - r.size();
			if (numOfMissingCols > 0) {
				for (int i = 0; i <= numOfMissingCols - 1; i++) {
					csv.append(divider + "Empty");
				}
			}

			// Next row
			csv.append("\n");
		}
		return csv.toString();
	}

	/**
	 * Creates a HTML- formated table from this tables data.
	 * 
	 * @param emptyCellDesignator
	 *            If this table contains rows with less columns then the title
	 *            row, then the remaining cells are filled with the content
	 *            specified by this parameter.
	 *            
	 * @return A {@link String}- object containing the HTML- formated table.
	 */
	public String createHtmlTable(String emptyCellDesignator) {

		StringBuilder html = new StringBuilder();

		// Prepare
		html.append("<!DOCTYPE html>\n");
		html.append("<head>\n");
		html.append(this.htmlTableStyle());
		html.append("</head>\n");
		html.append("<body>\n");
		
		html.append("<table>\n");
		
		Iterator<String> rowData;

		// Get table row
		for (List<String> r : rows) {
			html.append("<tr>\n");

			rowData = r.iterator();

			// Get cells and build a new row for this table
			while (rowData.hasNext()) {
				html.append("<td>" + rowData.next() + "</td>\n");
			}

			// If # of elements in this row is less than the
			// number of cells in this table, then fill the
			// remaining cells with empty data....
			int numOfMissingCols = this.getColumns() - r.size();
			if (numOfMissingCols > 0) {
				for (int i = 0; i <= numOfMissingCols - 1; i++) {
					html.append("<td>" + emptyCellDesignator + "</td>\n");
				}
			}

			// Next row
			html.append("</tr>\n");

		}
		// Terminate table
		html.append("</table>\n");
		html.append("</body>");
		
		return html.toString();
	}

	/**
	 * Sets the table style....
	 * 
	 * @return
	 */
	private String htmlTableStyle() {
		String s = "<style>\n" + "table,th,td{\n" + "border: 1px solid black;\n" + "border-collapse: collapse;\n"
				+ "padding-right: 5px;\n" + "padding-left: 5px;\n" + "padding-top: 5px;\n" + "padding-bottom: 5px;\n"
				+ "text-align: center;\n" + "}\n" + "</style>\n\n";

		return s;
	}

	/**
	 * The number of rows of this table.
	 * 
	 * @return Number of rows
	 */
	public int getRows() {
		return rows.size();
	}

	/**
	 * The number of columns of this table.
	 * <p>
	 * The number of columns is specified by the number of columns of the title
	 * row, innately passed to create the table.
	 * 
	 * @return The number of columns.
	 */
	public int getColumns() {
		return this.titleRow.size();
	}

}
