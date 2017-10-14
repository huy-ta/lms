package lms.login;
import javafx.application.Application;
import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class AsyncLoadingExample extends Application {
    private void loadItems(final ObservableList<String> listItems, final ProgressIndicator loadingIndicator) {
        if (loadingIndicator.isVisible()) {
            return;
        }

        // clears the list items and start displaying the loading indicator at the Application Thread
        listItems.clear();
        loadingIndicator.setVisible(true);

        // loads the items at another thread, asynchronously
        Task listLoader = new Task<List<String>>() {
            {
                setOnSucceeded(workerStateEvent -> {
                    listItems.setAll(getValue());
                    loadingIndicator.setVisible(false); // stop displaying the loading indicator
                });

                setOnFailed(workerStateEvent -> getException().printStackTrace());
            }

            @Override
            protected List<String> call() throws Exception {
                final List<String> loadedItems = new LinkedList<>();

                Thread.sleep(2000l); // just emulates some loading time

                // populates the list view with dummy items
                while (loadedItems.size() < 10) {
                    loadedItems.add("Item " + loadedItems.size());
                }

                return loadedItems;
            }
        };

        Thread loadingThread = new Thread(listLoader, "list-loader");
        loadingThread.setDaemon(true);
        loadingThread.start();
    }

    @Override
    public void start(Stage primaryStage) {
        final ListView<String> listView = new ListView<>();
        final ObservableList<String> listItems = FXCollections.observableArrayList();
        final ProgressIndicator loadingIndicator = new ProgressIndicator();
        final Button button = new Button("Click me to start loading");

        primaryStage.setTitle("Async Loading Example");        

        listView.setPrefSize(200, 250);
        listView.setItems(listItems);

        loadingIndicator.setVisible(false);

        button.setOnAction(event -> loadItems(listItems, loadingIndicator));

        VBox root = new VBox(
                new StackPane(
                        listView,
                        loadingIndicator
                ),
                button
        );

        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}