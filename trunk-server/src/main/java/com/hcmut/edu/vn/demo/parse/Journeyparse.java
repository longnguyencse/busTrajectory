package com.hcmut.edu.vn.demo.parse;

import com.hcmut.edu.vn.demo.model.JourneryModel;
import com.hcmut.edu.vn.demo.model.JourneryReponsitory;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

//@Component
public class Journeyparse implements ApplicationListener<ContextRefreshedEvent> {
    private final static String FILE_RESOURCE ="journey/JourneyGPS/51B02517.xlsx";
    @Autowired
    JourneryReponsitory journeryReponsitory;
//    public static void main(String[] args) {
//        Journeyparse journeyparse = new Journeyparse();
//        try {
//            journeyparse.readJourneyFromExcel();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        final File folder = new File("D:\\Project\\ACA\\bustrajectory\\trunk-server\\src\\main\\resources\\journey\\JourneyGPS");
        final String PATH = "";
        final File folder = new File("D:\\learn\\ACA2017\\busTrajectory\\trunk-server\\src\\main\\resources\\journey\\JourneyGPS");
        List<String> files = listFilesForFolder(folder);
        for (String path: files) {
            System.out.println("==================== new file: " + path);
            try {
                readJourneyFromExcel(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    private void readJourneyFromExcel(String path) throws IOException {
//        try (Stream<Path> paths = Files.walk(Paths.get("D:\\Project\\ACA\\bustrajectory\\trunk-server\\src\\main\\resources\\journey"))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(System.out::println);
//        }
//        InputStream inputStream = RouteParse.class.getClassLoader().getResourceAsStream(FILE_RESOURCE);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        ZipSecureFile.setMinInflateRatio(-1.0d);
        Workbook workbook = null;

        String type = FilenameUtils.getExtension(FILE_RESOURCE);
        String name = FilenameUtils.getBaseName(path);
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
            journeryModel.setJourneyCode(name);
            journeryModelList.add(journeryModel);
            journeryReponsitory.save(journeryModelList);
        }
    }


}
