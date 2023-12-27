package controllers;

import com.example.mvc.Table;

public class RotateController {
    private final Table table;
    public RotateController(Table table) {
        this.table = table;
    }

    public boolean checkValue(String s) {
        try {
            double value = Double.parseDouble(s);
            if (value > Math.abs(360)) {
                return false;
            }
            else {
                table.setRotate(value);
                return true;
            }
        }
        catch (Exception ignored) {
            return false;
        }
    }
}
