package com.usbcali.fizzbuzz.my_app;

import com.usbcali.fizzbuzz.data_storage.FileStorage;
import com.usbcali.fizzbuzz.data_storage.IDataStorage;
import com.usbcali.fizzbuzz.problem_solver.FizzBuzz;
import com.usbcali.fizzbuzz.problem_solver.IProblemSolver;

import java.util.List;

public class MyApp {
    private IDataStorage data_handler;
    private IProblemSolver problem_handler;

    public MyApp(IDataStorage data_handler, IProblemSolver problem_solver_instance) {
        this.data_handler = data_handler;
        this.problem_handler = problem_solver_instance;
    }

    public void run() {
        List<String> input_data = data_handler.read_data();
        if (!input_data.isEmpty()) {
            List<String> results = problem_handler.compute_results(input_data);
            data_handler.save_data(results);
        } else {
            System.out.println("No data found to process.");

        }
    }

    public static void main(String[] args) {
        IDataStorage dataStorage = new FileStorage("input.txt","output.txt");
        IProblemSolver problemSolver = new FizzBuzz();
        MyApp app = new MyApp(dataStorage, problemSolver);
        app.run();
    }
}