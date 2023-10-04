package commandLine.forms;

import exceptions.ExceptionInFileMode;
import commandLine.*;
import models.Coordinates;
import utility.ConsoleColors;
import utility.ExecuteFileManager;

/**
 * Форма для координат
 */
public class CoordinatesForm extends Form<Coordinates>{
    private final Printable console;
    private final UserInput scanner;

    public CoordinatesForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Coordinates}
     * @return объект класса {@link Coordinates}
     */
    @Override
    public Coordinates build(){
        return new Coordinates(askX(), askY());
    }

    private int askX(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("X должно быть числом типа int");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
    private Long askY(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Y должно быть числом типа Float");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
