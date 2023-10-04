package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class DataList<E extends Dragon> extends ConcurrentLinkedDeque<E> implements IDataCollection, Serializable {
    private LocalDateTime modificationDate;
    private LocalDateTime initDate;

    public DataList() {
        setInitDate();
        updateModificationDate();
    }

    /**
     *
     * @param data Сырой массив данных
     */
    public DataList(E[] data) {
        this();
        if (data != null)
            addAll(Arrays.asList(data));
    }

    @Override
    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    @Override
    public LocalDateTime getInitDate() {
        return initDate;
    }

    @Override
    public String getTypeName() {
        return Stack.class.getCanonicalName();
    }

    @Override
    public int getSize() {
        return size();
    }

    @Override
    public int generateUniqueId() {
        for (int id = 1; id < Integer.MAX_VALUE; id++) {
            if (Dragon.checkId(id) && !getAllIds().contains(id))
                return id;
        }
        throw new IndexOutOfBoundsException("Не удалось сгенерировать id в диапазонах типа int");
    }

    @Override
    public List<Integer> getAllIds() {
        return this.stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
    }

    private void updateModificationDate() {
        this.modificationDate = LocalDateTime.now();
    }

    private void setInitDate() {
        this.initDate = LocalDateTime.now();
    }
}
