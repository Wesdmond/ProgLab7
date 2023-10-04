package models;

import java.io.Serializable;

public class DragonHead implements Comparable<DragonHead>, Validator, Serializable {
    private int eyesCount = 2;

    public DragonHead(){}

    public DragonHead(int eyesCount) {
        this.eyesCount = eyesCount;
    }

    public int getEyesCount() {
        return eyesCount;
    }

    public static void checkEyes(int eyesCount) {

    }

    /**
     * Сравнить два DragonHead по количеству глаз
     */
    @Override
    public int compareTo(DragonHead o) {
        return Integer.compare(this.getEyesCount(), o.getEyesCount());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == getClass()) {
            DragonHead convObj = (DragonHead) obj;
            return (convObj.getEyesCount() == ((DragonHead) obj).getEyesCount());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(eyesCount);
    }

    @Override
    public boolean validate() {
        return true;
    }
}