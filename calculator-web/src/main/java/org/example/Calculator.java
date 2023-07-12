package org.example;

public class Calculator {
    private ArrayStack numStack;
    private ArrayStack operStack;
    private int openParenthesesCount;
    private int closeParenthesesCount;
    private boolean error;

    public Calculator() {
        numStack = new ArrayStack(10);
        operStack = new ArrayStack(10);
        openParenthesesCount = 0;
        closeParenthesesCount = 0;
        error = false;
    }

    public void calculate(String expression) {
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        char ch = ' ';
        int oper = 0;

        while (index < expression.length()) {
            ch = expression.charAt(index);

            if (ch == '(') {
                openParenthesesCount++;
                operStack.push(ch);
            } else if (ch == ')') {
                closeParenthesesCount++;
                if (openParenthesesCount <= 0) {
                    error = true;
                    break;
                }

                while (!operStack.isEmpty() && operStack.peek() != '(') {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = cal(num1, num2, oper);
                    numStack.push(res);
                }

                if (!operStack.isEmpty() && operStack.peek() == '(') {
                    operStack.pop();
                } else {
                    error = true;
                    break;
                }
            } else if (Utils.isOper(ch)) {
                if (!operStack.isEmpty() && Utils.priority(ch) <= Utils.priority(operStack.peek())) {
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    oper = operStack.pop();
                    res = cal(num1, num2, oper);
                    numStack.push(res);
                    operStack.push(ch);
                } else {
                    operStack.push(ch);
                }

            } else if (Character.isDigit(ch)) {
                StringBuilder numBuilder = new StringBuilder();
                numBuilder.append(ch);
                while (index + 1 < expression.length() && Character.isDigit(expression.charAt(index + 1))) {
                    numBuilder.append(expression.charAt(index + 1));
                    index++;
                }
                int num = Integer.parseInt(numBuilder.toString());
                numStack.push(num);
                if (index + 1 < expression.length() && expression.charAt(index + 1) == '(') {
                    error = true;
                    break;
                }
            }

            index++;
        }

        if (openParenthesesCount > closeParenthesesCount) {
            System.out.println("Ошибка: " + (openParenthesesCount - closeParenthesesCount) + " незакрытых скобок");
        } else if (openParenthesesCount < closeParenthesesCount) {
            System.out.println("Ошибка: " + (closeParenthesesCount - openParenthesesCount) + " лишних закрывающих скобок");
        }else if (error) {
            System.out.println("Ошибка: Цифра перед открывающей скобкой без знака");
        }  else {
            while (!operStack.isEmpty()) {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = cal(num1, num2, oper);
                numStack.push(res);
            }

            if (openParenthesesCount != closeParenthesesCount || error) {
                System.out.println("Ошибка: Несоответствие скобок");
            } else {
                System.out.println("Выражение: " + expression + " = " + numStack.pop());
            }
        }
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
        }
        return res;
    }
}
