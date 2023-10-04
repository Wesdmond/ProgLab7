package commandLine.forms;

import commandLine.*;
import exceptions.ExceptionInFileMode;
import models.DragonType;
import utility.ConsoleColors;
import utility.ExecuteFileManager;

import java.util.Locale;

/**
 * Форма для выбора типа дракона
 */
public class DragonTypeForm extends Form<DragonType>{
    private final Printable console;
    private final UserInput scanner;


    public DragonTypeForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    /**
     * Сконструировать новый элемент класса {@link DragonType}
     * @return объект класса {@link DragonType}
     */
    @Override
    public DragonType build() {
        console.println("Возможные типы дракона: ");
        console.println(DragonType.names());
        while (true){
            console.println(ConsoleColors.toColor("Введите тип дракона" + ": ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                int ordinal = Integer.parseInt(input.toUpperCase(Locale.ROOT));
                return DragonType.class.getEnumConstants()[ordinal];
            } catch (Exception eexception) {
                try {
                    return DragonType.valueOf(input.toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException exception) {
                    console.printError("Такого типа нет в списке");
                    if (Console.isFileMode()) throw new ExceptionInFileMode();
                }
            }
        }
    }
}
