package com.hcmut.edu.vn.demo.parse;

import com.hcmut.edu.vn.demo.model.RouteModel;
import com.hcmut.edu.vn.demo.model.RouteReponsitory;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RouteParse implements ApplicationListener<ContextRefreshedEvent>{
    private final static String FILE_RESOURCE ="route/RouteGPS/2.xlsx";

    @Autowired
    private RouteReponsitory routeParseJPA;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            readPoi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPoi() throws IOException {
        InputStream inputStream = RouteParse.class.getClassLoader().getResourceAsStream(FILE_RESOURCE);
//        ZipSecureFile.setMinInflateRatio(-1.0d);
        Workbook workbook = null;
        String type = FilenameUtils.getExtension(FILE_RESOURCE);
        if ("xls".equals(type)) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }

        int sheetsNum = workbook.getNumberOfSheets();
        Sheet datatypeSheet = workbook.getSheetAt(0);

        Workbook finalWorkbook = workbook;
        Iterator<Row> iterator = datatypeSheet.iterator();

        // row begin
        RouteModel routeModel = null;

        int row = 0;
        while(iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (row == 0) {
                row++;
                continue;
            }
            Iterator<Cell> cellIterator = currentRow.iterator();
            routeModel = new RouteModel();
            List<RouteModel> routeModels = new ArrayList<>();

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
                        routeModel.setDistance(Integer.parseInt(currentCell.toString()));
                    }
                }
                col++;
            }
            System.out.println(routeModel.toString());
            row++;

            // add route type
            routeModel.setRouteType(0);
            routeModels.add(routeModel);
            routeParseJPA.save(routeModels);
        }
    }
}
