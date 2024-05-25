package com.example.RecruitmentService.excel;

import com.example.RecruitmentService.entity.Firm;
import com.example.RecruitmentService.entity.Response;
import com.example.RecruitmentService.entity.Vacancy;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.*;
import java.util.*;

public class Write {
    public static void writeAll() throws IOException {

    }

    public static void writeFirm(List<Firm> firms)  {
        try {

        FileInputStream file = new FileInputStream(
                new File("Рекрутинг.xlsx"));
        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Creating a blank Excel sheet
        XSSFSheet sheet
                = workbook.getSheet("Фирмы");

        // Creating an empty TreeMap of string and Object][]
        // type
        Map<String, Object[]> data
                = new TreeMap<String, Object[]>();


        Integer index=2;
        data.put("1",
                new Object[] { "ID", "Название", "Сфера деятельности", "Год основания", "Веб сайт", "Директор", "Телефон", "Количество вакансий", "Количество рекрутеров"});
        for (Firm firm : firms) {
            data.put(index.toString(), new Object[]{firm.getId_firm(), firm.getTitle(), firm.getField(), firm.getYear(), firm.getWebsite(), firm.getDirector(), firm.getWorkPhone(), firm.getVacancies().size(), firm.getRecruters().size()});
            index++;
        }

        // Iterating over data and writing it to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;

        for (String key : keyset) {

            // Creating a new row in the sheet
            Row row = sheet.createRow(rownum++);

            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr) {

                // This line creates a cell in the next
                // column of that row
                Cell cell = row.createCell(cellnum++);

                if (obj instanceof String)
                    cell.setCellValue((String)obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        // Try block to check for exceptions

            file.close();
            // Writing the workbook
            FileOutputStream out = new FileOutputStream(
                    "Рекрутинг.xlsx");
            workbook.write(out);

            // Closing file output connections
            out.close();

            // Console message for successful execution of
            // program
            System.out.println(
                    "Ректутинг.xlsx written successfully on disk.");
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Display exceptions along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }

    public static void writeVacancy(List<Vacancy> vacancies)
    {
        try {
            FileInputStream file = new FileInputStream(
                    new File("Рекрутинг.xlsx"));
            // Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Creating a blank Excel sheet
        XSSFSheet sheet
                = workbook.getSheet("Вакансии");

        // Creating an empty TreeMap of string and Object][]
        // type
        Map<String, Object[]> data
                = new TreeMap<String, Object[]>();


        Integer index=2;
        data.put("1",
                new Object[] { "ID", "Позиция","Зарплата", "Обязанности", "Условия", "Требования", "Ключевые навыки", "Город", "Дата размещения", "Фирма" });
        for (Vacancy vacancy : vacancies) {
            data.put(index.toString(), new Object[]{vacancy.getId_vacancy(), vacancy.getPosition(), vacancy.getSalary(), vacancy.getResponsibilities(), vacancy.getRequirements(), vacancy.getConditions(), vacancy.getKeySkills(), vacancy.getCity(), vacancy.getDateOfCreate().toString(), vacancy.getFirm().getTitle()});
            index++;
        }

        // Iterating over data and writing it to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;

        for (String key : keyset) {

            // Creating a new row in the sheet
            Row row = sheet.createRow(rownum++);

            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr) {

                // This line creates a cell in the next
                // column of that row
                Cell cell = row.createCell(cellnum++);

                if (obj instanceof String)
                    cell.setCellValue((String)obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        // Try block to check for exceptions

            file.close();
            // Writing the workbook
            FileOutputStream out = new FileOutputStream(
                    "Рекрутинг.xlsx");
            workbook.write(out);

            // Closing file output connections
            out.close();

            // Console message for successful execution of
            // program
            System.out.println(
                    "Ректутинг.xlsx written successfully on disk.");
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Display exceptions along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }

    public static void writeResponse(List<Response> responses)
    {
        try {
            FileInputStream file = new FileInputStream(
                    new File("Рекрутинг.xlsx"));
            // Blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Creating a blank Excel sheet
            XSSFSheet sheet
                    = workbook.getSheet("Отклики");

            // Creating an empty TreeMap of string and Object][]
            // type
            Map<String, Object[]> data
                    = new TreeMap<String, Object[]>();


            Integer index=2;
            data.put("1",
                    new Object[] { "ID", "Дата отклика","Статус", "Комментарий", "Гитхаб", "Позиция", "Фирма", "Соискатель" });
            for (Response response : responses) {
                data.put(index.toString(), new Object[]{response.getId_response(), response.getDateOfCreate().toString(), response.getStatus(), response.getComment(), response.getGitHub(), response.getVacancy().getPosition(), response.getVacancy().getFirm().getTitle(), (response.getApplicant().getUser().getFirstName()+ " " +response.getApplicant().getUser().getLastName())});
                index++;
            }

            // Iterating over data and writing it to sheet
            Set<String> keyset = data.keySet();

            int rownum = 0;

            for (String key : keyset) {

                // Creating a new row in the sheet
                Row row = sheet.createRow(rownum++);

                Object[] objArr = data.get(key);

                int cellnum = 0;

                for (Object obj : objArr) {

                    // This line creates a cell in the next
                    // column of that row
                    Cell cell = row.createCell(cellnum++);

                    if (obj instanceof String)
                        cell.setCellValue((String)obj);

                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }

            // Try block to check for exceptions

            file.close();
            // Writing the workbook
            FileOutputStream out = new FileOutputStream(
                    "Рекрутинг.xlsx");
            workbook.write(out);

            // Closing file output connections
            out.close();

            // Console message for successful execution of
            // program
            System.out.println(
                    "Ректутинг.xlsx written successfully on disk.");
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Display exceptions along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
}
