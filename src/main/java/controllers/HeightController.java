package controllers;

import com.example.mvc.Table;

public class HeightController {
    private final Table table;
    public HeightController(Table table) {
        this.table = table;
    }

    public boolean checkValue(String s) {
        try {
            double value = Double.parseDouble(s);
            if (value < 0) {
                return false;
            }
            else {
                table.setHeight(value);
                return true;
            }
        }
        catch (Exception ignored) {
            return false;
        }
    }
}
