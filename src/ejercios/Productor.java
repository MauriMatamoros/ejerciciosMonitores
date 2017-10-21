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
public class Productor extends Thread {
    private Buffer buffer;
    private char piece;
    
    public Productor () {
    
    }
    
    public Productor (Buffer buffer, char piece) {
        this.buffer = buffer;
        this.piece = piece;
    }
    
    public void run () {
        while (true) {
            try {
                if (this.piece == 'c') {
                    if(buffer.full()){
                        System.out.println("Piece 'c' created");
                        this.buffer.empty();
                    }
                }else {
                    this.buffer.Add(this.piece);
                }
            } catch (InterruptedException e) {
                System.out.println("oops");
            }
        }
    }
    
}
