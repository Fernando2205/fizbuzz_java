package com.usbcali.fizzbuzz.my_app;

import com.usbcali.fizzbuzz.data_storage.IDataStorage;
import com.usbcali.fizzbuzz.problem_solver.FizzBuzz;
import com.usbcali.fizzbuzz.problem_solver.IProblemSolver;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MyAppTest {
    private IDataStorage dataStorage;
    private IProblemSolver problemSolver;
    private MyApp app;

    @Before
    public void setUp() {
        dataStorage = new IDataStorage() {
            private List<String> data;

            @Override
            public List<String> read_data() {
                return data;
            }

            @Override
            public void save_data(List<String> data) {
                this.data = data;
            }
        };

        problemSolver = new FizzBuzz();

        app = new MyApp(dataStorage, problemSolver);
    }

    @Test
    public void testRunWithData() {
        dataStorage.save_data(Arrays.asList("-5", "2", "3", "15"));
        app.run();
        assertEquals(Arrays.asList("-5 Buzz", "2 2", "3 Fizz", "15 FizzBuzz"), dataStorage.read_data());
    }

    @Test
    public void testRunWithNoData() {
        dataStorage.save_data(Collections.emptyList());
        app.run();
        assertTrue(dataStorage.read_data().isEmpty());
    }

    @Test
    public void testRunWithMixedData() {
        dataStorage.save_data(Arrays.asList("1", "2", "3", "5", "15","A","B","C"));
        app.run();
        assertEquals(Arrays.asList("1 1", "2 2", "3 Fizz", "5 Buzz", "15 FizzBuzz", "A","B", "C"), dataStorage.read_data());
    }

    @Test
    public void testRunWithInvalidData() {
        dataStorage.save_data(Arrays.asList("a", "b", "c"));
        app.run();
        assertEquals(Arrays.asList("a", "b", "c"), dataStorage.read_data());
    }

}