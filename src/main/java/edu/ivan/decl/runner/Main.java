package edu.ivan.decl.runner;

import edu.ivan.decl.entity.Contract;
import edu.ivan.decl.entity.Transport;
import edu.ivan.decl.exception.ProjectException;
import edu.ivan.decl.parser.TransportParser;
import edu.ivan.decl.reader.ExcelReader;
import edu.ivan.decl.writer.XmlWriter;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ExcelReader reader = new ExcelReader(new File("input\\2019-1.xls"));
        XmlWriter writer = new XmlWriter(new File("output\\keita.xml"));
        TransportParser transportParser = new TransportParser();

        try {
            System.out.println("Data: ");
            List<List<String>> transportData = reader.readFile("2018_4");
            transportData.forEach(l -> System.out.println(l));
            List<Transport> transportList = transportParser.createTransports(transportData);
            writer.createFile(transportList);
        }catch (ProjectException e){
            e.printStackTrace();
        }
    }
}
