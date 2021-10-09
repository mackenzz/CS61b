package com.company;

public class IntList {
    public int first;
    public IntList second;

    public IntList(int f, IntList s) {
        first = f;
        second = s;
    }

    public int size() {
        if (second == null) {
            return 1;
        }
        else
        {
            return 1 + this.second.size();
        }
    }


    public int get(int i) {
        if (i == 0) {
            return first;
        }
        else
            return second.get(i-1);

    }
}
