package models;

import java.io.Serializable;

/**
 * Координаты
 */
public class Coordinates implements Comparable<Coordinates>, Validator, Serializable {
    private int x;
    private Long y; //Поле не может быть null

    /**
     *
     * @param x Координата X
     * @param y Координата Y
     */
    public Coordinates(int x, Long y){
        setX(x);
        setY(y);
    }

    /**
     * Устанавливает новое значение поля X
     * @param x Координата X
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Возвращает поле X
     * @return Координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Устанваливает новое значение поля Y
     * @param y Координата Y
     */
    public void setY(Long y){
        this.y = y;
    }

    /**
     * Возвращает поле Y
     * @return Координата Y
     */
    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d; %d)", this.x, this.y);
    }

    /**
     * Сранивает координаты сначала по X. Если они равны, то по Y.
     * @param o Объекет класса Coordinates, с которым будет идти сравнение
     * @return значение 0, если объекты равны; значение меньше нуля если 1) если поле Y меньше поля Y параметра o
     * при равных полях X, или 2) если поле X меньше поля X параметра o
     */
    @Override
    public int compareTo(Coordinates o) {
        if (o.getX() == this.x) {
            return this.y.compareTo(o.getY());
        } else {
            return this.x - o.getX();
        }
    }

    @Override
    public boolean validate() {
        return y != null;
    }
}