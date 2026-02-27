package com.nk.lc.thread;

import java.util.Objects;

public class ABC {

    private static String state = "A";
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Say("A"));
        Thread t2 = new Thread(new Say("B"));
        Thread t3 = new Thread(new Say("C"));

        t3.start();
        t2.start();
        t1.start();

    }

    private static class Say implements Runnable {

        private String target;

        public Say(String target) {
            this.target = target;
        }

        @Override
        public void run() {

            while (true) {
                synchronized (lock) {
                    while (!Objects.equals(target,state)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println(target);
                    if (Objects.equals(state,"A")) {
                        state = "B";
                    } else if (Objects.equals(state,"B")) {
                        state = "C";
                    } else if (Objects.equals(state,"C")) {
                        state = "A";
                    }
                    lock.notifyAll();
                }
            }
        }
    }


}
