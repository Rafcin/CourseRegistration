package main.services.table;

import main.fake.generate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class tableTest {
    @Test
    void print() {
        table table = new table();
        generate generate = new generate();
        table.print(generate.createStudent());
    }
}