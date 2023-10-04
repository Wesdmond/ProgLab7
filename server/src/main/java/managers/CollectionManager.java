package managers;

import exceptions.InvalidForm;
import models.DataList;
import models.Dragon;
import utility.DatabaseHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс организующий работу с коллекцией
 */
public class CollectionManager {
    private final DataList<Dragon> collection = new DataList<Dragon>();
    private static int nextId = 0;
    /**
     * Дата создания коллекции
     */
    private LocalDateTime lastInitTime;
    /**
     * Дата последнего изменения коллекции
     */
    private LocalDateTime lastSaveTime;

//    static final Logger collectionManagerLogger = LogManager.getLogger(CollectionManager.class);

    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
        collection.addAll(DatabaseHandler.getDatabaseManager().loadCollection());
    }

    public DataList<Dragon> getCollection() {
        return collection;
    }

    public static void updateId(Collection<Dragon> collection){
        nextId = collection.stream()
                .filter(Objects::nonNull)
                .map(Dragon::getId)
                .max(Integer::compareTo)
                .orElse(0);
//        collectionManagerLogger.info("Обновлен айди на " + nextId);
    }

    public static int getNextId(){
        return ++nextId;
    }
    /**
     * Метод скрывающий дату, если она сегодняшняя
     * @param localDateTime объект {@link LocalDateTime}
     * @return вывод даты
     */
    public static String timeFormatter(LocalDateTime localDateTime){
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Метод скрывающий дату, если она сегодняшняя
     * @param dateToConvert объект {@link Date}
     * @return вывод даты
     */
    public static String timeFormatter(Date dateToConvert){
        LocalDateTime localDateTime = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        if (localDateTime == null) return null;
        if (localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getLastInitTime() {
        return timeFormatter(lastInitTime);
    }

    public String getLastSaveTime() {
        return timeFormatter(lastSaveTime);
    }
    /**
     * @return Имя типа коллекции.
     */
    public String collectionType() {
        return "LinkedList";
    }

    public int collectionSize() {
        return collection.size();
    }

    public void clear(){
        this.collection.clear();
        lastInitTime = LocalDateTime.now();
//        collectionManagerLogger.info("Коллекция очищена");
    }

    public Dragon getLast() {
        return collection.getLast();
    }

    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */
    public Dragon getById(int id) {
        for (Dragon element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    /**
     * Изменить элемент коллекции с таким id
     * @param id id
     * @param newElement новый элемент
     * @throws InvalidForm Нет элемента с таким id
     */
    public void editById(int id, Dragon newElement){
        Dragon pastElement = this.getById(id);
        this.removeElement(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
//        collectionManagerLogger.info("Объект с айди " + id + " изменен", newElement);
    }

    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }

    public void addElement(Dragon dragon){
        this.lastSaveTime = LocalDateTime.now();
        collection.add(dragon);
//        collectionManagerLogger.info("Добавлен объект в коллекцию", dragon);
    }

    public void addElements(Collection<Dragon> collection) throws InvalidForm{
        if (collection == null) return;
        for (Dragon dragon :collection){
            this.addElement(dragon);
        }
    }

    public void removeElement(Dragon dragon){
        collection.remove(dragon);
    }

    public void removeElements(Collection<Dragon> collection){this.collection.removeAll(collection);}

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = getLast();

        StringBuilder info = new StringBuilder();
        for (Dragon dragon : collection) {
            info.append(dragon);
            if (dragon != last) info.append("\n\n");
        }
        return info.toString();
    }
}