package commands;

import dtp.Request;
import dtp.Response;
import dtp.ResponseStatus;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import models.Dragon;
import utility.DatabaseHandler;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

/**
 * Команда 'remove_any_by_age'
 * Удаляет из коллекции все элементы, значение поля age которых эквивалентны заданному
 */
public class RemoveAnyByAge extends Command implements CollectionEditor{
    private final CollectionManager collectionManager;

    public RemoveAnyByAge(CollectionManager collectionManager) {
        super("remove_any_by_age", "  age : удалить из коллекции все элементы, значение поля age которых эквивалентны заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArguments {
        if (request.getArgs().isBlank()) throw new IllegalArguments();
        try {
            long averageMark = Long.parseLong(request.getArgs().trim());
            List<Dragon> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(dragon -> (dragon.getAge()) == averageMark)
                    .filter((obj) -> DatabaseHandler.getDatabaseManager().deleteObject(obj.getId(), request.getUser()))
                    .toList();
            collectionManager.removeElements(toRemove);
            return new Response(ResponseStatus.OK,"Удалены элементы с таким average_mark");
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR,"average_mark должно быть числом типа long");
        }
    }
}
