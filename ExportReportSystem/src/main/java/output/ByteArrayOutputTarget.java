/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package output;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */
public class ByteArrayOutputTarget implements OutputTarget {
    private byte[] last;
    @Override public void write(byte[] bytes) {
        this.last = bytes.clone(); 
    }
    
    public byte[] getLast() { 
        return last; 
    }
    @Override public String description() { return "InMemory(byte[])"; }
}
