package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private MediaView mediaView;
    @FXML private Slider volumeSlider;
    private MediaPlayer mediaPlayer;
    private Media media;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File ("src/MediaToPlay/videoToPlayDamian.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height =mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        volumeSlider.setValue(mediaPlayer.getVolume() *100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() /100);
                }
            }
        );
    }

    public void play(javafx.event.ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    public void pause(javafx.event.ActionEvent event){
        mediaPlayer.pause();
    }

    public void slow(javafx.event.ActionEvent event){
        mediaPlayer.setRate(0.5);
    }

    public void fast(javafx.event.ActionEvent event){
        mediaPlayer.setRate(2);
    }

    public void reload(javafx.event.ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }


}
