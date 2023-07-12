package org.example;

public class Utils {
    public static boolean isOper(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }
}
