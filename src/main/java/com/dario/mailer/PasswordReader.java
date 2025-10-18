package com.dario.mailer;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class PasswordReader {

    public static String readPasswordWithAsterisk(String prompt) throws IOException {
        Console console = System.console();

        if (console == null) {
            // Fallback (p.ej., terminal embebido de algunos IDEs)
            System.out.print(prompt);
            return new Scanner(System.in).nextLine();
        }

        System.out.print(prompt);

        MaskingThread maskingThread = new MaskingThread();
        Thread mask = new Thread(maskingThread);
        mask.setDaemon(true);
        mask.start();

        char[] passwordChars = console.readPassword();
        maskingThread.stopMasking();

        return new String(passwordChars);
    }

    private static class MaskingThread implements Runnable {
        private volatile boolean stop;

        public void run() {
            try {
                while (!stop) {
                    System.out.print("\010*");
                    Thread.sleep(1);
                }
            } catch (InterruptedException ignored) {}
        }

        public void stopMasking() {
            this.stop = true;
        }
    }
}
