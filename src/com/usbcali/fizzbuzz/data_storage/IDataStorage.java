package com.usbcali.fizzbuzz.data_storage;

import java.util.List;

public interface IDataStorage {
    void save_data(List<String> data);
    List<String> read_data();
}
