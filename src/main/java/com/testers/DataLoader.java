package com.testers;

import com.testers.model.Bug;
import com.testers.model.Devices;
import com.testers.model.Tester;
import com.testers.model.TesterDevice;
import com.testers.repo.BugRepo;
import com.testers.repo.DevicesRepo;
import com.testers.repo.TesterDeviceRepo;
import com.testers.repo.TestersRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // This class is somewhat verbose and code is repeated.
    // Fast way to populate the H2 database for test example.

    private final BugRepo bugRepo;
    private final DevicesRepo devicesRepo;
    private final TestersRepo testersRepo;
    private final TesterDeviceRepo testerDeviceRepo;

    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData(){
        Resource bugFile = new ClassPathResource("static/bugs.csv");
        Resource deviceFile = new ClassPathResource("static/devices.csv");
        Resource tester_deviceFile = new ClassPathResource("static/tester_device.csv");
        Resource testersFile = new ClassPathResource("static/testers.csv");

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(bugFile.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Bug bugs = new Bug(
                        Long.parseLong(csvRecord.get("bugId")),
                        Long.parseLong(csvRecord.get("deviceId")),
                        Long.parseLong(csvRecord.get("testerId")));
                bugRepo.save(bugs);
            }
        } catch (
                IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(tester_deviceFile.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                TesterDevice testerDevice = new TesterDevice(
                        Long.parseLong(csvRecord.get("testerId")),
                        Long.parseLong(csvRecord.get("deviceId")));
                testerDeviceRepo.save(testerDevice);
            }
        } catch (
                IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(deviceFile.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Devices devices = new Devices(
                        Long.parseLong(csvRecord.get("deviceId")),
                        csvRecord.get("description"));
                devicesRepo.save(devices);
            }
        } catch (
                IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(testersFile.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (CSVRecord csvRecord : csvRecords) {
                Tester testers = new Tester(
                        Long.parseLong(csvRecord.get("testerId")),
                        csvRecord.get("firstName"),
                        csvRecord.get("lastName"),
                        csvRecord.get("country"),
                        LocalDateTime.parse(csvRecord.get("lastLogin"), format),
                        null);
                testersRepo.save(testers);
            }
        } catch (
                IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
