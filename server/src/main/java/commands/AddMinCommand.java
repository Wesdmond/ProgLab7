package commands;

import dtp.Request;
import dtp.Response;
import dtp.ResponseStatus;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import models.Dragon;
import utility.DatabaseHandler;

import java.util.Objects;

/**
 * Команда 'add_if_max'
 * Добавляет элемент в коллекцию если он больше максмального
 */
public class AddMinCommand extends Command implements CollectionEditor{
    private final CollectionManager collectionManager;

    public AddMinCommand(CollectionManager collectionManager) {
        super("add_if_min", " {element}: добавить элемент в коллекцию если он меньше");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArguments {
        if (!request.getArgs().isBlank()) throw new IllegalArguments();
        if (Objects.isNull(request.getObject())){
            return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
        }
        if (request.getObject().compareTo(collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .min(Dragon::compareTo)
                .orElse(null)) < 0)
        {
            int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
            if(new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
            request.getObject().setId(new_id);
            request.getObject().setUserLogin(request.getUser().name());
            collectionManager.addElement(request.getObject());
            return new Response(ResponseStatus.OK, "Объект успешно добавлен");
        }
        return new Response(ResponseStatus.ERROR,"Элемент меньше максимального");
    }
}