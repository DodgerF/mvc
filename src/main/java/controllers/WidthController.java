package controllers;

import com.example.mvc.Table;

public class WidthController {
    private final Table table;
    public WidthController(Table table) {
        this.table = table;
    }

    public boolean checkValue(String s) {
        try {
            double value = Double.parseDouble(s);
            if (value < 0){
                return false;
            }
            else {
                table.setWidth(value);
                return true;
            }
        }
        catch (Exception ignored) {
            return false;
        }
    }
}
