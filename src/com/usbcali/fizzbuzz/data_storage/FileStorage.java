package com.usbcali.fizzbuzz.data_storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements IDataStorage {
    private String input_path;
    private String output_path;

    public FileStorage(String input_file, String output_file) {
        // Read input and output paths from config file
        this.input_path = input_file;
        this.output_path = output_file;
    }

    @Override
    public void save_data(List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_path))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data saved in " + output_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> read_data() {
        List<String> data = new ArrayList<>();
        File inputFile = new File(input_path);

        if (!inputFile.exists()) {
                System.out.println("File " + input_path + " doesn't exist. Creating file with sample data...");
            createSampleInputFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(input_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            System.out.println("Data read from " + input_path);
        } catch (IOException e) {
            System.err.println("Error reading data: " + e.getMessage());
        }
        return data;
    }

    private void createSampleInputFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(input_path))) {
            for (int i = 1; i <= 20; i++) {
                writer.write(Integer.toString(i));
                writer.newLine();
            }
            System.out.println("Sample file created in " + input_path);
        } catch (IOException e) {
            System.err.println("Error creating sample file: " + e.getMessage());
        }
    }
}
