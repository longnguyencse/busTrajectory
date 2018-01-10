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

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

//@Component
public class RouteParse implements ApplicationListener<ContextRefreshedEvent>{
    private final static String FILE_RESOURCE ="route/RouteGPS/2.xlsx";

    @Autowired
    private RouteReponsitory routeParseJPA;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        try (Stream<Path> paths = Files.walk(Paths.get("D:\\Project\\ACA\\bustrajectory\\trunk-server\\src\\main\\resources\\route"))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEachOrdered(RouteParse::readPoi);
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("========================================================================================");
/*        final File folder = new File("D:\\Project\\ACA\\bustrajectory\\trunk-server\\src\\main\\resources\\route\\RouteCell60");
        List<String> files = listFilesForFolder(folder);
        for (String path: files) {
            System.out.println("==================== new file: " + path);
            readPoi(path);
        }*/
    }

    public List<String> listFilesForFolder(final File folder) {
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getPath());
                files.add(fileEntry.getPath());
            }
        }
        return files;
    }

    public static void main(String[] args) {
        final File folder = new File("D:\\Project\\ACA\\bustrajectory\\trunk-server\\src\\main\\resources\\route");
        RouteParse routeParse = new RouteParse();

        routeParse.listFilesForFolder(folder);
    }

//    private static void readPoi(Path path) {
//        System.out.println(path.toString());
//        try {
//            FileInputStream inputStream = new FileInputStream(new File(path.toUri()));
//            saveRouteDb(inputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private void saveRouteDb(FileInputStream fileInputStream) {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
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
            routeModel.setRouteType(60);
            routeModels.add(routeModel);
            routeParseJPA.save(routeModels);
        }
    }

    public void readPoi(String path) {
//        InputStream inputStream = RouteParse.class.getClassLoader().getResourceAsStream(path);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        ZipSecureFile.setMinInflateRatio(-1.0d);
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
            routeParseJPA.save(routeModels);
        }
    }
}
