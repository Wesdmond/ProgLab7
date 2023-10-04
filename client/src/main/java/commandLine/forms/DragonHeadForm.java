package commandLine.forms;

import exceptions.ExceptionInFileMode;
import commandLine.*;
import models.DragonHead;
import utility.ConsoleColors;
import utility.ExecuteFileManager;

/**
 * Форма для локации
 */
public class DragonHeadForm extends Form<DragonHead>{
    private final Printable console;
    private final UserInput scanner;

    public DragonHeadForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link DragonHead}
     * @return объект класса {@link DragonHead}
     */
    @Override
    public DragonHead build(){
        return new DragonHead(
                askEyesCount());
    }

    private int askEyesCount(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите количество глаз на голове", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("Количество глаз должно быть числом типа int");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
        }