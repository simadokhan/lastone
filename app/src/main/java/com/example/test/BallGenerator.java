package com.example.test;
import java.util.Random;

public class BallGenerator {
    private int r_value;
    private int g_value;
    private int b_value;

    public int getR_value() {
        return r_value;
    }

    public int getG_value() {
        return g_value;
    }

    public int getB_value() {
        return b_value;
    }


    public BallGenerator() {
        this.r_value = new Random().nextInt((245 - 50) + 1) + 50;
        this.g_value = new Random().nextInt((245 - 50) + 1) + 50;
        this.b_value = new Random().nextInt((245 - 50) + 1) + 50;
    }
    int hard= new Random().nextInt((5 - 1) + 1) + 1;
    int new_r,new_b,new_g;
    public int Hardr() {
        Random rnd = new Random();
        new_r = rnd.nextInt(
                this.r_value - this.r_value + hard
        ) + (this.r_value - hard);
        return new_r;
    }

    public int Hardg() {
        Random rnd = new Random();
        new_g = rnd.nextInt(
                this.r_value - this.r_value + hard
        ) + (this.r_value - hard);
        return new_g;
    }

    public int Hardb() {
        Random rnd = new Random();
        new_b = rnd.nextInt(
                this.b_value - this.b_value + hard
        ) + (this.b_value - hard);
        return new_b;
    }
    int mid= new Random().nextInt((30 - 6) + 1) + 6;
    public int midr() {
        Random rnd = new Random();
        new_r = rnd.nextInt(
                this.r_value - this.r_value +hard
        ) + (this.r_value - hard);
        return new_r;
    }

    public int midg() {
        Random rnd = new Random();
        new_g = rnd.nextInt(
                this.r_value - this.r_value + mid
        ) + (this.r_value - mid);
        return new_g;
    }

    public int midb() {
        Random rnd = new Random();
        new_b = rnd.nextInt(
                this.b_value - this.b_value +mid
        ) + (this.b_value - mid);
        return new_b;
    }
    int es= new Random().nextInt((45 - 30) + 1) +30;
    public int esr() {
        Random rnd = new Random();
        new_r = rnd.nextInt(
                this.r_value - this.r_value + es
        ) + (this.r_value - es);
        return new_r;
    }

    public int esg() {
        Random rnd = new Random();
        new_g = rnd.nextInt(
                this.r_value - this.r_value + es
        ) + (this.r_value -es);
        return new_g;
    }

    public int esb() {
        Random rnd = new Random();
        new_b = rnd.nextInt(
                this.b_value - this.b_value + es
        ) + (this.b_value - es);
        return new_b;
    }
}