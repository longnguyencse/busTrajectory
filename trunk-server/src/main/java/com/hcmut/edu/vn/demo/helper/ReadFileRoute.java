package com.hcmut.edu.vn.demo.helper;

import com.hcmut.edu.vn.demo.model.RouteModel;
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

/**
 * Created by nqlong on 11-Jan-18.
 */

public class ReadFileRoute {
    // file nam format to input
    private final static String FILE_RESOURCE ="route/RouteGPS/2.xlsx";

    public static void main(String[] args) {
        // main test
        ReadFileRoute readFileRoute = new ReadFileRoute();
        readFileRoute.readRouteFromFile(FILE_RESOURCE);
    }

    public List<RouteModel> readRouteFromFile(String path) {
        InputStream inputStream = ReadFileRoute.class.getClassLoader().getResourceAsStream(path);
        Workbook workbook = null;
        String type = FilenameUtils.getExtension(FILE_RESOURCE);
        if ("xls".equals(type)) {
            try {
                workbook = new HSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                workbook = new XSSFWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int sheetsNum = workbook.getNumberOfSheets();
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Workbook finalWorkbook = workbook;
        Iterator<Row> iterator = datatypeSheet.iterator();

        // row begin
        RouteModel routeModel = null;
        List<RouteModel> routeModels = new ArrayList<>();

        int row = 0;
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row == 0) {
                row++;
                continue;
            }
            Iterator<Cell> cellIterator = currentRow.iterator();
            routeModel = new RouteModel();


            int col = 0;
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                switch (col) {
                    case 0: {
                        routeModel.setRouteId(Integer.parseInt(currentCell.toString()));
                        break;
                    }
                    case 1: {
                        routeModel.setStationId(currentCell.toString());
                        break;
                    }
                    case 2: {
                        routeModel.setStationCode(currentCell.toString());
                        break;
                    }
                    case 3: {
                        routeModel.setStationDirection(Integer.parseInt(currentCell.toString()));
                        break;
                    }
                    case 4: {
                        routeModel.setStationOrder(currentCell.toString());
                        break;
                    }
                    case 5: {
                        routeModel.setStationAddress(currentCell.toString());
                        break;
                    }
                    case 7: {
                        routeModel.setLat(Double.parseDouble(currentCell.toString()));
                        break;
                    }
                    case 8: {
                        routeModel.setLng(Double.parseDouble(currentCell.toString()));
                        break;
                    }
                    case 9: {
                        routeModel.setPolyline(currentCell.toString());
                        break;
                    }
                    case 10: {
                        routeModel.setDistance(Double.parseDouble(currentCell.toString()));
                    }
                }
                col++;
            }
            System.out.println(routeModel.toString());
            row++;

            // add route type
            routeModel.setRouteType(0);
            routeModels.add(routeModel);
        }

        return routeModels;
    }
}
