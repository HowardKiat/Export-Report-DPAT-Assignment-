/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.Record;
import java.util.*;
/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
// ===============================
// Version 2: Strategy Pattern
// Each export algorithm is encapsulated in its own class.
// Context selects which strategy to use at runtime.
// Improves flexibility: new formats can be added without changing old code.
// ===============================

// Strategy interface
interface ExportStrategy {
    String export(List<Record> data);
}

// Concrete strategies
class CsvExportStrategy implements ExportStrategy {
    @Override
    public String export(List<Record> data) {
        StringBuilder sb = new StringBuilder("id,name,amount\n");
        for (Record r : data) {
            sb.append(r.getId()).append(',')
              .append(r.getName()).append(',')
              .append(r.getAmount()).append('\n');
        }
        return sb.toString();
    }
}

class JsonExportStrategy implements ExportStrategy {
    @Override
    public String export(List<Record> data) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.size(); i++) {
            Record r = data.get(i);
            sb.append(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"amount\":%.2f}",
                    r.getId(), r.getName(), r.getAmount()));
            if (i < data.size() - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}

// Context
class ReportContext {
    private ExportStrategy strategy;

    public void setStrategy(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    public String export(List<Record> data) {
        return strategy.export(data);
    }
}

public class MainStrategy {
    public static void main(String[] args) {
        List<Record> data = Arrays.asList(
                new Record("R1", "Alice", 10),
                new Record("R2", "Bob", 20)
        );

        ReportContext context = new ReportContext();

        context.setStrategy(new CsvExportStrategy());
        System.out.println("=== STRATEGY CSV ===\n" + context.export(data));

        context.setStrategy(new JsonExportStrategy());
        System.out.println("=== STRATEGY JSON ===\n" + context.export(data));
    }
}
