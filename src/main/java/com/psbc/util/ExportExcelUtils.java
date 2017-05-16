package com.psbc.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExportExcelUtils<T> {
    private static Logger logger = Logger.getLogger(ExportExcelUtils.class);

    public void exportExcel(String title, String[] headers, String[] fields,
                            Collection<T> dataset, OutputStream out) {
        // 声明�?个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成�?个表�?  
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字�?          
        sheet.setDefaultColumnWidth(15);
        // 生成表头样式  
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成表头字体  
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样�?  
        style.setFont(font);
        // 生成并设置数据样�?  
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成另一个字�?  
        HSSFFont font2 = workbook.createFont();
        font2.setFontName("微软雅黑");
        font2.setColor(HSSFColor.BLACK.index);
        font2.setFontHeightInPoints((short) 12);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样�?  
        style2.setFont(font2);

        // 产生表格标题�?  
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属�?�的先后顺序，动态调用getXxx()方法得到属�?��??  
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = fields[i];
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Class<?> tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName);
                    Object value = getMethod.invoke(t);
                    if (value != null) {
                        String textValue = value.toString();
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    } else {
                        cell.setCellValue("");
                    }
                } catch (SecurityException e) {
                    logger.info("contexte", e);
                } catch (NoSuchMethodException e) {
                    logger.info("contexte", e);
                } catch (IllegalArgumentException e) {
                    logger.info("contexte", e);
                } catch (IllegalAccessException e) {
                    logger.info("contexte", e);
                } catch (InvocationTargetException e) {
                    logger.info("contexte", e);
                }
            }
        }
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.info("contexte", e);
        }
    }
}
