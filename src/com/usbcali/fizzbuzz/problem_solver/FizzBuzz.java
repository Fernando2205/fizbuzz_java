package com.usbcali.fizzbuzz.problem_solver;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz implements IProblemSolver {
    private String fizz_buzz(long number) {
        String[] results = {"", "Fizz", "Buzz", "FizzBuzz"};
        int index = (number % 3 == 0 ? 1 : 0) + (number % 5 == 0 ? 2 : 0);
        return results[index].isEmpty() ? Long.toString(number) : results[index];
    }

    @Override
    public List<String> compute_results(List<String> data) {
        List<String> results = new ArrayList<>();
        for (String numberStr : data) {
            try {
                long number = Long.parseLong(numberStr);
                String fizzbuzz_result = fizz_buzz(number);
                results.add(number + " " + fizzbuzz_result);

            } catch (NumberFormatException e) {
                results.add(numberStr);
            }
        }
        return results;
    }


}
