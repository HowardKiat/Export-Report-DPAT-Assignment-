package model;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class Record {
    private final String id;
    private final String name;
    private final double amount;


    public Record(String id, String name, double amount) {
        this.id = id; this.name = name; this.amount = amount;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name; 
    }
    
    public double getAmount() { 
        return amount;
    }
}