package main;

import dtp.Response;
import managers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.*;
import commands.*;

import java.util.List;

public class App extends Thread {
    public static final int CONNECTION_TIMEOUT = 60 * 1000;

    public static final String HASHING_ALGORITHM = "SHA-512";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/studs";
    public static final String DATABASE_URL_HELIOS = "jdbc:postgresql://pg:5432/studs";
    public static final String DATABASE_CONFIG_PATH = "dbconfig.cfg";

    public static int PORT;
    public static final Logger rootLogger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        rootLogger.info("--------------------------------------------------------------------");
        rootLogger.info("----------------------ЗАПУСК СЕРВЕРА--------------------------------");
        rootLogger.info("--------------------------------------------------------------------");
        if(args.length != 0){
            try{
                PORT = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        CollectionManager collectionManager = new CollectionManager();

        CommandManager commandManager = new CommandManager(utility.DatabaseHandler.getDatabaseManager());
        commandManager.addCommand(List.of(
                new Help(commandManager),
                new Info(collectionManager),
                new Show(collectionManager),
                new AddElement(collectionManager),
                new Update(collectionManager),
                new RemoveById(collectionManager),
                new Clear(collectionManager),
                new ExecuteScript(),
                new Exit(),
                new AddMinCommand(collectionManager),
                new RemoveGreater(collectionManager),
                new RemoveLower(collectionManager),
                new History(commandManager),
                new RemoveAnyByAge(collectionManager),
                new CountByCharacterCommand(collectionManager),
                new PrintUniqueHeadCommand(collectionManager),
                new Register(DatabaseHandler.getDatabaseManager()),
                new Ping()
        ));
        Server server = new Server(commandManager, DatabaseHandler.getDatabaseManager());
        server.run();
    }
}