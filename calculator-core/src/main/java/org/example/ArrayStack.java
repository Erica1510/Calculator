package org.example;

public class ArrayStack {
    private int maxSize; // Максимальная длина массива
    private int[] arr; // Массив, где хранятся данные
    private int top = -1; // Вершина стека, инициализируется значением -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public int peek() {
        return arr[top];
    }

    public int elementAt(int index) {
        return arr[index];
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("Стек полон.");
            return;
        }
        top++;
        arr[top] = val;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Стек пуст.");
        }
        int val = arr[top];
        top--;
        return val;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Стек пуст.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
