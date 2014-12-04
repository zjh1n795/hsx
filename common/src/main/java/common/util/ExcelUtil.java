package common.util;
import java.io.OutputStream;     
import java.lang.reflect.Field;     
import java.lang.reflect.Method;     
import java.util.List;     
          
import org.apache.poi.hssf.usermodel.HSSFCell;     
import org.apache.poi.hssf.usermodel.HSSFCellStyle;     
import org.apache.poi.hssf.usermodel.HSSFRow;     
import org.apache.poi.hssf.usermodel.HSSFSheet;     
import org.apache.poi.hssf.usermodel.HSSFWorkbook;     
          
public class ExcelUtil {     
          
    /**     
     * 导出xls文件，考虑到一般只是导出某种对象信息，所以使用泛型去解析数据，只用传入需要导出的字段及表头信息，以及数据，所以这样可以完全地专注于业务，     
     * 同时有可能对于某些字段可能要设置一些默认值     
     * ，这样当数据为null时，就可以设置，还有一个问题就是有些字段可能需要进行一些转换，比如性别用0和1表示，这样导出的时候就可以显示为男和女。     
     *      
     * @param output     
     *            导出的数据流，有可能是文件流，可能是网络数据流     
     * @param workSheetName     
     *            工作表名称，涉及到一个表只能有65536行，256列，所以，当数据量较大时会直接写入下一个表。列超出则直接抛出异常。     
     * @param titles     
     *            表头信息     
     * @param property     
     *            对象属性，对对象的哪些属性进行导出     
     * @param data     
     *            数据集合     
     * @param defaultValue     
     *            设置默认值     
     * @param clazz     
     *            进行类型转换的类，需要注意的是方法必须使用get+属性名（首字母大写）。参数为属性类型。如username为String类型     
     *            ，则方法为public String getUsername(String     
     *            username),返回值原则上可以为任何类型，返回以后直接进行String     
     *            .valuOf()操作。如果使用转换类，则所有字段必须全部包含，不需要转换的字段直接返回。     
     *            考虑到一般情况下可能输出字段较多，进行转换的字段不是很多，所以提供该类的生成工具类     
     *            {@link com.gsww.jup.util.BornParser},详情及使用请参看     
     *            {@link com.gsww.jup.util.BornParser}     
     */
    public void export(OutputStream output, String workSheetName, String[] titles, String[] property, List<?> data, String defaultValue, Class<?> clazz) {     
          
        // 声明一个转换对象     
        Object parser = null;     
          
        // 如果传入的转换类型为null，则说明不用转换的，所有字段直接输出就可以了。     
        if (clazz != null) {     
          
            try {     
          
                // 实例化转换对象     
                parser = clazz.newInstance();     
          
            } catch (InstantiationException e) {     
          
                e.printStackTrace();     
          
            } catch (IllegalAccessException e) {     
          
                e.printStackTrace();     
          
            }     
          
        }     
          
        // 每个sheet中的总显示数（最大行数）     
        int sheetMaxRowCount = 65534;     
          
        // 每个sheet的列数     
        int columNumber = titles.length;     
          
        try {     
          
            // 创建工作薄     
            HSSFWorkbook wb = new HSSFWorkbook();     
          
            HSSFCellStyle setBorder = wb.createCellStyle();     
          
            // sheet索引     
            int sheetNum = 1;     
          
            // 行     
            int rowNum = 0;     
          
            // 声明工作表     
            HSSFSheet sheet = null;     
          
            for (Object bean : data) {     
          
                // 此处加1.00，用于强制转换类型留下一行用于表头信息，然后肯定是要向上进位了     
                Double sheetIndex = Math.ceil((rowNum + 1.00) / sheetMaxRowCount);     
          
                // 返回的Double，当然要取int类型了     
                sheetNum = sheetIndex.intValue();     
          
                HSSFRow rowData = null;// 表内容k     
          
                // 如果行数刚好是最大行数的整数倍，就应该加一个工作表了     
                if (rowNum == sheetMaxRowCount * (sheetNum - 1)) {     
          
                    // sheet表名依次为表0、表1、表2...     
                    sheet = wb.createSheet(workSheetName + sheetNum);     
          
                    // 创建行     
                    rowData = sheet.createRow(0);     
          
                    // 在表头每列 放值     
                    for (int columnIndex = 0; columnIndex < columNumber; columnIndex++) {     
          
                        // 按照表头信息生成每一个表头单元格信息     
                        HSSFCell headerCell = rowData.createCell(columnIndex);     
          
                        // 设置每一列的宽度     
                        sheet.setColumnWidth(columnIndex, 6000);     
          
                        // 居中     
                        setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);     
          
                        // 设置单元格值     
                        headerCell.setCellValue(titles[columnIndex]);     
          
                    }     
          
                }     
          
                // 创建行     
                rowData = sheet.createRow(rowNum - sheetMaxRowCount * (sheetNum - 1) + 1);     
          
                for (int columnIndex = 0; columnIndex < columNumber; columnIndex++) {     
          
                    // 按照表头信息生成每一个表头单元格信息     
                    HSSFCell cellData = rowData.createCell(columnIndex);     
          
                    // 设置每一列的宽度     
                    sheet.setColumnWidth(columnIndex, 6000);     
          
                    // 居中     
                    setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);     
          
                    // 根据传入的需要导出的字段名称取得集合对象中对应字段对象     
                    Field field = bean.getClass().getDeclaredField(property[columnIndex]);     
          
                    // 有可能是private对象，无法直接访问，所以要先设置为可以访问     
                    field.setAccessible(true);     
          
                    // 根据字段对象取得集合中每一个对象的属性值     
                    if (field.get(bean) != null) {     
          
                        // 考虑到有些字段需要进行数据转换，而又不知道转换的方法是哪个类，哪个方法，所以固定转换方法为“get”+属性名（首字母大写）。参数为属性类型。如username为String类型     
                        // ，则方法为public String getUsername(String     
                        // username),返回值原则上可以为任何类型，返回以后直接进行String.valuOf()操作
                    	Method method =null;
                    	try{
                         method = clazz.getDeclaredMethod("get" + StringUtil.upperCaseFirstChar(field.getName()));//, field.getType());
//                         cellData.setCellValue(String.valueOf(method.invoke(parser, field.get(bean))));
                         cellData.setCellValue(String.valueOf(field.get(bean)));
                    	}catch(Exception e){
                    		System.out.println("【】【】【】"+field.getName());
                    		e.printStackTrace();
                    	}
                        //本想做个判断如果不必要的方法，也就是不用转换的方法可以不写，但却没法去检查。
                    	//所以只能把属性导出的全部方法都 要对应，考虑到无聊，提供了生成工具。
                    	//到时只能改自己需要转换的方法。同时，如果不提供属性对应的方法，直接抛出异常     
                             
          
                    } else {     
          
                        //如果值为null，则有可能会设置为默认值的     
                        cellData.setCellValue(defaultValue);     
          
                    }     
          
                }     
          
                //行数当然要加1了。     
                rowNum++;     
          
            }     
          
            wb.write(output);     
          
            output.flush();     
          
            output.close();     
          
        } catch (Exception e) {     
          
            e.printStackTrace();     
          
        }     
          
    }     
}