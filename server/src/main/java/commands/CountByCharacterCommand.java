package commands;

import dtp.Request;
import dtp.Response;
import dtp.ResponseStatus;
import exceptions.IllegalArguments;
import managers.CollectionManager;
import java.util.Objects;
import models.DragonCharacter;

public class CountByCharacterCommand extends Command{
    private CollectionManager collectionManager;

    public CountByCharacterCommand(CollectionManager collectionManager) {
        super("count_by_character", " character : вывести количество элементов, значение поля character которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArguments {
        if (request.getArgs().isBlank()) throw new IllegalArguments();
        try {
            DragonCharacter character = DragonCharacter.valueOf(request.getArgs().trim());
            return new Response(ResponseStatus.OK,"Количество элементов, с равным значением поля character: " +
                    collectionManager.getCollection().stream()
                            .filter(Objects::nonNull)
                            .filter(s -> s.getCharacter() == character)
                            .count());
        } catch (IllegalArgumentException exception) {
            return new Response(ResponseStatus.ERROR,"character должно быть enum");
        }
    }
}
