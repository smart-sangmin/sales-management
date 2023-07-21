package org.sangmins.salesmanagement.global;

public final class MathUtils {
    private MathUtils() {

    }

    public static int roundTensPlace(int dividedNum, int dividingNum) {
        return (int) Math.round((dividedNum / (double) dividingNum) / 10) * 10;
    }
}
