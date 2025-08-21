package datasource;

import java.util.*;
import model.Record;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class InMemoryDataSource implements ReportDataSource {
    private final String name;
    private final List<Record> records;


    public InMemoryDataSource(String name, List<Record> records) {
        this.name = name;
        this.records = new ArrayList<>(records);
    }

    @Override public List<Record> fetch() {
        return Collections.unmodifiableList(records);
    }
    
    @Override public String getName() { 
        return name;
    }
}
