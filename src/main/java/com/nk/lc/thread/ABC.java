package com.nk.lc.thread;

import java.util.Objects;

public class ABC {

    private static int state = 1;

    private static int count = 3;

    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Say(1));
        Thread t2 = new Thread(new Say(2));
        Thread t3 = new Thread(new Say(3));

        t1.start();
        t2.start();
        t3.start();

    }

    private static class Say implements Runnable {

        int num;

        int total = 0;

        public Say(int num) {
            this.num = num;
        }

        public void run() {
            while (true) {
                synchronized (lock) {

                    if (total > count) {
                        break;
                    }

                    while (state != num) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    System.out.println(num);
                    if (state == 1) {
                        state = 2;
                    } else if (state == 2) {
                        state = 3;
                    } else {
                        state = 1;
                    }
                    total ++;

                    lock.notifyAll();
                }


            }

        }

    }


}
