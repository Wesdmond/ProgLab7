package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Дракон, основной объект данных
 *
 */
public class Dragon implements Comparable<Dragon>, Validator, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле не может быть null
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;
    private String userLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * @param name Имя Дракона
     * @param coordinates Координаты
     * @param age Возраст Дракона
     * @param color Цвет Дракона
     * @param type Тип Дракона
     * @param character Характер Дракона
     * @param head Голова Дракона
     */
    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head, String userLogin){
        setId(0);
        setName(name);
        setCoordinates(coordinates);
        setAge(age);
        setColor(color);
        setType(type);
        setCharacter(character);
        setHead(head);
        generateCreationDate();
        setUserLogin(userLogin);
    }

    public Dragon(String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head){
        setId(0);
        setName(name);
        setCoordinates(coordinates);
        setAge(age);
        setColor(color);
        setType(type);
        setCharacter(character);
        setHead(head);
        generateCreationDate();
    }

    public Dragon(int id, String name, Coordinates coordinates, Date creationDate, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head, String userLogin) {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setAge(age);
        setColor(color);
        setType(type);
        setCharacter(character);
        setHead(head);
        generateCreationDate();
        setUserLogin(userLogin);
    }

    public static boolean checkId(Integer id) {
        return (id > 0);
    }

    /**
     * Устанавливает параметр id в поле id, поле проверяется перед установкой
     * @param id id
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Возвращает значение поля id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает параметр name в поле name, поле проверяется перед установкой
     * @param name Имя Дракона
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Возвращает значение поля name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает значение параметра coordinates в значение поля coordinates, значение проверяется перед установкой на соответствие ограничениям
     * @param coordinates Новые координаты
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setAge(Long age){
        this.age = age;
    }

    public Long getAge() {
        return this.age;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCreationDate(java.util.Date creationDate){
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void generateCreationDate() {
        this.creationDate = new java.util.Date();
    }

    public void setType(DragonType type){
        this.type = type;
    }

    public DragonType getType() {
        return type;
    }

    public void setCharacter(DragonCharacter character){
        this.character = character;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setHead(DragonHead head){
        this.head = head;
    }

    public DragonHead getHead() {
        return head;
    }

    @Override
    public String toString() {
        String creationDateString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, new Locale("ru")).format(this.creationDate);
        return String.format("""
                        ID: %d
                        Имя дракона: %s
                        Текущие координаты: %s
                        Дата создания: %s
                        Возраст: %d
                        Цвет дракона: %s
                        Тип дракона: %s
                        Характер дракона: %s
                        Количество глаз: %d
                        Владелец: %s
                        """,
                this.id, this.name, this.coordinates, creationDateString, this.age, this.color, this.type, this.character, this.head.getEyesCount(), this.userLogin
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == getClass()) {
            Dragon convObj = (Dragon) obj;
            return convObj.getId().equals(getId());
        } else {
            return false;
        }
    }

    /**
     * Сравнивает двух Dragon сначала по возрасту, если равны, то по координатам, если опять равны, то по имени, если снова равны, то по id
     *
     * @param o Объект класса Dragon, с которым будет идти сравнение
     */
    @Override
    public int compareTo(Dragon o) {
        if (Objects.isNull(o)) return 1;
        if (o.getAge().equals(this.age)) {
            if (o.getCoordinates().equals(this.coordinates)) {
                if (o.getName().equals(this.name)) {
                    return this.id - o.getId();
                } else {
                    return this.name.compareTo(o.getName());
                }
            } else {
                return this.coordinates.compareTo(o.getCoordinates());
            }
        } else {
            return this.age.compareTo(o.age);
        }
    }

    @Override
    public boolean validate(){
        if (name == null || name.isBlank()) return false;
        if (coordinates == null) return false;
        if (age == null || age <= 0) return false;
        if (color == null) return false;
        return creationDate != null;
    }
}