package com.hcmut.edu.vn.demo.parse;

import com.hcmut.edu.vn.demo.model.JourneryModel;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Journeyparse {
    private final static String FILE_RESOURCE ="journey/JourneyGPS/51B02517.xlsx";

    public static void main(String[] args) {
        Journeyparse journeyparse = new Journeyparse();
        try {
            journeyparse.readJourneyFromExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readJourneyFromExcel() throws IOException {
        InputStream inputStream = RouteParse.class.getClassLoader().getResourceAsStream(FILE_RESOURCE);
        Workbook workbook = null;
        String type = FilenameUtils.getExtension(FILE_RESOURCE);
        if ("xls".equals(type)) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }

        int sheetsNum = workbook.getNumberOfSheets();
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = datatypeSheet.iterator();

        JourneryModel journeryModel = null;
        List<JourneryModel> journeryModelList = new ArrayList<>();

        int row = 0;
        while(iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row == 0) {
                row++;
                continue;
            }
            Iterator<Cell> cellIterator = currentRow.iterator();
            journeryModel = new JourneryModel();

            int col = 0;
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                switch (col) {
                    case 0: {
                        journeryModel.setLat(Double.parseDouble(currentCell.toString()));
                        break;
                    }
                    case 1: {
                        journeryModel.setLng(Double.parseDouble(currentCell.toString()));
                        break;
                    }
                    case 2: {
                        journeryModel.setReceivingTime(Double.parseDouble(currentCell.toString()));
                        break;
                    }
                }
                col++;
            }
            row++;
            journeryModel.setJourneyType(0);
            journeryModel.setJourneyCode("51B02517");
            journeryModelList.add(journeryModel);
//            journeryModelList.save(routeModels);
            System.out.println(journeryModel.toString());
        }
    }
}
