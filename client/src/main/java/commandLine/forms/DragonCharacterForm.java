package commandLine.forms;

import commandLine.*;
import exceptions.ExceptionInFileMode;
import models.DragonCharacter;
import utility.ConsoleColors;
import utility.ExecuteFileManager;

import java.util.Locale;

/**
 * Форма для выбора характера дракона
 */
public class DragonCharacterForm extends Form<DragonCharacter>{
    private final Printable console;
    private final UserInput scanner;


    public DragonCharacterForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    /**
     * Сконструировать новый элемент класса {@link DragonCharacter}
     * @return объект класса {@link DragonCharacter}
     */
    @Override
    public DragonCharacter build() {
        console.println("Возможные типы дракона: ");
        console.println(DragonCharacter.names());
        while (true){
            console.println(ConsoleColors.toColor("Введите тип дракона" + ": ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                int ordinal = Integer.parseInt(input.toUpperCase(Locale.ROOT));
                return DragonCharacter.class.getEnumConstants()[ordinal];
            } catch (Exception eexception) {
                try {
                    return DragonCharacter.valueOf(input.toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException exception) {
                    console.printError("Такого типа нет в списке");
                    if (Console.isFileMode()) throw new ExceptionInFileMode();
                }

            }
        }
    }
}
