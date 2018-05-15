package ui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import objects.Order;
import objects.Pool;
import objects.Round;
import objects.Tournament;
import objects.auxiliary.PeekableCircularIterator;
import objects.match.Match;
import objects.team.Team;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-04-24.
 */
public class RoundsTabController {
	@FXML
	private VBox roundContent;
	@FXML
	private VBox poolContent;
	@FXML
	private VBox matchContent;

	private AnchorPane selectedRoundPane = null;
	private Round selectedRound = null;
	private AnchorPane selectedPoolPane = null;
	private Pool selectedPool = null;
	private AnchorPane selectedMatchPane = null;
	private Match selectedMatch = null;

	@FXML
	public void addRoundClick(ActionEvent actionEvent) throws IOException {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("");
		dialog.setHeaderText("Create a new Round in your Tournament");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Add Round",
		                                            ButtonBar.ButtonData
			                                            .OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,
		                                               ButtonType.CANCEL);

		// Create the roundName label and field.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField roundName = new TextField();
		roundName.setPromptText("Name");

		grid.add(new Label("Round name:"), 0, 0);
		grid.add(roundName, 1, 0);

		Node continueButton = dialog.getDialogPane().lookupButton(
			loginButtonType);
		continueButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		roundName.textProperty().addListener(
			(observable, oldValue, newValue) -> {
				continueButton.setDisable(newValue.trim().isEmpty());
			});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the roundName field by default.
		Platform.runLater(roundName::requestFocus);

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return roundName.getText();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(this::addRound);
	}

	public void addRound(String roundName) {
		Round round = new Round(roundName);
		Tournament.getInstance().addRound(round);

		Separator separator = new Separator();
		AnchorPane anchorPane = new AnchorPane();
		Label label = new Label(roundName);
		AnchorPane.setLeftAnchor(label, 10.0);
		AnchorPane.setTopAnchor(label, 12.0);

		Button button = new Button("Remove");
		button.setOnAction(evt -> {
			roundContent.getChildren().remove(anchorPane);
			roundContent.getChildren().remove(separator);
			Tournament.getInstance().removeRound(round);
			if (selectedRound != null && selectedRound.equals(round)) {
				selectedRound = null;
			}
		});
		AnchorPane.setRightAnchor(button, 20.0);
		AnchorPane.setTopAnchor(button, 7.0);
		AnchorPane.setBottomAnchor(button, 7.0);
		anchorPane.getChildren().addAll(label, button);
		anchorPane.setOnMouseClicked(
			event -> roundClicked(event, anchorPane, round));
		roundContent.getChildren().add(anchorPane);

		separator.setOrientation(Orientation.HORIZONTAL);
		roundContent.getChildren().add(separator);

		roundClicked(null, anchorPane, round);
	}

	private void roundClicked(MouseEvent event, AnchorPane anchorPane,
	                          Round round) {
		if (selectedRoundPane != null) {
			selectedRoundPane.setBackground(anchorPane.getBackground());
		}
		anchorPane.setBackground(new Background(
			new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY,
			                   Insets.EMPTY)));

		selectedRound = round;
		selectedRoundPane = anchorPane;

		showPools(selectedRound);
	}

	private void showPools(Round round) {
		poolContent.getChildren().clear();
		PeekableCircularIterator<Pool> poolIterator = round.iterator();
		while (poolIterator.hasNext()) {
			Pool pool = poolIterator.next();

			Separator separator = new Separator();
			AnchorPane anchorPane = new AnchorPane();
			Label label = new Label(pool.getPoolName());
			AnchorPane.setLeftAnchor(label, 10.0);
			AnchorPane.setTopAnchor(label, 12.0);

			Button button = new Button("Remove");
			button.setOnAction(evt -> {
				poolContent.getChildren().remove(anchorPane);
				poolContent.getChildren().remove(separator);
				round.removePool(pool);
				if (selectedPool != null && selectedPool.equals(pool)) {
					selectedPool = null;
				}
			});
			AnchorPane.setRightAnchor(button, 20.0);
			AnchorPane.setTopAnchor(button, 7.0);
			AnchorPane.setBottomAnchor(button, 7.0);
			anchorPane.getChildren().addAll(label, button);
			anchorPane.setOnMouseClicked(event -> {
				poolClicked(event, anchorPane, pool);
			});
			poolContent.getChildren().add(anchorPane);

			separator.setOrientation(Orientation.HORIZONTAL);
			poolContent.getChildren().add(separator);
		}
	}

	public void poolClicked(MouseEvent event, AnchorPane anchorPane,
	                        Pool pool) {
		if (selectedPool != null) {
			selectedPoolPane.setBackground(anchorPane.getBackground());
		}
		anchorPane.setBackground(new Background(
			new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY,
			                   Insets.EMPTY)));

		selectedPool = pool;
		selectedPoolPane = anchorPane;

		showMatches(selectedPool);
	}

	public void showMatches(Pool pool) {
		while (pool.hasNextMatch()) {
			Match match = pool.getNextMatch();

			StringBuilder poolName = new StringBuilder();
			List<Team> teams = match.getTeams();
			for (Team team : teams) {
				if (!"".equals(poolName.toString())) {
					poolName.append(" - ");
				}
				poolName.append(team.getName());
			}
			AnchorPane anchorPane = new AnchorPane();
			Label label = new Label(poolName.toString());
			AnchorPane.setLeftAnchor(label, 10.0);
			AnchorPane.setTopAnchor(label, 12.0);

			Button button = new Button("Remove");
			button.setOnAction(evt -> {
				poolContent.getChildren().remove(anchorPane);
				pool.removeMatch(match);
			});
			AnchorPane.setRightAnchor(button, 20.0);
			AnchorPane.setTopAnchor(button, 7.0);
			AnchorPane.setBottomAnchor(button, 7.0);
			anchorPane.getChildren().addAll(label, button);
			anchorPane.setOnMouseClicked(event -> {
				poolClicked(event, anchorPane, pool);
			});
			poolContent.getChildren().add(anchorPane);

			Separator separator = new Separator();
			separator.setOrientation(Orientation.HORIZONTAL);
			poolContent.getChildren().add(separator);
		}
	}

	@FXML
	public void addPoolClick(ActionEvent actionEvent) throws IOException {
		if (selectedRound == null) {
			addRoundClick(actionEvent);
		}

		// Create the custom dialog.
		Dialog<PoolCreationResult> dialog = new Dialog<>();
		dialog.setTitle("");
		dialog.setHeaderText("Create a new pool in " + selectedRound.getName
			());

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Add Round",
		                                            ButtonBar.ButtonData
			                                            .OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,
		                                               ButtonType.CANCEL);

		// Create the roundName and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField roundName = new TextField();
		roundName.setPromptText("Name");

		grid.add(new Label("Pool name:"), 0, 0);
		grid.add(roundName, 1, 0);

		Node continueButton = dialog.getDialogPane().lookupButton(
			loginButtonType);
		continueButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		roundName.textProperty().addListener(
			(observable, oldValue, newValue) -> {
				continueButton.setDisable(newValue.trim().isEmpty());
			});


		final ToggleGroup orderGroup = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Linear");
		rb1.setToggleGroup(orderGroup);
		rb1.setUserData(Order.LINEAR);
		rb1.setSelected(true);

		RadioButton rb2 = new RadioButton("Random");
		rb2.setUserData(Order.RANDOM);
		rb2.setToggleGroup(orderGroup);

		VBox vBox = new VBox();
		vBox.getChildren().add(grid);
		vBox.getChildren().add(rb1);
		vBox.getChildren().add(rb2);

		dialog.getDialogPane().setContent(vBox);

		// Request focus on the roundName field by default.
		Platform.runLater(roundName::requestFocus);

		dialog.setResultConverter(button -> {
			// here you can also check what button was pressed
			// and return things accordingly
			if (ButtonBar.ButtonData.OK_DONE.equals(button.getButtonData())) {
				return new PoolCreationResult(roundName.getText(),
				                              (Order) orderGroup
					                              .getSelectedToggle()
					                              .getUserData());
			} else {
				return null;
			}
		});
		Optional<PoolCreationResult> result = dialog.showAndWait();

		result.ifPresent(this::addPool);
	}

	public void addPool(PoolCreationResult poolCreationResult) {
		Pool pool = new Pool(poolCreationResult.getPoolName(),
		                     poolCreationResult.getOrder());
		selectedRound.addPool(pool);
		showPools(selectedRound);
	}

	@FXML
	public void addMatchClick(ActionEvent actionEvent) throws IOException {

	}
}

class PoolCreationResult {
	private String poolName;
	private Order order;

	public PoolCreationResult(String poolName, Order order) {
		this.poolName = poolName;
		this.order = order;
	}

	public String getPoolName() {
		return poolName;
	}

	public Order getOrder() {
		return order;
	}
}