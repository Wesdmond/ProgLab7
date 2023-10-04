package commandLine.forms;

import exceptions.ExceptionInFileMode;
import commandLine.*;
import models.*;
import utility.ConsoleColors;
import utility.ExecuteFileManager;


/**
 * Форма дракона

 */
public class DragonForm extends Form<Dragon> {
    private final Printable console;
    private final UserInput scanner;

    public DragonForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Dragon}
     *
     * @return объект класса {@link Dragon}
     */
    @Override
    public Dragon build() {
        return new Dragon(
                askName(),
                askCoordinates(),
                askAge(),
                askColor(),
                askDragonType(),
                askDragonCharacter(),
                askDragonHead()
        );
    }

    private String askName() {
        String name;
        while (true) {
            console.println(ConsoleColors.toColor("Введите имя дракона", ConsoleColors.GREEN));
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                console.printError("Имя не может быть пустым");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            } else {
                return name;
            }
        }
    }

    private Coordinates askCoordinates() {
        return new CoordinatesForm(console).build();
    }

    private Long askAge() {
        while (true) {
            console.println(ConsoleColors.toColor("Введите возраст дракона", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Возраст дракона должен быть числом типа Long");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }

    private Color askColor() {
        return new ColorForm(console).build();
    }

    private DragonType askDragonType() {
        return new DragonTypeForm(console).build();
    }

    private DragonCharacter askDragonCharacter() {
        return new DragonCharacterForm(console).build();
    }

    private DragonHead askDragonHead() {
        return new DragonHeadForm(console).build();
    }
}