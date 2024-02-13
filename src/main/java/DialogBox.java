
import java.io.IOException;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private double charInterval = 20;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        HBox.setMargin(dialog, new Insets(0, 10, 0, 10));
        dialog.setStyle("-fx-text-fill: #33FF00; -fx-font-family: 'Monospaced'; -fx-font-size: 12pt;");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getGeePeeTeeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.displayTextWithTypingAnimation(text);
        db.flip();
        return db;
    }

    public void displayTextWithTypingAnimation(String text) {
        StringBuilder displayText = new StringBuilder();
        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(i * charInterval), e -> {
                displayText.append(c);
                dialog.setText(displayText.toString());
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }
}