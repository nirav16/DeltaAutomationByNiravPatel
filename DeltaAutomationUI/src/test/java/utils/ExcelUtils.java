package utils;

        import java.io.File;
import java.io.FileInputStream;
		import java.io.FileNotFoundException;
		import java.io.FileOutputStream;
		import java.io.IOException;
		import org.apache.poi.xssf.usermodel.XSSFCell;
		import org.apache.poi.xssf.usermodel.XSSFRow;
		import org.apache.poi.xssf.usermodel.XSSFSheet;
		import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    public class ExcelUtils {

			private static XSSFSheet ExcelWSheet;
			private static XSSFWorkbook ExcelWBook;
			private static XSSFCell Cell;
			private static XSSFRow Row;

		public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

		   String[][] tabArray = null;
		   try {
			   File files = new File(FilePath);
			   String propertyFilePath = files.getAbsolutePath();
			   String filepath = propertyFilePath.replaceAll("DataExcel.xlsx", "");
			   String finalPropertypath = (filepath + "\\src\\test\\java\\com\\DataProvider\\DataExcel.xlsx") ;
			   System.out.println("the sheetname recieved is " +SheetName);
			   FileInputStream ExcelFile = new FileInputStream(finalPropertypath);
			   // Access the required test data sheet
			   ExcelWBook = new XSSFWorkbook(ExcelFile);
			   ExcelWSheet = ExcelWBook.getSheet(SheetName);
			   
			    int startRow = 0;
			   int startCol = 0;
			   int ci,cj;
			   int totalRows = ExcelWSheet.getLastRowNum();
			   System.out.println("total row are " +totalRows);
			   // you can write a function as well to get Column count
			   int totalCols = 1;
			   tabArray=new String[totalRows +1][totalCols];
			   ci=0;
			   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
				  cj=0;
				   for (int j=startCol;j<totalCols;j++, cj++){
					   System.out.println("the value of i is " +i);
					   System.out.println("the value of j is " +j);
					   tabArray[ci][cj]=getCellData(i,j);
					   System.out.println(tabArray[ci][cj]);  
						}
					}
				}

			catch (FileNotFoundException e){

				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
				}

			catch (IOException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
				}
			return(tabArray);
			}

		public static String getCellData(int RowNum, int ColNum) throws Exception {
			try{
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				/*int dataType = Cell.getCellType();
				if  (dataType == 3) {
					return "";
				}else{*/
					String CellData = Cell.getStringCellValue();
					return CellData;

				//}
			}catch (Exception e){
				System.out.println(e.getMessage());
				throw (e);
				}
			}
	}
    