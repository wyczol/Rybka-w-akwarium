package controller;

import java.io.IOException;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationWindowContoller {
	Stage primaryStage;

	@FXML
	private Circle circle;

	@FXML // cialo ryby
	private Ellipse f_body;

	@FXML // oko ryby
	private Circle f_eye;

	@FXML // plewa prawa i lewa
	private Polygon f_finLeft, f_finRight;

	@FXML // bąblae powietrza
	private Circle b1, b2, b3, b4, b5;

	@FXML
	ImageView img_fish, img_fish2, img_fish3, img_fish4, img_fish5;

	// animacje
	Animation animationRight, animationTimeLine, animationLeft, animationPath, animationPathRandom2,
			animationPathRandom3, animationPathRandom4, animationPathRandom5;

	private Stage animationWindowStage;
	private int animationNo;

	public void setAnimationNo(int animationNo) {
		this.animationNo = animationNo;
		if (animationNo == 1) {

			// poruszanie ogonka w prawo
			RotateTransition rotateRight = new RotateTransition();
			rotateRight.setNode(f_finLeft);
			rotateRight.setFromAngle(70);
			rotateRight.setToAngle(110);
			rotateRight.setDuration(Duration.seconds(0.1));
			rotateRight.setCycleCount(Animation.INDEFINITE);
			rotateRight.setInterpolator(Interpolator.LINEAR);
			rotateRight.setAutoReverse(true);
			animationRight = rotateRight;

			// poruszanie ogonka w lewo
			RotateTransition rotateLeft = new RotateTransition();
			rotateLeft.setNode(f_finRight);
			rotateLeft.setFromAngle(250);
			rotateLeft.setToAngle(290);
			rotateLeft.setDuration(Duration.seconds(0.1));
			rotateLeft.setCycleCount(Animation.INDEFINITE);
			rotateLeft.setInterpolator(Interpolator.LINEAR);
			rotateLeft.setAutoReverse(true);
			animationLeft = rotateLeft;

			// poruszanie rybki - klatki kluczowe
			KeyFrame kf1 = new KeyFrame(Duration.millis(0), new KeyValue(f_finLeft.layoutXProperty(), 10),
					new KeyValue(f_finRight.visibleProperty(), false), new KeyValue(f_body.layoutXProperty(), 90),
					new KeyValue(f_eye.layoutXProperty(), 120),

					// incjacja bąbla nr 1
					new KeyValue(b1.visibleProperty(), true), new KeyValue(b1.layoutYProperty(), 350),
					// inicjacja bąbla nr 2
					new KeyValue(b2.visibleProperty(), true), new KeyValue(b2.layoutYProperty(), 400),
					// incjacja bąbla nr 5
					new KeyValue(b5.visibleProperty(), true), new KeyValue(b5.layoutYProperty(), 400)

			);

			KeyFrame kf2 = new KeyFrame(Duration.millis(2000), new KeyValue(f_finLeft.layoutXProperty(), 310),
					new KeyValue(f_finRight.visibleProperty(), false), new KeyValue(f_body.layoutXProperty(), 390),
					new KeyValue(f_eye.layoutXProperty(), 420),

					// koniec bąbla nr 1
					new KeyValue(b1.layoutYProperty(), 100), new KeyValue(b1.visibleProperty(), false),
					// koniec bąbla nr 2
					new KeyValue(b2.layoutYProperty(), 50), new KeyValue(b2.visibleProperty(), false),
					// inicjacja bąbla nr 4
					new KeyValue(b4.visibleProperty(), true), new KeyValue(b4.layoutYProperty(), 500)

			);

			KeyFrame kf3 = new KeyFrame(Duration.millis(2000), new KeyValue(f_finRight.layoutXProperty(), 470),
					new KeyValue(f_finLeft.visibleProperty(), false), new KeyValue(f_finRight.visibleProperty(), true),
					new KeyValue(f_body.layoutXProperty(), 390), new KeyValue(f_eye.layoutXProperty(), 360),

					// inicjacla bąbla rn3
					new KeyValue(b3.visibleProperty(), true), new KeyValue(b3.layoutYProperty(), 350),
					// koniec bąbla nr 4
					new KeyValue(b4.visibleProperty(), false), new KeyValue(b4.layoutYProperty(), 50)

			);

			KeyFrame kf4 = new KeyFrame(Duration.millis(4300), new KeyValue(f_finLeft.visibleProperty(), false),
					new KeyValue(f_finRight.layoutXProperty(), 160), new KeyValue(f_body.layoutXProperty(), 90),
					new KeyValue(f_eye.layoutXProperty(), 60),

					// koniec bąbla nr 5
					new KeyValue(b5.visibleProperty(), false), new KeyValue(b5.layoutYProperty(), 50),
					// koniec bąbla nr 3
					new KeyValue(b3.visibleProperty(), false), new KeyValue(b3.layoutYProperty(), 50));

			Timeline t1 = new Timeline(kf1, kf2, kf3, kf4);//

			t1.setCycleCount(Animation.INDEFINITE);
			animationTimeLine = t1;

			// =======================================================================================
			// poruszanie rybki po ścieżce
			img_fish.setVisible(false);
			Path path = new Path();
			MoveTo moveTo = new MoveTo(0, 50);
			CubicCurveTo sineCurve = new CubicCurveTo(200, -250, 200, 150, 300, 0);
			path.getElements().addAll(moveTo, sineCurve);

			PathTransition transition = new PathTransition();
			transition.setNode(img_fish);

			transition.setDuration(Duration.seconds(5));
			transition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			transition.setInterpolator(Interpolator.EASE_OUT);
			transition.setPath(path);
			transition.setCycleCount(Animation.INDEFINITE);
			transition.setAutoReverse(false);
			animationPath = transition;

			// ============================================
			// rybka randomowa
			MoveTo moveTo3 = new MoveTo(0, 50);
			Path path3 = new Path();
			CubicCurveTo sineCurve3 = new CubicCurveTo(200, 100, 200, -150, 580, -80);
			path3.getElements().addAll(moveTo3, sineCurve3);
			PathTransition transition3 = new PathTransition();
			transition3.setNode(img_fish3);
			transition3.setDuration(Duration.seconds(7));
			transition3.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			transition3.setInterpolator(Interpolator.EASE_OUT);
			transition3.setPath(path3);
			animationPathRandom3 = transition3;

			MoveTo moveTo4 = new MoveTo(0, 50);
			Path path4 = new Path();
			CubicCurveTo sineCurve4 = new CubicCurveTo(200, new Random().nextInt((3)) * 50 * -1, -50, 150, 600, 350);
			path4.getElements().addAll(moveTo4, sineCurve4);
			PathTransition transition4 = new PathTransition();
			transition4.setNode(img_fish4);
			transition4.setDuration(Duration.seconds(5));
			transition4.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			transition4.setInterpolator(Interpolator.EASE_OUT);
			transition4.setPath(path4);
			transition4.setAutoReverse(false);
			animationPathRandom4 = transition4;

			MoveTo moveTo5 = new MoveTo(0, 50);
			Path path5 = new Path();
			CubicCurveTo sineCurve5 = new CubicCurveTo(200, new Random().nextInt((3)) * 150, 200, 150, 500, -200);
			path5.getElements().addAll(moveTo5, sineCurve5);
			PathTransition transition5 = new PathTransition();
			transition5.setNode(img_fish5);
			transition5.setDuration(Duration.seconds(10));
			transition5.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			transition5.setInterpolator(Interpolator.EASE_OUT);
			transition5.setPath(path5);
			transition5.setAutoReverse(false);
			animationPathRandom5 = transition5;

		}
	}

	public void setAnimationWindowStage(Stage animationWindowStage) {
		this.animationWindowStage = animationWindowStage;
	}

	@FXML
	void closeWindow() {
		animationWindowStage.close();

	}

	@FXML
	void pauseAnimation() {
		animationRight.pause();
		animationLeft.pause();
		animationPath.pause();
		animationTimeLine.pause();
		animationPathRandom5.pause();
		animationPathRandom4.pause();
		animationPathRandom3.pause();
		animationPath.pause();
	}

	@FXML
	void playAnimationPath() {
		img_fish.setVisible(true);
		animationPath.play();
	}

	@FXML
	void playAnimationPathRandom() {
		img_fish3.setVisible(true);
		animationPathRandom3.play();

		img_fish4.setVisible(true);
		animationPathRandom4.play();

		img_fish5.setVisible(true);
		animationPathRandom5.play();

	}

	@FXML
	void playAnimation() {
		animationRight.play();
		animationTimeLine.play();
		animationLeft.play();

	}




}
