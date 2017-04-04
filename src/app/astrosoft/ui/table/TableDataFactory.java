/**
 * TableDataFactory.java
 * Created On 2006, Feb 8, 2006 7:42:08 PM
 * @author E. Rajasekar
 * 
 */

package app.astrosoft.ui.table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.astrosoft.consts.AstrosoftTableColumn;
import static java.lang.Enum.valueOf;
import static java.lang.Math.ceil;
import static java.util.logging.Logger.getLogger;

public class TableDataFactory {
	
	private static final Logger log = getLogger(TableDataFactory.class.getName());

	public static <E extends TableRowData> TableData<E> getTableData(List<E> rows){
		return new ListTableData<>(rows);
	}
	
	public static <E extends TableRowData> TableData<E> getTableData(E[] rows){
		return new ListTableData<>(rows);
	}
	
	public static <E extends TableRowData> TableData<E> getSortedTableData(TableData<E> data, Comparator<TableRowData> cmp){
		return new ListTableData<>(data, cmp);
	}
	
	public static <K extends Enum<K>, V extends TableRowData> TableData<V> getTableData(EnumMap<K,V> data, Class <K> enumClass){
		return new EnumMapTableData<>(data, enumClass);
	}
	
	public static <E extends TableRowData> TableData<E> getTableData(TableData<E> t1, TableData<E> t2){
		return new ExtendedTableData<>(t1,t2);
	}
	
	public static <E extends TableRowData> TableData<E> emptyTableData(){
		return new ListTableData<>(java.util.Collections.EMPTY_LIST);
	}
	
	public static <E extends TableRowData> List<ListTableData<E>> splitTableData(ListTableData<E> table, int size){
		List<E> rows = table.getAllRows();
		List<ListTableData<E>> data = new ArrayList<>();
		int count  = (int)ceil(rows.size() / size);
		int index = 0;
		int i = 0;
		for(index = 0; i < count ; index = index + size, i++){
			data.add(new ListTableData<>(rows.subList(index, index + size)));
		}
		if (index < rows.size()){
			data.add(new ListTableData<>(rows.subList(index, rows.size())));
		}
		return data;
	}
	
	public static <E extends TableRowData> TableData<E> getTableData(Iterable<E> rows){
		
		List <E> data = new ArrayList<>();
		for(E row : rows){
			data.add(row);
		}
		return new ListTableData<>(data);
	}
	
	public static <E extends TableRowData>String toCSV(TableData<E> data, ColumnMetaData colData){
	
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < data.getRowCount(); i++){
			
			TableRowData row = data.getRow(i);
			for(AstrosoftTableColumn col : colData.getColumns()){
				sb.append(row.getColumnData(col));
				sb.append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static <E extends TableRowData> Table getReversedTable(TableData<E> data, ColumnMetaData colData, AstrosoftTableColumn colHeader,AstrosoftTableColumn rowHeader ){
		
		
		
		List<AstrosoftTableColumn> newCols = new ArrayList<>();
		newCols.add(rowHeader);
		
		for(int i = 0; i < data.getRowCount(); i++) {
			
			TableRowData rowData = data.getRow(i);
			
			Object val= rowData.getColumnData(colHeader);
				
			try {
				
				if (val instanceof Enum){
					val = valueOf(AstrosoftTableColumn.class, ((Enum)val).name());
				}else{
					val = valueOf(AstrosoftTableColumn.class, val.toString());
				}
			
			}catch(IllegalArgumentException e){
				log.log(Level.SEVERE, "Value of Header column cannot be converted to AstrosoftTableColumn type" , e);
			}
			
			newCols.add((AstrosoftTableColumn)val);
			
		}
		
		final ColumnMetaData newColMetaData = new DefaultColumnMetaData(newCols); 
		MapTableRowHelper rowHelper = new MapTableRowHelper(newColMetaData);
		List<MapTableRow> rows = new ArrayList<>();
		
		List <AstrosoftTableColumn> cols = new ArrayList<>(colData.getVisibleColumns());
		cols.remove(colHeader);
	
		for(AstrosoftTableColumn col:cols){
			
			Object vals[]= new Object[newCols.size()];
			vals[0] = col;
			
			for(int i = 0; i < data.getRowCount(); i++){
				vals[i+1] = ((TableRowData)data.getRow(i)).getColumnData(col);
			}
			
			rows.add(rowHelper.createRow(vals));
		}
		
		final TableData<MapTableRow> reversedTableData = getTableData(rows);
		
		//System.out.println(reversedTableData);
		
		//System.out.println(newColMetaData);
		
		return new Table() {

			public TableData<? extends TableRowData> getTableData() {
				return reversedTableData;
			}

			public ColumnMetaData getColumnMetaData() {
				
				return newColMetaData;
			}
			
		};
	}
}
