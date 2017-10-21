/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercios;

/**
 *
 * @author mmatamoros
 */
public class Ejercios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Buffer buffer = new Buffer(10);
        new Productor(buffer, 'a').start();
        new Productor(buffer, 'b').start();
        new Productor(buffer, 'c').start();
    }
    
}
