package commands;

import dtp.Request;
import dtp.Response;
import dtp.ResponseStatus;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import models.Dragon;
import models.DragonHead;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Команда 'print_unique_head'
 * Выводит все элементы, значение поля head которых разные
 */
public class PrintUniqueHeadCommand extends Command {
    private CollectionManager collectionManager;

    public PrintUniqueHeadCommand(CollectionManager collectionManager) {
        super("print_unique_head", ": вывести количество элементов, значение поля head которых разные");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArguments {
        if (collectionManager.getCollection().isEmpty())
            return new Response(ResponseStatus.OK, "коллекция пуста");
        return new Response(ResponseStatus.OK, "элементы, с разным значением поля head:\n" +
                collectionManager.getCollection().stream()
                        .filter(Objects::nonNull)
                        .map(Dragon::getHead)
                        .distinct()
                        .map(Object::toString)
                        .collect(Collectors.joining()));

    }
}
