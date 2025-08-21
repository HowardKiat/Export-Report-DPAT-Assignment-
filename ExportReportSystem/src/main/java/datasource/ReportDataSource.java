package datasource;

import java.util.List;
import model.Record;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public interface ReportDataSource {
    List<Record> fetch();
    String getName();
}
