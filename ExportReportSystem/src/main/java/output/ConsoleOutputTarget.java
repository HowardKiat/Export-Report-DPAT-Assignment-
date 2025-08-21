/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package output;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */

public class ConsoleOutputTarget implements OutputTarget {
    @Override public void write(byte[] bytes) {
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
    @Override public String description() { return "Console"; }
}