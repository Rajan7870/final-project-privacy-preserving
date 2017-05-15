/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Arun Kumar
 */
public class Stringtobinary {

    public static String main(String getVal) {

        String cipher = getVal;
        StringBuilder binary = null;
        try {
            byte[] bytes = cipher.getBytes();
            binary = new StringBuilder();
            for (byte b : bytes) {
                int val = b;
                for (int i = 0; i < 8; i++) {
                    binary.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            System.out.println("Binary....." + binary.toString());
            // return binary.toString();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return binary.toString();
    }
}
