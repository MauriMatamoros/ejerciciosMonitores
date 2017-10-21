/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercios;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mmatamoros
 */
public class Buffer {
    private int maxSize;
    private int currentSize;
    private char[] array;
    private int input;
    private int output;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    
    public Buffer() {
        this(10);
    }
    
    public Buffer(int size) {
        this.maxSize = size;
        array = new char[size];
    }
    
    public void Add(char input) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.currentSize == this.maxSize) {
                this.notFull.await();
            }
            int counterA = 0;
            int counterB = 0;
            for (int i = 0; i < this.maxSize; i++) {
                if (input == 'a' && this.array[i] == 'a') {
                    counterA++;
                }else if (input == 'b' && this.array[i] == 'b') {
                    counterB++;
                }
            }
            if ((counterA < 2 && input == 'a') || (counterB < 2 && input == 'b')) {
                this.array[this.input] = input;
                this.input = (this.input + 1) % this.maxSize;
                this.currentSize++;
                System.out.println(this.array);
                this.notEmpty.signal();
            }else{
                this.notFull.signal();
            }
        } finally {
            this.lock.unlock();
        }
    }
    
    public char Remove() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.currentSize == 0) {
                this.notEmpty.await();
            }
            char returnValue = this.array[this.output];
            this.output = (this.output + 1) % this.maxSize;
            this.currentSize--;
            this.notFull.signal();
            return returnValue;
        } finally {
            this.lock.unlock();
        }
    }
    
    public boolean full () throws InterruptedException{
        return this.currentSize == this.maxSize;
    }
            
    public void empty () throws InterruptedException {
        this.lock.lock();
        try {
            while (this.currentSize == 0) {
                this.notEmpty.await();
            }
            this.array = new char[this.maxSize];
            this.currentSize = 0;
            this.notFull.signal();
        } finally {
            this.lock.unlock();
        }
    }
}
