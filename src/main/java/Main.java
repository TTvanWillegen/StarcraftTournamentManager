import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-04-17.
 */
public class Main extends Application {
	public static void main(String[] args) {
		//read settings
		//allow firewall
		//make connection
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws IOException {
		stage.setTitle("StarCraft Tournament Manager");

		Parent root = FXMLLoader.load(getClass().getResource
			("ui/StarcraftRootView.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}
}
