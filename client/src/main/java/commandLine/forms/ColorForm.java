package commandLine.forms;

import commandLine.*;
import exceptions.ExceptionInFileMode;
import models.Color;
import utility.ConsoleColors;
import utility.ExecuteFileManager;

import java.util.Locale;

/**
 * Форма для выбора цвета
 */
public class ColorForm extends Form<Color>{
    private final Printable console;
    private final UserInput scanner;


    public ColorForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }
    /**
     * Сконструировать новый элемент класса {@link Color}
     * @return объект класса {@link Color}
     */
    @Override
    public Color build() {
        console.println("Возможные цвета: ");
        console.println(Color.names());
        while (true){
            console.println(ConsoleColors.toColor("Введите цвет дракона" + ": ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                int ordinal = Integer.parseInt(input.toUpperCase(Locale.ROOT));
                return Color.class.getEnumConstants()[ordinal];
            } catch (Exception eexception) {
                try {
                    return Color.valueOf(input.toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException exception) {
                    console.printError("Такого цвета нет в списке");
                    if (Console.isFileMode()) throw new ExceptionInFileMode();
                }
            }
        }
    }
}
