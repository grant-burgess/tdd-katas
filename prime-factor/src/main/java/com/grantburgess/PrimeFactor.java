package com.grantburgess;

import java.util.ArrayList;

public class PrimeFactor {
    public static ArrayList<Integer> of(int n) {
        ArrayList<Integer> primes = new ArrayList<>();

        for (int divisor = 2; n > 1; divisor++)
            for (; n % divisor == 0; n /= divisor)
                primes.add(divisor);

        return primes;
    }
}
