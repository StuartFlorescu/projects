import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.FontPosture;

public class Spaceinvaders extends Application {


        // declares necessary components for game
        AnimationTimer timer;
        Timer bulletE1, bulletE2, bulletE3;

        // declares booleans for moving the shooter left
        boolean goLeft, goRight, shoot, shoot1, movingLeft = false;
        // declares booleans for enemy shooting
        boolean bulletV1, bulletV2, bulletV3, bulletV4, bulletV5, bulletV6, bulletV7, bulletV8, bulletV9, bulletV10,
        bulletB1;
        // declares ints for enemy shooting
        int bulleten1 = 0, bulleten2 = 1, bulleten3 = 2, bulleten4 = 3, bulleten5 = 4, bulleten6 = 5, bulleten7 = 6,
        bulleten8 = 7, bulleten9 = 8, bulleten10 = 9;
        int bulletBn1 = 1;
        int upperbound1 = 10, upperbound2 = 10, upperbound3 = 2;
        int randInt1, randInt2, randInt3;
        
        // declares bool to check if boss is edge right or left
        boolean edgeLeft, edgeRight = false;
        // bool if all enemy sway is left
        boolean movingRight = true;
        // bool if bullets have been shot
        boolean notshot = true, notshot1 = true;
        boolean bossshot=true;
        double bx,by;
        
    
        
        // creates an int counter and intialises sniperNumber
        int counter = 0, sniperNumber;
        // ints for tank lives
        int tank1lives = 2, tank2lives = 2, tank3lives = 2, tank4lives = 2, tank5lives = 2, tank6lives = 2,
                        tank7lives = 2, tank8lives = 2, tank9lives = 2, tank10lives = 2, tank11lives = 2;

        
        int sniper1lives = 1, sniper2lives = 1, sniper3lives = 1, sniper4lives = 1, sniper5lives = 1, sniper6lives = 1,
                        sniper7lives = 1, sniper8lives = 1, sniper9lives = 1, sniper10lives = 1;
        int bosslives = 10;
        int playerLives = 3;
        int sniperskilled = 0;
        int tankskilled = 0;

        // Declares all panes
        public static Pane layout;
        public static Pane pausePane = new Pane();
        public static Pane menuLayout = new Pane();
        public static Scene menuScene = new Scene(menuLayout, 1500, 1000);
        public static Pane conts = new Pane();
        public static Pane winPane = new Pane();
        public static Pane gamePane = new Pane();

        // declares all scenes
        public static Scene win = new Scene(winPane, 1500, 1000);
        public static Pane losePane = new Pane();
        public static Scene lose = new Scene(losePane, 1500, 1000);
        public static Scene space = new Scene(gamePane, 1500, 1000);
        public static Scene Thingey = new Scene(conts, 1500, 1000);
        public static Scene pause = new Scene(pausePane, 1500, 1000);

        @Override
        public void start(Stage primaryStage) throws Exception {

                
                Random rand1 = new Random();
                Random rand2 = new Random();
                Random rand3 = new Random();

                // ALL RELAVENT CODE FOR CONTROLS

                // Creates a black rectangle the panes width and height. 
                Rectangle huh = new Rectangle(1500, 1000);
                huh.setFill(Color.BLACK);
                losePane.getChildren().add(huh);

                // Creates a black rectangle the panes width and height. 
                Rectangle wtf = new Rectangle(1500, 1000);
                wtf.setFill(Color.BLACK);
                winPane.getChildren().add(wtf);

                  // Creates a black rectangle the panes width and height. 
                Rectangle imOutaNAmes = new Rectangle(1500, 1000);
                imOutaNAmes.setFill(Color.BLACK);
                gamePane.getChildren().add(imOutaNAmes);

                // Created the tank death sign for win
                Rectangle tankdeath = new Rectangle(600, 200, 80, 20);
                tankdeath.setFill(Color.RED);
                winPane.getChildren().add(tankdeath);

                  // Created the tank death sign for lose
                Rectangle tankdeath1 = new Rectangle(600, 200, 80, 20);
                tankdeath1.setFill(Color.RED);
                losePane.getChildren().add(tankdeath1);

                // Creates label for when you lose
                Label lost = new Label("You've lost");
                lost.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 60));
                lost.setStyle(
                                "-fx-text-fill: White");
                lost.relocate(600, 10);
                losePane.getChildren().add(lost);

                // creates sniper death icon when you win
                Polygon sniperdeath = new Polygon();
                sniperdeath.getPoints().addAll(new Double[] {
                        625.0, 285.0,
                        650.0, 285.0,
                        637.5, 335.0,

                });
                sniperdeath.setFill(Color.BLUE);
                winPane.getChildren().add(sniperdeath);

                // creates sniper death icon when you lose
                Polygon sniperdeath1 = new Polygon();
                sniperdeath1.getPoints().addAll(new Double[] {
                                625.0, 285.0,
                                650.0, 285.0,
                                637.5, 335.0,

                });
                sniperdeath1.setFill(Color.BLUE);
                losePane.getChildren().add(sniperdeath1);

                // creates label for win
                Label won = new Label("You've won!");
                won.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 60));
                won.setStyle(
                                "-fx-text-fill: White");
                won.relocate(600, 10);
                winPane.getChildren().add(won);

        

                // Creates icon for shooter lives
                Rectangle shooterlive1 = new Rectangle(30, 10);
                shooterlive1.relocate(1275, 100);
                shooterlive1.setFill(Color.WHITE);
                gamePane.getChildren().add(shooterlive1);
                Rectangle bulletlive1 = new Rectangle(3, 15);
                bulletlive1.relocate(1288, 95);
                bulletlive1.setFill(Color.WHITE);
                gamePane.getChildren().add(bulletlive1);

                // Creates icon for shooter lives
                Rectangle shooterlive2 = new Rectangle(30, 10);
                shooterlive2.relocate(1325, 100);
                shooterlive2.setFill(Color.WHITE);
                gamePane.getChildren().add(shooterlive2);
                Rectangle bulletlive2 = new Rectangle(3, 15);
                bulletlive2.relocate(1338, 95);
                bulletlive2.setFill(Color.WHITE);
                gamePane.getChildren().add(bulletlive2);

                 // Creates icon for shooter lives
                Rectangle shooterlive3 = new Rectangle(30, 10);
                shooterlive3.relocate(1375, 100);
                shooterlive3.setFill(Color.WHITE);
                gamePane.getChildren().add(shooterlive3);
                Rectangle bulletlive3 = new Rectangle(3, 15);
                bulletlive3.relocate(1388, 95);
                bulletlive3.setFill(Color.WHITE);
                gamePane.getChildren().add(bulletlive3);

                // Creates label for shooter lives
                Label lives = new Label("Lives");
                lives.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
                lives.setStyle(
                                "-fx-text-fill: White");
                lives.relocate(1315, 40);
                gamePane.getChildren().add(lives);

                

                // 11 tanks creations, colour filled red
                Rectangle tank1 = new Rectangle(145, 250, 80, 20);
                tank1.setFill(Color.RED);
                gamePane.getChildren().add(tank1);
                Rectangle tank2 = new Rectangle(245, 250, 80, 20);
                tank2.setFill(Color.RED);
                gamePane.getChildren().add(tank2);
                Rectangle tank3 = new Rectangle(345, 250, 80, 20);
                tank3.setFill(Color.RED);
                gamePane.getChildren().add(tank3);
                Rectangle tank4 = new Rectangle(445, 250, 80, 20);
                tank4.setFill(Color.RED);
                gamePane.getChildren().add(tank4);
                Rectangle tank5 = new Rectangle(545, 250, 80, 20);
                tank5.setFill(Color.RED);
                gamePane.getChildren().add(tank5);
                Rectangle tank6 = new Rectangle(645, 250, 80, 20);
                tank6.setFill(Color.RED);
                gamePane.getChildren().add(tank6);
                Rectangle tank7 = new Rectangle(745, 250, 80, 20);
                tank7.setFill(Color.RED);
                gamePane.getChildren().add(tank7);
                Rectangle tank8 = new Rectangle(845, 250, 80, 20);
                tank8.setFill(Color.RED);
                gamePane.getChildren().add(tank8);
                Rectangle tank9 = new Rectangle(945, 250, 80, 20);
                tank9.setFill(Color.RED);
                gamePane.getChildren().add(tank9);
                Rectangle tank10 = new Rectangle(1045, 250, 80, 20);
                tank10.setFill(Color.RED);
                gamePane.getChildren().add(tank10);
                Rectangle tank11 = new Rectangle(1145, 250, 80, 20);
                tank11.setFill(Color.RED);
                gamePane.getChildren().add(tank11);

                // 10 triangular sniper creations, colour filled blue
                Polygon sniper1 = new Polygon();
                sniper1.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper1.setFill(Color.BLUE);
                sniper1.relocate(225, 190);
                gamePane.getChildren().add(sniper1);
                Polygon sniper2 = new Polygon();
                sniper2.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper2.setFill(Color.BLUE);
                sniper2.relocate(325, 190);
                gamePane.getChildren().add(sniper2);
                Polygon sniper3 = new Polygon();
                sniper3.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper3.setFill(Color.BLUE);
                sniper3.relocate(425, 190);
                gamePane.getChildren().add(sniper3);
                Polygon sniper4 = new Polygon();
                sniper4.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper4.setFill(Color.BLUE);
                sniper4.relocate(525, 190);
                gamePane.getChildren().add(sniper4);
                Polygon sniper5 = new Polygon();
                sniper5.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper5.setFill(Color.BLUE);
                sniper5.relocate(625, 190);
                gamePane.getChildren().add(sniper5);
                Polygon sniper6 = new Polygon();
                sniper6.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper6.setFill(Color.BLUE);
                sniper6.relocate(725, 190);
                gamePane.getChildren().add(sniper6);

                Polygon sniper7 = new Polygon();
                sniper7.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper7.setFill(Color.BLUE);
                sniper7.relocate(825, 190);
                gamePane.getChildren().add(sniper7);

                Polygon sniper8 = new Polygon();
                sniper8.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper8.setFill(Color.BLUE);
                sniper8.relocate(925, 190);
                gamePane.getChildren().add(sniper8);

                Polygon sniper9 = new Polygon();
                sniper9.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper9.setFill(Color.BLUE);
                sniper9.relocate(1025, 190);
                gamePane.getChildren().add(sniper9);

                Polygon sniper10 = new Polygon();
                sniper10.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, 0.0,
                                12.5, 50.0,

                });
                sniper10.setFill(Color.BLUE);
                sniper10.relocate(1125, 190);
                gamePane.getChildren().add(sniper10);

                // boss created, colour gold, radius 50
                Circle boss = new Circle(50);
                boss.setFill(Color.GOLD);
                boss.relocate(670, 50);
                gamePane.getChildren().add(boss);
                // always add 25 to x to find the middle

                // creates boss bullet
                Rectangle bulletb1 = new Rectangle();
                bulletb1.setWidth(40);
                bulletb1.setHeight(60);
                bulletb1.setFill(Color.GOLD);
                bulletb1.setX(700);
                bulletb1.setY(100);
                gamePane.getChildren().add(bulletb1);

                // Creates rectangle for boss bullet to return to
                Rectangle bossFake = new Rectangle();
                bossFake.setWidth(20);
                bossFake.setHeight(30);
                bossFake.setFill(Color.GOLD);
                bossFake.setX(710);
                bossFake.setY(100);
                gamePane.getChildren().add(bossFake);

                // Shooter creation
                Rectangle shooter = new Rectangle();
                shooter.setX(688);
                shooter.setY(700);
                shooter.setWidth(60);
                shooter.setHeight(20);
                shooter.setFill(Color.WHITE);
                gamePane.getChildren().add(shooter);

                // creates switch for movement and shooting
                space.setOnKeyPressed(event -> {
                        switch (event.getCode()) {
                                // if left key pressed, shooter goes left
                                case LEFT:
                                        goLeft = true;
                                        break;
                                // if right key pressed, shooter goes right
                                case RIGHT:
                                        // shooter.setX(shooter.getX() + 5);
                                        goRight = true;

                                        break;
                                // if up preessed, counter incremented
                                case UP:
                                        counter++;
                                        if (counter % 2 == 1) {
                                                // checks for counter, even or odd, so shoots either bullet 1 or 2
                                                shoot = true;
                                                // shoots bullet 1
                                                notshot = false;
                                                // no longer moves the bullet left or right
                                                
                                        }
                                        if (counter % 2 == 0) {
                                                shoot1 = true;
                                                // shoots bullet 2
                                                notshot1 = false;
                                                // no longer moves the bullet left or right
                                        }
                                        break;
                                        // breaks
                                default:
                                        break;
                        }
                });

                space.setOnKeyReleased(event -> {
                        // if key released
                        switch (event.getCode()) {
                                case LEFT:
                                     
                                        goLeft = false;
                                        // sets goLeft to false
                                        break;
                                case RIGHT:
                                       
                                        goRight = false;
                                        // set goRight to false
                                        break;
                               
                                default:
                                        break;
                        }
                });

                // CREATES NEW SCENE

                primaryStage.setScene(Thingey);
                // Sets scene Thingey

                // creates retry button
                Button retry = new Button("Retry");
                retry.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
                retry.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                retry.relocate(670, 110);
                losePane.getChildren().add(retry);

                // creates set on action
                retry.setOnAction(even -> {
                        primaryStage.setScene(space);
                        // starts timer, creates new Timers
                        timer.start();
                        bulletE1 = new Timer();
                        bulletE2 = new Timer();
                        bulletE3= new Timer();
                        //create a new schedule of the timer, or something that can make the timer do something repeatedly at a delay
                        //in our case, this is generating random numbers
                        bulletE1.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //this makes a randInt and generates it at every delay 
                                        //utilizes the random.util feature of Java
                                        int randInt1 = rand1.nextInt(upperbound1);
                                        //this rand int is then checked in the following lines of code
                                        //each sniper has a unique integer (ranging from 0-9) and the timer generates a rand int (ints from 0-9)
                                        //If the randInt generated is equal to the snipers corrresponding integer, then the boolean to shoot is turned to true
                                        //this makes the bullets randomly fire, our desired way of enemies to shoot
                                        if (randInt1 == bulleten1) {
                                                bulletV1 = true;
        
                                        }
                                        if (randInt1 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt1 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt1 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt1 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt1 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt1 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt1 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt1 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt1 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                // the 1000 is the delay before the repeated execution starts
                                //the 500 is how long before it repeats
                        }, 1000, 500); 

                        //creates the same logic schedule task
                        //however, we do this timer again as we wanted two bullets from the enemies to be fired at once
                        bulletE2.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        int randInt2 = rand2.nextInt(upperbound2);
                                        if (randInt2 == bulleten1) {
                                                bulletV2 = true;
        
                                        }
                                        if (randInt2 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt2 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt2 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt2 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt2 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt2 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt2 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt2 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt2 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                //this one however, is delayed 1500 before running at a delay of 600 so that the shots seem to be sporadic
                        }, 1500, 600);

                        //this has the same logic as the sniper bullets, but is now for the boss bullet
                        bulletE3.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //the upper bound is 2, so the rand3 will generate ints in the range of (0,1)
                                        //this means that 1/2 chance every delay that the boss bullet fires
                                        int randInt3 = rand3.nextInt(upperbound3);
                                        //If it is equal to 1, then the boolean to fire the bullet is true
                                        //we also set bossshot equal to false as we do not want the bullet to move in the x as it it fired
                                        if (randInt3 == bulletBn1) {
                                                bulletB1 = true;
                                                bossshot = false;
                                        }
                                        //if the randint is equal to 0, then nothing is changed and bossshot is true and the bullet keeps
                                        //moving in the boss
                                        if (randInt3 ==0){
                                                bossshot=true;
                                        }
                                }
                                //this is done after a delay of 1500, then repeated every 2000
                        }, 1500, 2000);
                });
                // CREATES NEW SCENE

                // CREATES BACKROUND
                Rectangle tf = new Rectangle(1500, 1000);
                tf.setFill(Color.BLACK);
                pausePane.getChildren().add(tf);

                // CREATES THE BACKROUND
                Rectangle bck = new Rectangle(1500, 1000);
                bck.setFill(Color.BLACK);
                conts.getChildren().add(bck);

                // CREATES THE TITLE FOR THE CONTROL
                Label controlsTitle = new Label("Controls and Rules");
                controlsTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 60));
                controlsTitle.setStyle(
                                "-fx-text-fill: White");
                controlsTitle.relocate(515, 10);
                conts.getChildren().add(controlsTitle);

                // TITLE OF THE TANK
                Label tankName = new Label("The Tanks");
                tankName.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                tankName.setStyle(
                                "-fx-text-fill: White");
                tankName.relocate(100, 100);
                conts.getChildren().add(tankName);

                // DESCRIBES THE TANK
                Label tankDescription = new Label(
                                "The Tanks are big brutes,\ncapabale of sustaining 2\nshots from the player.\nTanks ensure the snipers can\nstay alive to kill the player.\nThey are red rectangles and\ncan not shoot at the player");
                tankDescription.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
                tankDescription.setStyle(
                                "-fx-text-fill: White");
                tankDescription.relocate(100, 300);
                conts.getChildren().add(tankDescription);

                // CREATES THE TANK
                Rectangle tankDisplay = new Rectangle(200, 40);
                tankDisplay.setFill(Color.RED);
                tankDisplay.relocate(100, 200);
                conts.getChildren().add(tankDisplay);

                // CREATES THE SNIPER NAME
                Label sniperName = new Label("The Snipers");
                sniperName.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                sniperName.setStyle(
                                "-fx-text-fill: White");
                sniperName.relocate(435, 100);
                conts.getChildren().add(sniperName);

                // DESCRIBES THE SNIPER
                Label sniperDescription = new Label(
                                "The snipers are deadly assasins.\nThey are fragile, but shoot bullets \nthat inflict damage on the player.\nTheir bullets are fast, and pack a punch\ntaking out 1 life per landed shot,\nbut, they are also destroyed in one shot.\nThey are blue triangles");
                sniperDescription.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
                sniperDescription.setStyle(
                                "-fx-text-fill: White");
                sniperDescription.relocate(400, 300);
                conts.getChildren().add(sniperDescription);

                // CREATES THE SNIPER
                Polygon sniperDisplay = new Polygon();
                sniperDisplay.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                50.0, 0.0,
                                25.0, 100.0,
                                // dimension ratio : width 1:length 2
                });
                sniperDisplay.setFill(Color.BLUE);
                sniperDisplay.relocate(500, 175);
                conts.getChildren().add(sniperDisplay);

                // CREATES THE BOSS NAME
                Label bossName = new Label("The Big Boss");
                bossName.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                bossName.setStyle(
                                "-fx-text-fill: White");
                bossName.relocate(800, 100);
                conts.getChildren().add(bossName);

                // DESCRIBES THE BOSS
                Label bossDescription = new Label(
                                "Oh my, the Big Boss is a handful.\nYour job will be to fight 1 and \nthe only way of winning the\nentire game is by killing him.\nLarge in size, big bosses can take\n10 shots from the player, and \nalso shoot back.");
                bossDescription.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
                bossDescription.setStyle(
                                "-fx-text-fill: White");
                bossDescription.relocate(800, 300);
                conts.getChildren().add(bossDescription);

                // DISPLAY FOR THE BOSS
                Circle bossDisplay = new Circle(50);
                bossDisplay.setFill(Color.GOLD);
                bossDisplay.relocate(875, 175);
                conts.getChildren().add(bossDisplay);

                // STRAIGHT LINE FOR ORGANISATION
                Rectangle splitter1 = new Rectangle(5, 500);
                splitter1.setFill(Color.WHITE);
                splitter1.relocate(1100, 0);
                conts.getChildren().add(splitter1);

                // STRAIGHT LINE FOR ORGANISATION
                Rectangle splitter2 = new Rectangle(99999, 5);
                splitter2.setFill(Color.WHITE);
                splitter2.relocate(0, 500);
                conts.getChildren().add(splitter2);

                // CREATES THE TITLE FOR RULES
                Label rulesTitle = new Label("Rules");
                rulesTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                rulesTitle.setStyle(
                                "-fx-text-fill: White");
                rulesTitle.relocate(1200, 100);
                conts.getChildren().add(rulesTitle);

                // CREATES THE RULES TEXT
                Label rules = new Label(
                                "The goals and rules of the game\nare simple. Destroy the boss. \nIf you get shot three times, you\nwill lose all your lives and die.\nThe shooter takes time\nto reload and can only\nshoot 2 shots at once since\nit needs time to reload");
                rules.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
                rules.setStyle(
                                "-fx-text-fill: White");
                rules.relocate(1130, 200);
                conts.getChildren().add(rules);

                // CREATES THE LEFT ARROW
                Polygon leftArrow = new Polygon();
                leftArrow.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                25.0, -25.0,
                                25.0, 25.0,

                });
                leftArrow.setFill(Color.WHITE);
                leftArrow.relocate(300, 650);
                conts.getChildren().add(leftArrow);

                // CREATES THE TITLE FOR THE LEFT ARROW
                Label leftTitle = new Label("Move Left");
                leftTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
                leftTitle.setStyle(
                                "-fx-text-fill: White");
                leftTitle.relocate(175, 650);
                conts.getChildren().add(leftTitle);

                // CREATES THE UP ARROW
                Polygon upArrow = new Polygon();
                upArrow.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                -25.0, 25.0,
                                25.0, 25.0,

                });
                upArrow.setFill(Color.WHITE);
                upArrow.relocate(400, 600);
                conts.getChildren().add(upArrow);

                // CREATES THE TITLE FOR THE UP ARROW
                Label upTitle = new Label("Shoot");
                upTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
                upTitle.setStyle(
                                "-fx-text-fill: White");
                upTitle.relocate(385, 550);
                conts.getChildren().add(upTitle);

                // CREATES THE RIGTH ARROW
                Polygon rightArrow = new Polygon();
                rightArrow.getPoints().addAll(new Double[] {
                                0.0, 0.0,
                                -25.0, -25.0,
                                -25.0, 25.0,

                });
                rightArrow.setFill(Color.WHITE);
                rightArrow.relocate(525, 650);
                conts.getChildren().add(rightArrow);

                // TITLE FOR THE RIGHT ARROW
                Label rightTitle = new Label("Move Right");
                rightTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30));
                rightTitle.setStyle(
                                "-fx-text-fill: White");
                rightTitle.relocate(575, 650);
                conts.getChildren().add(rightTitle);

                // BUTTON TO PLAY FROM THE CONTROLS MENU
                Button playMenu = new Button("Play");
                playMenu.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                playMenu.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                playMenu.relocate(1000, 625);
                conts.getChildren().add(playMenu);

                playMenu.setOnAction(even -> {

                        primaryStage.setScene(space);
                        timer.start();

                });

                // SHOWS THE STAGE
                primaryStage.show();
                primaryStage.setTitle("Space battle");

                // Sets scene menuscene
                primaryStage.setScene(menuScene);

                // RANDOM RECTANGLE FOR BACKROUND
                Rectangle shape = new Rectangle(1500, 1000);
                shape.setFill(Color.BLACK);
                menuLayout.getChildren().add(shape);

                // LABEL FOR THE GAMETITLE
                Label gameTitle = new Label("Space Invaders!");
                gameTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 60));
                gameTitle.setStyle(
                                "-fx-text-fill: White");
                gameTitle.relocate(525, 10);
                menuLayout.getChildren().add(gameTitle);

                // Creates first player bullet
                Rectangle bulletp1 = new Rectangle();
                bulletp1.setWidth(5);
                bulletp1.setHeight(30);
                bulletp1.setFill(Color.WHITE);
                bulletp1.setX(715);
                bulletp1.setY(690);
                gamePane.getChildren().add(bulletp1);
                // Creates second player bullet
                Rectangle bulletp2 = new Rectangle();
                bulletp2.setWidth(5);
                bulletp2.setHeight(30);
                bulletp2.setFill(Color.WHITE);
                bulletp2.setX(715);
                bulletp2.setY(690);
                gamePane.getChildren().add(bulletp2);

                // creates all enemy bullets
                Rectangle bullete1 = new Rectangle();
                bullete1.setWidth(5);
                bullete1.setHeight(30);
                bullete1.setFill(Color.BLUE);
                bullete1.setX(235);
                bullete1.setY(190);
                gamePane.getChildren().add(bullete1);

                Rectangle bullete2 = new Rectangle();
                bullete2.setWidth(5);
                bullete2.setHeight(30);
                bullete2.setFill(Color.BLUE);
                bullete2.setX(335);
                bullete2.setY(190);
                gamePane.getChildren().add(bullete2);

                Rectangle bullete3 = new Rectangle();
                bullete3.setWidth(5);
                bullete3.setHeight(30);
                bullete3.setFill(Color.BLUE);
                bullete3.setX(435);
                bullete3.setY(190);
                gamePane.getChildren().add(bullete3);

                Rectangle bullete4 = new Rectangle();
                bullete4.setWidth(5);
                bullete4.setHeight(30);
                bullete4.setFill(Color.BLUE);
                bullete4.setX(535);
                bullete4.setY(190);
                gamePane.getChildren().add(bullete4);

                Rectangle bullete5 = new Rectangle();
                bullete5.setWidth(5);
                bullete5.setHeight(30);
                bullete5.setFill(Color.BLUE);
                bullete5.setX(635);
                bullete5.setY(190);
                gamePane.getChildren().add(bullete5);

                Rectangle bullete6 = new Rectangle();
                bullete6.setWidth(5);
                bullete6.setHeight(30);
                bullete6.setFill(Color.BLUE);
                bullete6.setX(735);
                bullete6.setY(190);
                gamePane.getChildren().add(bullete6);

                Rectangle bullete7 = new Rectangle();
                bullete7.setWidth(5);
                bullete7.setHeight(30);
                bullete7.setFill(Color.BLUE);
                bullete7.setX(835);
                bullete7.setY(190);
                gamePane.getChildren().add(bullete7);

                Rectangle bullete8 = new Rectangle();
                bullete8.setWidth(5);
                bullete8.setHeight(30);
                bullete8.setFill(Color.BLUE);
                bullete8.setX(935);
                bullete8.setY(190);
                gamePane.getChildren().add(bullete8);

                Rectangle bullete9 = new Rectangle();
                bullete9.setWidth(5);
                bullete9.setHeight(30);
                bullete9.setFill(Color.BLUE);
                bullete9.setX(1035);
                bullete9.setY(190);
                gamePane.getChildren().add(bullete9);

                Rectangle bullete10 = new Rectangle();
                bullete10.setWidth(5);
                bullete10.setHeight(30);
                bullete10.setFill(Color.BLUE);
                bullete10.setX(1135);
                bullete10.setY(190);

                gamePane.getChildren().add(bullete10);

                // BUTTON TO PLAY
                Button play = new Button("Play");
                play.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                play.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                play.relocate(625, 100);
                menuLayout.getChildren().add(play);

                play.setOnAction(even -> {
                        // sets the scene to space when clicked

                        primaryStage.setScene(space);
                        
                        // start timer
                        timer.start();

                        bulletE1 = new Timer();
                        bulletE2 = new Timer();
                        bulletE3= new Timer();
                        bulletE1.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //this makes a randInt and generates it at every delay 
                                        //utilizes the random.util feature of Java
                                        int randInt1 = rand1.nextInt(upperbound1);
                                        //this rand int is then checked in the following lines of code
                                        //each sniper has a unique integer (ranging from 0-9) and the timer generates a rand int (ints from 0-9)
                                        //If the randInt generated is equal to the snipers corrresponding integer, then the boolean to shoot is turned to true
                                        //this makes the bullets randomly fire, our desired way of enemies to shoot
                                        if (randInt1 == bulleten1) {
                                                bulletV1 = true;
        
                                        }
                                        if (randInt1 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt1 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt1 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt1 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt1 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt1 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt1 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt1 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt1 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                // the 1000 is the delay before the repeated execution starts
                                //the 500 is how long before it repeats
                        }, 1000, 500); 

                        //creates the same logic schedule task
                        //however, we do this timer again as we wanted two bullets from the enemies to be fired at once
                        bulletE2.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        int randInt2 = rand2.nextInt(upperbound2);
                                        if (randInt2 == bulleten1) {
                                                bulletV2 = true;
        
                                        }
                                        if (randInt2 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt2 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt2 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt2 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt2 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt2 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt2 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt2 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt2 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                //this one however, is delayed 1500 before running at a delay of 600 so that the shots seem to be sporadic
                        }, 1500, 600);

                        //this has the same logic as the sniper bullets, but is now for the boss bullet
                        bulletE3.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //the upper bound is 2, so the rand3 will generate ints in the range of (0,1)
                                        //this means that 1/2 chance every delay that the boss bullet fires
                                        int randInt3 = rand3.nextInt(upperbound3);
                                        //If it is equal to 1, then the boolean to fire the bullet is true
                                        //we also set bossshot equal to false as we do not want the bullet to move in the x as it it fired
                                        if (randInt3 == bulletBn1) {
                                                bulletB1 = true;
                                                bossshot = false;
                                        }
                                        //if the randint is equal to 0, then nothing is changed and bossshot is true and the bullet keeps
                                        //moving in the boss
                                        if (randInt3 ==0){
                                                bossshot=true;
                                        }
                                }
                                //this is done after a delay of 1500, then repeated every 2000
                        }, 1500, 2000);
                });

                // PAUSE ACCESS BUTTON FROM THE GAMEPANE
                Button PauseAccess = new Button("| |");
                PauseAccess.setFocusTraversable(false);
                PauseAccess.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                PauseAccess.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                PauseAccess.relocate(10, 50);
                gamePane.getChildren().add(PauseAccess);

                PauseAccess.setOnAction(even -> {

                        primaryStage.setScene(pause);
                        timer.stop();
                        bulletE1.cancel();
                        bulletE2.cancel();
                        bulletE3.cancel();
                });
                // NEW LABEL FOR THE PAUSE MENU
                Label pauseTitle = new Label("Pause Menu");
                pauseTitle.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 60));
                pauseTitle.setStyle(
                                "-fx-text-fill: White");
                pauseTitle.relocate(525, 10);
                pausePane.getChildren().add(pauseTitle);

                // RESUME GAME BUTTON FROM PAUSE
                Button resume = new Button("Resume Play");
                resume.setFocusTraversable(false);
                resume.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                resume.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                resume.relocate(525, 100);
                pausePane.getChildren().add(resume);

                resume.setOnAction(even -> {
                        
                        // sets the scene to space
                        primaryStage.setScene(space);
                        timer.start();
                        // begins timer

                        bulletE1 = new Timer();
                        bulletE2 = new Timer();
                        bulletE3= new Timer();
                        bulletE1.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //this makes a randInt and generates it at every delay 
                                        //utilizes the random.util feature of Java
                                        int randInt1 = rand1.nextInt(upperbound1);
                                        //this rand int is then checked in the following lines of code
                                        //each sniper has a unique integer (ranging from 0-9) and the timer generates a rand int (ints from 0-9)
                                        //If the randInt generated is equal to the snipers corrresponding integer, then the boolean to shoot is turned to true
                                        //this makes the bullets randomly fire, our desired way of enemies to shoot
                                        if (randInt1 == bulleten1) {
                                                bulletV1 = true;
        
                                        }
                                        if (randInt1 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt1 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt1 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt1 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt1 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt1 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt1 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt1 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt1 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                // the 1000 is the delay before the repeated execution starts
                                //the 500 is how long before it repeats
                        }, 1000, 500); 

                        //creates the same logic schedule task
                        //however, we do this timer again as we wanted two bullets from the enemies to be fired at once
                        bulletE2.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        int randInt2 = rand2.nextInt(upperbound2);
                                        if (randInt2 == bulleten1) {
                                                bulletV2 = true;
        
                                        }
                                        if (randInt2 == bulleten2) {
                                                bulletV2 = true;
                                        }
                                        if (randInt2 == bulleten3) {
        
                                                bulletV3 = true;
                                        }
                                        if (randInt2 == bulleten4) {
        
                                                bulletV4 = true;
        
                                        }
                                        if (randInt2 == bulleten5) {
        
                                                bulletV5 = true;
        
                                        }
                                        if (randInt2 == bulleten6) {
        
                                                bulletV6 = true;
        
                                        }
                                        if (randInt2 == bulleten7) {
        
                                                bulletV7 = true;
        
                                        }
                                        if (randInt2 == bulleten8) {
        
                                                bulletV8 = true;
        
                                        }
                                        if (randInt2 == bulleten9) {
        
                                                bulletV9 = true;
        
                                        }
                                        if (randInt2 == bulleten10) {
        
                                                bulletV10 = true;
                                        }
                                }
                                //this one however, is delayed 1500 before running at a delay of 600 so that the shots seem to be sporadic
                        }, 1500, 600);

                        //this has the same logic as the sniper bullets, but is now for the boss bullet
                        bulletE3.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                        //the upper bound is 2, so the rand3 will generate ints in the range of (0,1)
                                        //this means that 1/2 chance every delay that the boss bullet fires
                                        int randInt3 = rand3.nextInt(upperbound3);
                                        //If it is equal to 1, then the boolean to fire the bullet is true
                                        //we also set bossshot equal to false as we do not want the bullet to move in the x as it it fired
                                        if (randInt3 == bulletBn1) {
                                                bulletB1 = true;
                                                bossshot = false;
                                        }
                                        //if the randint is equal to 0, then nothing is changed and bossshot is true and the bullet keeps
                                        //moving in the boss
                                        if (randInt3 ==0){
                                                bossshot=true;
                                        }
                                }
                                //this is done after a delay of 1500, then repeated every 2000
                        }, 1500, 2000);
                });
                // RETURN TO MAIN MENU (MM) BUTTON FROM PAUSE
                Button MM = new Button("Back to Main Menu");
                MM.setFocusTraversable(false);
                MM.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                MM.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                MM.relocate(475, 200);
                pausePane.getChildren().add(MM);

                MM.setOnAction(even -> {

                        primaryStage.setScene(menuScene);

                });
                // CONTROLS BUTTON
                Button ControlsAndRules = new Button("Controls and Rules");
                ControlsAndRules.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 45));
                ControlsAndRules.setStyle(
                                "-fx-text-fill: White; -fx-background-color: Black");
                ControlsAndRules.relocate(500, 200);
                menuLayout.getChildren().add(ControlsAndRules);

                // SENDS USER TO CONTROLS AND RULES SCENE

                ControlsAndRules.setOnAction(even -> {

                        primaryStage.setScene(Thingey);

                });

                // creates labels for tankd andd tankd1 (for win and lose pane)
                Label tankd = new Label();
                Label sniperd = new Label();
                Label tankd1 = new Label();
                Label sniperd1 = new Label();
                losePane.getChildren().add(tankd);
                losePane.getChildren().add(sniperd);
                winPane.getChildren().add(tankd1);
                winPane.getChildren().add(sniperd1);
              
                // ANIMATION FOR SHOOTER
                timer = new AnimationTimer() {
                        @Override
                        public void handle(long arg0) {
                                // this is the loop that runs 60 times a second
                                if (!movingRight && !movingLeft){
                                        //if enemies arent moving right or lift, they default to right
                                        movingRight=true;
                                }
                                if (goLeft) {
                                        // if player is holding left
                                        if (5 >= shooter.getX()) {
                                                // if shooter is less than 5

                                                // nothing happens
                                                shooter.setX(shooter.getX());
                                                if (notshot) {
                                                        bulletp1.setX(bulletp1.getX());
                                                }
                                                if (notshot1) {
                                                        bulletp2.setX(bulletp2.getX());
                                                }
                                        } else {
                                                // otherwise move 10 pixels left
                                                shooter.setX(shooter.getX() - 10);
                                                if (notshot) {
                                                        // incase bullet shot
                                                        bulletp1.setX(bulletp1.getX() - 10);
                                                }
                                                if (notshot1) {
                                                        // incase bullet 2 shot
                                                        bulletp2.setX(bulletp2.getX() - 10);
                                                }
                                        }

                                }
                                if (goRight) {
                                        // logic repeated for go right
                                        if (1370 <= shooter.getX()) {
                                                shooter.setX(shooter.getX());
                                                if (notshot) {
                                                        bulletp1.setX(bulletp1.getX());
                                                }
                                                if (notshot1) {
                                                        bulletp2.setX(bulletp2.getX());
                                                }
                                        } else {
                                                shooter.setX(shooter.getX() + 10);
                                                if (notshot) {
                                                        bulletp1.setX(bulletp1.getX() + 10);
                                                }
                                                if (notshot1) {
                                                        bulletp2.setX(bulletp2.getX() + 10);
                                                }
                                        }
                                }

                                        // if boss gets higher than 1150, edge right true
                                if (boss.getBoundsInParent().getMinX() > 1150 && boss.getBoundsInParent().getMinX() < 5000) {
                                        edgeRight = true;
                                }
                                        // if boss gets lower than 150, edge left = true
                                if (boss.getBoundsInParent().getMinX() < 150) {
                                        edgeLeft = true;
                                }

                                if (edgeRight) {

                                        // boss speeds up
                                        boss.setTranslateX(boss.getTranslateX() - 8);
                                        // only moves boss bullet if it has not been shot
                                        if (bossshot) {
                                                bulletb1.setTranslateX(bulletb1.getTranslateX() - 8);
                                        }
                                        bossFake.setX(bossFake.getX()-8);

                                        // once it reaches 850, stopps moving speedy and sets edge right to false
                                        if (boss.getBoundsInParent().getMinX() < 850) {
                                                edgeRight = false;
                                        }
                                } else if (edgeLeft) {
                                        // logic is repeated for edge left

                                        boss.setTranslateX(boss.getTranslateX() + 8);
                                        if (bossshot) {
                                                bulletb1.setTranslateX(bulletb1.getTranslateX() + 8);
                                                }
                                        bossFake.setX(bossFake.getX()+8);

                                        if (boss.getBoundsInParent().getMinX() > 450) {
                                                edgeLeft = false;

                                        }
                                } else {
                                        // will move boss opposit to side of player
                                        if (shooter.getBoundsInParent().getMinX()+ 30 <= boss.getBoundsInParent().getMinX() + 25) {
                                                boss.setTranslateX(boss.getTranslateX() + 2);
                                                // moves 2 right if player is to left
                                                if (bossshot) {
                                                        bulletb1.setTranslateX(bulletb1.getTranslateX() + 2);
                                                }
                                                bossFake.setX(bossFake.getX() + 2);

                                        }
                                        if (shooter.getBoundsInParent().getMinX() + 30 > boss.getBoundsInParent().getMinX() + 25) {
                                                // moves 2 left is player is to right
                                                boss.setTranslateX(boss.getTranslateX() - 2);
                                                if (bossshot) {
                                                        bulletb1.setTranslateX(bulletb1.getTranslateX() - 2);
                                                }
                                                bossFake.setX(bossFake.getX()-2);
                                        }
                                }

                                if (movingRight) {
                                        // shifts all tanks and sniper right 1 if moving right is true
                                        tank1.setX(tank1.getX() + 1);
                                        tank2.setX(tank2.getX() + 1);
                                        tank3.setX(tank3.getX() + 1);
                                        tank4.setX(tank4.getX() + 1);
                                        tank5.setX(tank5.getX() + 1);
                                        tank6.setX(tank6.getX() + 1);
                                        tank7.setX(tank7.getX() + 1);
                                        tank8.setX(tank8.getX() + 1);
                                        tank9.setX(tank9.getX() + 1);
                                        tank10.setX(tank10.getX() + 1);
                                        tank11.setX(tank11.getX() + 1);
                                        sniper1.setTranslateX(sniper1.getTranslateX() + 1);
                                        sniper2.setTranslateX(sniper2.getTranslateX() + 1);
                                        sniper3.setTranslateX(sniper3.getTranslateX() + 1);
                                        sniper4.setTranslateX(sniper4.getTranslateX() + 1);
                                        sniper5.setTranslateX(sniper5.getTranslateX() + 1);
                                        sniper6.setTranslateX(sniper6.getTranslateX() + 1);
                                        sniper7.setTranslateX(sniper7.getTranslateX() + 1);
                                        sniper8.setTranslateX(sniper8.getTranslateX() + 1);
                                        sniper9.setTranslateX(sniper9.getTranslateX() + 1);
                                        sniper10.setTranslateX(sniper10.getTranslateX() + 1);
                                        bullete1.setTranslateX(bullete1.getTranslateX() + 1);
                                        bullete2.setTranslateX(bullete2.getTranslateX() + 1);
                                        bullete3.setTranslateX(bullete3.getTranslateX() + 1);
                                        bullete4.setTranslateX(bullete4.getTranslateX() + 1);
                                        bullete5.setTranslateX(bullete5.getTranslateX() + 1);
                                        bullete6.setTranslateX(bullete6.getTranslateX() + 1);
                                        bullete7.setTranslateX(bullete7.getTranslateX() + 1);
                                        bullete8.setTranslateX(bullete8.getTranslateX() + 1);
                                        bullete9.setTranslateX(bullete9.getTranslateX() + 1);
                                        bullete10.setTranslateX(bullete10.getTranslateX() + 1);

                                        if (tank1.getX() >= 245) {
                                                // will switch moving right to left once tank1 is greater or equal to 245 
                                                movingRight = false;
                                                movingLeft = true;
                                        }
                                }
                                if (movingLeft) {
                                        // moves all tanks and snipers 1 left
                                        tank1.setX(tank1.getX() - 1);
                                        tank2.setX(tank2.getX() - 1);
                                        tank3.setX(tank3.getX() - 1);
                                        tank4.setX(tank4.getX() - 1);
                                        tank5.setX(tank5.getX() - 1);
                                        tank6.setX(tank6.getX() - 1);
                                        tank7.setX(tank7.getX() - 1);
                                        tank8.setX(tank8.getX() - 1);
                                        tank9.setX(tank9.getX() - 1);
                                        tank10.setX(tank10.getX() - 1);
                                        tank11.setX(tank11.getX() - 1);
                                        sniper1.setTranslateX(sniper1.getTranslateX() - 1);
                                        sniper2.setTranslateX(sniper2.getTranslateX() - 1);
                                        sniper3.setTranslateX(sniper3.getTranslateX() - 1);
                                        sniper4.setTranslateX(sniper4.getTranslateX() - 1);
                                        sniper5.setTranslateX(sniper5.getTranslateX() - 1);
                                        sniper6.setTranslateX(sniper6.getTranslateX() - 1);
                                        sniper7.setTranslateX(sniper7.getTranslateX() - 1);
                                        sniper8.setTranslateX(sniper8.getTranslateX() - 1);
                                        sniper9.setTranslateX(sniper9.getTranslateX() - 1);
                                        sniper10.setTranslateX(sniper10.getTranslateX() - 1);
                                        bullete1.setTranslateX(bullete1.getTranslateX() - 1);
                                        bullete2.setTranslateX(bullete2.getTranslateX() - 1);
                                        bullete3.setTranslateX(bullete3.getTranslateX() - 1);
                                        bullete4.setTranslateX(bullete4.getTranslateX() - 1);
                                        bullete5.setTranslateX(bullete5.getTranslateX() - 1);
                                        bullete6.setTranslateX(bullete6.getTranslateX() - 1);
                                        bullete7.setTranslateX(bullete7.getTranslateX() - 1);
                                        bullete8.setTranslateX(bullete8.getTranslateX() - 1);
                                        bullete9.setTranslateX(bullete9.getTranslateX() - 1);
                                        bullete10.setTranslateX(bullete10.getTranslateX() - 1);

                                        if (tank1.getX() <= 145) {
                                                // switches moving left to moving right once tank1 is leff than equal to 145
                                                movingRight = true;
                                                movingLeft = false;
                                        }
                                }
                                if (bulletp1.getBoundsInParent().getMinY() < -100) {
                                        // if the bulletp1 get off screen
                                        // bullet is reset to original status
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                }

                                
                                if (bulletp2.getBoundsInParent().getMinY() < -100) {
                                        // if the bulletp2 get off screen
                                        // bullet is reset to original status
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                }

                                if (shoot) {
                                        // if shoot is true, bulletp1 moves 20 up
                                        bulletp1.setTranslateY(bulletp1.getTranslateY() - 20);
                                }
                                if (shoot1) {
                                        // if shoot1 true, bullet p2 moves 20 up
                                        bulletp2.setTranslateY(bulletp2.getTranslateY() - 20);
                                }

                                if (tank1.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        // if tank makes contact with bullet, bullet is first reset
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank1lives--;
                                        // tank lives is decremented
                                        if (tank1lives == 0) {
                                                // if tank lives =0
                                                tankskilled++;
                                                // tanks killed is incremented
                                                tank1.relocate(10000, 0);
                                                // tank is "teleported" off sreen
                                        }
                                }
                                // logic repeated for both bullets and remaining tanks
                                if (tank1.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank1lives--;
                                        if (tank1lives == 0) {
                                                tankskilled++;
                                                tank1.relocate(10000, 0);
                                        }
                                }

                                if (tank2.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank2lives--;
                                        if (tank2lives == 0) {
                                                tankskilled++;
                                                tank2.relocate(10000, 0);
                                        }
                                }
                                if (tank2.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank2lives--;
                                        if (tank2lives == 0) {
                                                tankskilled++;
                                                tank2.relocate(10000, 0);
                                        }
                                }
                                if (tank3.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank3lives--;
                                        if (tank3lives == 0) {
                                                tankskilled++;
                                                tank3.relocate(10000, 0);
                                        }
                                }
                                if (tank3.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank3lives--;
                                        if (tank3lives == 0) {
                                                tankskilled++;
                                                tank3.relocate(10000, 0);
                                        }
                                }
                                if (tank4.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank4lives--;
                                        if (tank4lives == 0) {
                                                tankskilled++;
                                                tank4.relocate(10000, 0);
                                        }
                                }
                                if (tank4.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank4lives--;
                                        if (tank4lives == 0) {
                                                tankskilled++;
                                                tank4.relocate(10000, 0);
                                        }
                                }
                                if (tank5.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank5lives--;
                                        if (tank5lives == 0) {
                                                tankskilled++;
                                                tank5.relocate(10000, 0);
                                        }
                                }
                                if (tank5.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank5lives--;
                                        if (tank5lives == 0) {
                                                tankskilled++;
                                                tank5.relocate(10000, 0);
                                        }
                                }
                                if (tank6.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank6lives--;
                                        if (tank6lives == 0) {
                                                tankskilled++;
                                                tank6.relocate(10000, 0);
                                        }
                                }

                                if (tank6.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank6lives--;
                                        if (tank6lives == 0) {
                                                tankskilled++;
                                                tank6.relocate(10000, 0);
                                        }
                                }
                                if (tank7.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank7lives--;
                                        if (tank7lives == 0) {
                                                tankskilled++;
                                                tank7.relocate(10000, 0);
                                        }
                                }
                                if (tank7.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank7lives--;
                                        if (tank7lives == 0) {
                                                tankskilled++;
                                                tank7.relocate(10000, 0);
                                        }
                                }
                                if (tank8.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank8lives--;
                                        if (tank8lives == 0) {
                                                tankskilled++;
                                                tank8.relocate(10000, 0);
                                        }
                                }
                                if (tank8.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank8lives--;
                                        if (tank8lives == 0) {
                                                tankskilled++;
                                                tank8.relocate(10000, 0);
                                        }
                                }
                                if (tank9.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank9lives--;
                                        if (tank9lives == 0) {
                                                tankskilled++;
                                                tank9.relocate(10000, 0);
                                        }
                                }
                                if (tank9.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank9lives--;
                                        if (tank9lives == 0) {
                                                tankskilled++;
                                                tank9.relocate(10000, 0);
                                        }
                                }
                                if (tank10.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank10lives--;
                                        if (tank10lives == 0) {
                                                tankskilled++;
                                                tank10.relocate(10000, 0);
                                        }
                                }
                                if (tank10.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank10lives--;
                                        if (tank10lives == 0) {
                                                tankskilled++;
                                                tank10.relocate(10000, 0);
                                        }
                                }
                                if (tank11.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank11lives--;
                                        if (tank11lives == 0) {
                                                tankskilled++;
                                                tank11.relocate(10000, 0);
                                        }
                                }
                                if (tank11.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        tank11lives--;
                                        if (tank11lives == 0) {
                                                tankskilled++;
                                                tank11.relocate(10000, 0);
                                        }
                                }
                                
                                //if the shooter bullet hits the sniper then relocate the bullet back to the shooter
                                //relocate the sniper somewhere else as it died
                                //this code is the same logic to every other sniper, and is essentially a copy and paste
                                if (sniper1.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        //decrement the sniper lives
                                        sniper1lives--;
                                        if (sniper1lives == 0) {
                                                //increment the number of snipers that the player killed dduring his play
                                                sniperskilled++;
                                                sniper1.relocate(10000, 0);
                                                bullete1.relocate(10000, 0);
                                        }
                                }
                                //the same logic applies as above, but is for when the second shooter's bullet hits the sniper
                                //this is done for the rest of the snipers in the following lines 
                                if (sniper1.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper1lives--;
                                        if (sniper1lives == 0) {
                                                sniperskilled++;
                                                sniper1.relocate(10000, 0);
                                                bullete1.relocate(10000, 0);
                                        }
                                }
                                if (sniper2.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper2lives--;
                                        if (sniper2lives == 0) {
                                                sniperskilled++;
                                                sniper2.relocate(10000, 0);
                                                bullete2.relocate(10000, 0);
                                        }
                                }
                                if (sniper2.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper2lives--;
                                        if (sniper2lives == 0) {
                                                sniperskilled++;
                                                sniper2.relocate(10000, 0);
                                                bullete2.relocate(10000, 0);
                                        }
                                }
                                if (sniper3.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper3lives--;
                                        if (sniper3lives == 0) {
                                                sniperskilled++;
                                                sniper3.relocate(10000, 0);
                                                bullete3.relocate(10000, 0);
                                        }
                                }
                                if (sniper3.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper3lives--;
                                        if (sniper3lives == 0) {
                                                sniperskilled++;
                                                sniper3.relocate(10000, 0);
                                                bullete3.relocate(10000, 0);
                                        }
                                }
                                if (sniper4.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper4lives--;
                                        if (sniper4lives == 0) {
                                                sniperskilled++;
                                                sniper4.relocate(10000, 0);
                                                bullete4.relocate(10000, 0);
                                        }
                                }
                                if (sniper4.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper4lives--;
                                        if (sniper4lives == 0) {
                                                sniperskilled++;
                                                sniper4.relocate(10000, 0);
                                                bullete4.relocate(10000, 0);
                                        }
                                }
                                if (sniper5.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper5lives--;
                                        if (sniper5lives == 0) {
                                                sniperskilled++;
                                                sniper5.relocate(10000, 0);
                                                bullete5.relocate(10000, 0);
                                        }
                                }
                                if (sniper5.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper5lives--;
                                        if (sniper5lives == 0) {
                                                sniperskilled++;
                                                sniper5.relocate(10000, 0);
                                                bullete5.relocate(10000, 0);
                                        }
                                }
                                if (sniper6.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper6lives--;
                                        if (sniper6lives == 0) {
                                                sniperskilled++;
                                                sniper6.relocate(10000, 0);
                                                bullete6.relocate(10000, 0);
                                        }
                                }
                                if (sniper6.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper6lives--;
                                        if (sniper6lives == 0) {
                                                sniperskilled++;
                                                sniper6.relocate(10000, 0);
                                                bullete6.relocate(10000, 0);
                                        }
                                }
                                if (sniper7.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper7lives--;
                                        if (sniper7lives == 0) {
                                                sniperskilled++;
                                                sniper7.relocate(10000, 0);
                                                bullete7.relocate(10000, 0);
                                        }
                                }
                                if (sniper7.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper7lives--;
                                        if (sniper7lives == 0) {
                                                sniperskilled++;
                                                sniper7.relocate(10000, 0);
                                                bullete7.relocate(10000, 0);
                                        }
                                }
                                if (sniper8.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper8lives--;
                                        if (sniper8lives == 0) {
                                                sniperskilled++;
                                                sniper8.relocate(10000, 0);
                                                bullete8.relocate(10000, 0);
                                        }
                                }
                                if (sniper8.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper8lives--;
                                        if (sniper8lives == 0) {
                                                sniperskilled++;
                                                sniper8.relocate(10000, 0);
                                                bullete8.relocate(10000, 0);
                                        }
                                }
                                if (sniper9.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper9lives--;
                                        if (sniper9lives == 0) {
                                                sniperskilled++;
                                                sniper9.relocate(10000, 0);
                                                bullete9.relocate(10000, 0);
                                        }
                                }
                                if (sniper9.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper9lives--;
                                        if (sniper9lives == 0) {
                                                sniperskilled++;
                                                sniper9.relocate(10000, 0);
                                                bullete9.relocate(10000, 0);
                                        }
                                }
                                if (sniper10.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper10lives--;
                                        if (sniper10lives == 0) {
                                                sniperskilled++;
                                                sniper10.relocate(10000, 0);
                                                bullete10.relocate(10000, 0);
                                        }
                                }
                                if (sniper10.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        sniper10lives--;
                                        if (sniper10lives == 0) {
                                                sniperskilled++;
                                                sniper10.relocate(10000, 0);
                                                bullete10.relocate(10000, 0);
                                        }
                                }
                                //the following code occurs when the first bullet of the shooter hits the boss
                                if (boss.getBoundsInParent().intersects(bulletp1.getBoundsInParent())) {
                                        //We first do the logic to reset the bullet back to the shooter and reset it so that it is not shooting anymore
                                        //and is under the same movement logic
                                        shoot = false;
                                        notshot = true;
                                        bulletp1.setTranslateY(0);
                                        bulletp1.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        //decrement the boss lives
                                        bosslives--;
                                        //the following code checks if when we decrement the bosslives, that the boss is dead or not, if it is
                                        //or boss lives has hit 0, then the following code executes
                                        if (bosslives == 0) {
                                                //set it to the win scene, as the boss has been killed and the player has one
                                                primaryStage.setScene(win);
                                                //add the button to get to the main menu to this win scene
                                                winPane.getChildren().add(MM);
                                                //relocate this button
                                                MM.relocate(525, 700);
                                                //set the text of the label to shot how many tanks that the player killed when he played
                                                tankd1.setText("Killed: " + tankskilled);
                                                //set the font of the label
                                                tankd1.setFont(
                                                Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                                                //set the stule of the label
                                                tankd1.setStyle(
                                                                "-fx-text-fill: White");
                                                //relocate the label
                                                tankd1.relocate(700, 185);

                                                //set the text of the label to shot how many snipers that the player killed when he played
                                                sniperd1.setText("Killed: " + sniperskilled);
                                                //set the font of the label
                                                sniperd1.setFont(
                                                Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));

                                                //set the style of the label
                                                sniperd1.setStyle(
                                                                "-fx-text-fill: White");
                                                                 //set the style of the label
                                                sniperd.setStyle(
                                                        "-fx-text-fill: White");
                                                //relocate the label
                                                sniperd1.relocate(700, 285);
        
                                                //the following resets all the variables in our game so that is starts "fresh" or new again
                                                tankskilled = sniperskilled = 0;
                                                goLeft = goRight = shoot = shoot1 = movingLeft = movingRight =false;
                                                bulletV1 = bulletV2 = bulletV3 = bulletV4 = bulletV5 = bulletV6 = bulletV7 = bulletV8 = bulletV9 = bulletV10 = bulletB1 = false;
                                                bossshot = true;
                                                bulleten1 = 0;
                                                bulleten2 = 1;
                                                bulleten3 = 2;
                                                bulleten4 = 3;
                                                bulleten5 = 4;
                                                bulleten6 = 5;
                                                bulleten7 = 6;
                                                bulleten8 = 7;
                                                bulleten9 = 8;
                                                bulleten10 = 9;
                                                bulletBn1 = 1;
                                                edgeLeft = edgeRight = false;
                                                notshot = true;
                                                notshot1 = true;
                                                bx = 0;
                                                by = 0;
                                                counter = 0;
                                                sniperNumber = 0;
                                                tank1lives = 2;
                                                tank2lives = 2;
                                                tank3lives = 2;
                                                tank4lives = 2;                                                       
                                                tank5lives = 2;
                                                tank6lives = 2;              
                                                tank7lives = 2;
                                                tank8lives = 2;
                                                tank9lives = 2;
                                                tank10lives = 2;          
                                                tank11lives = 2;
                                                sniper1lives = 1;
                                                sniper2lives = 1;
                                                sniper3lives = 1;
                                                sniper4lives = 1;                                     
                                                sniper5lives = 1;
                                                sniper6lives = 1;                
                                                sniper7lives = 1;
                                                sniper8lives = 1;
                                                sniper9lives = 1;
                                                sniper10lives = 1;
                                                bosslives = 10;
                                                playerLives = 3;
                                                
                                                sniper1.relocate(225, 190);
                                                sniper2.relocate(325, 190);
                                                sniper3.relocate(425, 190);
                                                sniper4.relocate(525, 190);
                                                sniper5.relocate(625, 190);
                                                sniper6.relocate(725, 190);
                                                sniper7.relocate(825, 190);
                                                sniper8.relocate(925, 190);
                                                sniper9.relocate(1025, 190);
                                                sniper10.relocate(1125, 190);
                                                tank1.relocate(sniper1.getBoundsInParent().getMinX()-80, 250);
                                                tank2.relocate(tank1.getBoundsInParent().getMinX()+100, 250);
                                                tank3.relocate(tank1.getBoundsInParent().getMinX()+200, 250);
                                                tank4.relocate(tank1.getBoundsInParent().getMinX()+300, 250);
                                                tank5.relocate(tank1.getBoundsInParent().getMinX()+400, 250);
                                                tank6.relocate(tank1.getBoundsInParent().getMinX()+500, 250);
                                                tank7.relocate(tank1.getBoundsInParent().getMinX()+600, 250);
                                                tank8.relocate(tank1.getBoundsInParent().getMinX()+700, 250);
                                                tank9.relocate(tank1.getBoundsInParent().getMinX()+800, 250);
                                                tank10.relocate(tank1.getBoundsInParent().getMinX()+900, 250);
                                                tank11.relocate(tank1.getBoundsInParent().getMinX()+1000, 250);
                                                bullete1.relocate(-1000,-1100);
                                                bullete2.relocate(-1000,-1100);
                                                bullete3.relocate(-1000,-1100);
                                                bullete4.relocate(-1000,-1100);
                                                bullete5.relocate(-1000,-1100);
                                                bullete6.relocate(-1000,-1100);
                                                bullete7.relocate(-1000,-1100);
                                                bullete8.relocate(-1000,-1100);
                                                bullete9.relocate(-1000,-1100);
                                                bullete10.relocate(-1000,-1100);
        
                                                boss.setTranslateX(0);
                                                boss.setTranslateY(0);
                                                boss.relocate(670, 50);
        
                                                bulletb1.setTranslateX(0);
                                                bulletb1.setTranslateY(0);
                                                bulletb1.relocate(700,100);
        
                                                bossFake.setX(710);
                                                bossFake.setY(100);
        
                                                shooter.setX(688);
                                                shooter.setY(700);
        
                                                // 1275,1288
                                                shooterlive1.relocate(1275,100);
                                                bulletlive1.relocate(1288,95);
        
                                                shooterlive2.relocate(1325,100);
                                                bulletlive2.relocate(1338,95);
        
                                                shooterlive3.relocate(1375,100);
                                                bulletlive3.relocate(1388,95);
        
                                                bulletp1.setTranslateX(0);
                                                bulletp1.setTranslateY(0);
                                                bulletp2.setTranslateX(0);
                                                bulletp2.setTranslateY(0);
                                                bulletp1.relocate(715,690);
                                                bulletp2.relocate(715,690);
        
                                                timer.stop();
                                                bulletE1.cancel();
                                                bulletE2.cancel();
                                                bulletE3.cancel();
                                        }
                                }
                                //this code is the same as the one above except it corresponds to the second shooter bullet
                                if (boss.getBoundsInParent().intersects(bulletp2.getBoundsInParent())) {
                                        shoot1 = false;
                                        notshot1 = true;
                                        bulletp2.setTranslateY(0);
                                        bulletp2.relocate(shooter.getBoundsInParent().getMinX() + 27, 690);
                                        bosslives--;
                                        if (bosslives == 0) {
                                                primaryStage.setScene(win);
                                                winPane.getChildren().add(MM);
                                                MM.relocate(525, 700);
                                                tankd1.setText("Killed: " + tankskilled);
                                                tankd1.setFont(
                                                Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                                                tankd1.setStyle(
                                                                "-fx-text-fill: White");
                                                tankd1.relocate(700, 185);
        
                                                sniperd1.setText("Killed: " + sniperskilled);
                                                sniperd1.setFont(
                                                Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                                                sniperd.setStyle(
                                                                "-fx-text-fill: White");
                                                sniperd1.relocate(700, 285);
        
                                                tankskilled = sniperskilled = 0;
                                                goLeft = goRight = shoot = shoot1 = movingLeft = movingRight =false;
                                                bulletV1 = bulletV2 = bulletV3 = bulletV4 = bulletV5 = bulletV6 = bulletV7 = bulletV8 = bulletV9 = bulletV10 = bulletB1 = false;
                                                bossshot = true;
                                                bulleten1 = 0;
                                                bulleten2 = 1;
                                                bulleten3 = 2;
                                                bulleten4 = 3;
                                                bulleten5 = 4;
                                                bulleten6 = 5;
                                                bulleten7 = 6;
                                                bulleten8 = 7;
                                                bulleten9 = 8;
                                                bulleten10 = 9;
                                                bulletBn1 = 1;
                                                edgeLeft = edgeRight = false;
                                                notshot = true;
                                                notshot1 = true;
                                                bx = 0;
                                                by = 0;
                                                counter = 0;
                                                sniperNumber = 0;
                                                tank1lives = 2;
                                                tank2lives = 2;
                                                tank3lives = 2;
                                                tank4lives = 2;                                                       
                                                tank5lives = 2;
                                                tank6lives = 2;              
                                                tank7lives = 2;
                                                tank8lives = 2;
                                                tank9lives = 2;
                                                tank10lives = 2;          
                                                tank11lives = 2;
                                                sniper1lives = 1;
                                                sniper2lives = 1;
                                                sniper3lives = 1;
                                                sniper4lives = 1;                                     
                                                sniper5lives = 1;
                                                sniper6lives = 1;                
                                                sniper7lives = 1;
                                                sniper8lives = 1;
                                                sniper9lives = 1;
                                                sniper10lives = 1;
                                                bosslives = 10;
                                                playerLives = 3;
                                                
                                                sniper1.relocate(225, 190);
                                                sniper2.relocate(325, 190);
                                                sniper3.relocate(425, 190);
                                                sniper4.relocate(525, 190);
                                                sniper5.relocate(625, 190);
                                                sniper6.relocate(725, 190);
                                                sniper7.relocate(825, 190);
                                                sniper8.relocate(925, 190);
                                                sniper9.relocate(1025, 190);
                                                sniper10.relocate(1125, 190);
                                                tank1.relocate(sniper1.getBoundsInParent().getMinX()-80, 250);
                                                tank2.relocate(tank1.getBoundsInParent().getMinX()+100, 250);
                                                tank3.relocate(tank1.getBoundsInParent().getMinX()+200, 250);
                                                tank4.relocate(tank1.getBoundsInParent().getMinX()+300, 250);
                                                tank5.relocate(tank1.getBoundsInParent().getMinX()+400, 250);
                                                tank6.relocate(tank1.getBoundsInParent().getMinX()+500, 250);
                                                tank7.relocate(tank1.getBoundsInParent().getMinX()+600, 250);
                                                tank8.relocate(tank1.getBoundsInParent().getMinX()+700, 250);
                                                tank9.relocate(tank1.getBoundsInParent().getMinX()+800, 250);
                                                tank10.relocate(tank1.getBoundsInParent().getMinX()+900, 250);
                                                tank11.relocate(tank1.getBoundsInParent().getMinX()+1000, 250);
                                                bullete1.relocate(-1000,-1100);
                                                bullete2.relocate(-1000,-1100);
                                                bullete3.relocate(-1000,-1100);
                                                bullete4.relocate(-1000,-1100);
                                                bullete5.relocate(-1000,-1100);
                                                bullete6.relocate(-1000,-1100);
                                                bullete7.relocate(-1000,-1100);
                                                bullete8.relocate(-1000,-1100);
                                                bullete9.relocate(-1000,-1100);
                                                bullete10.relocate(-1000,-1100);
        
                                                boss.setTranslateX(0);
                                                boss.setTranslateY(0);
                                                boss.relocate(670, 50);
        
                                                bulletb1.setTranslateX(0);
                                                bulletb1.setTranslateY(0);
                                                bulletb1.relocate(700,100);
        
                                                bossFake.setX(710);
                                                bossFake.setY(100);
        
                                                shooter.setX(688);
                                                shooter.setY(700);
        
                                                // 1275,1288
                                                shooterlive1.relocate(1275,100);
                                                bulletlive1.relocate(1288,95);
        
                                                shooterlive2.relocate(1325,100);
                                                bulletlive2.relocate(1338,95);
        
                                                shooterlive3.relocate(1375,100);
                                                bulletlive3.relocate(1388,95);
        
                                                bulletp1.setTranslateX(0);
                                                bulletp1.setTranslateY(0);
                                                bulletp2.setTranslateX(0);
                                                bulletp2.setTranslateY(0);
                                                bulletp1.relocate(715,690);
                                                bulletp2.relocate(715,690);
        
                                                timer.stop();
                                                bulletE1.cancel();
                                                bulletE2.cancel();
                                                bulletE3.cancel();
                                        }
                                }
                                //if the boolean to shoot for the enemy is true, then translate the bullet at a speed of 30
                                //the following lines of code are the same for the corresponding sniper and boolean
                                if (bulletV1) {
                                        bullete1.setTranslateY(bullete1.getTranslateY() + 20);
                                }
                                if (bulletV2) {
                                        bullete2.setTranslateY(bullete2.getTranslateY() + 20);
                                }
                                if (bulletV3) {
                                        bullete3.setTranslateY(bullete3.getTranslateY() + 20);
                                }
                                if (bulletV4) {
                                        bullete4.setTranslateY(bullete4.getTranslateY() + 20);
                                }
                                if (bulletV5) {
                                        bullete5.setTranslateY(bullete5.getTranslateY() + 20);
                                }
                                if (bulletV6) {
                                        bullete6.setTranslateY(bullete6.getTranslateY() + 20);
                                }
                                if (bulletV7) {
                                        bullete7.setTranslateY(bullete7.getTranslateY() + 20);
                                }
                                if (bulletV8) {
                                        bullete8.setTranslateY(bullete8.getTranslateY() + 20);
                                }
                                if (bulletV9) {
                                        bullete9.setTranslateY(bullete9.getTranslateY() + 20);
                                }
                                if (bulletV10) {
                                        bullete10.setTranslateY(bullete10.getTranslateY() + 20);
                                }
                                //if the boss bullet boolean is true, fire the boss bullet
                                if (bulletB1) {
                                        bulletb1.setTranslateY(bulletb1.getTranslateY() + 20);
                                }
                                //if the boss bullet goes past 1000 in the y dir., or it doesnt hit anything, then relocate it back to the boss
                                //We use the bossFake as it in a circle, we can't get the x coordinate and need a "dummy" rectangle to track the movement
                                //of the bullet if it didnt shoot
                                //we put the && bosslives so that this relocation only happens when the boss is alive and not when it is "dead"
                                if (bulletb1.getBoundsInParent().getMinY() > 1000 && bosslives != 0) {
                                        bulletb1.setTranslateY(0);
                                        bulletb1.setTranslateX(0);
                                        bulletb1.relocate(bossFake.getX()-10, 100);
                                        bulletB1 = false;
                                        bossshot = true;
                                }
                                //if the snuper bullet goes past 1000 in the y dir. or it doesnt hit anything, then relocate it back to the sniper's position
                                //We use && so that this only happens when the sniper is alive
                                //We use bullete1.getX as the bullet's x does not change and is simply a y translation up back to the sniper
                                //the following lines of code are the same for the corresponding sniper bullet
                                if (bullete1.getBoundsInParent().getMinY() > 1000 && sniper1lives != 0) {
                                        bullete1.setTranslateY(0);
                                        bullete1.relocate(bullete1.getX(), 190);
                                        bulletV1 = false;
                                }
                                if (bullete2.getBoundsInParent().getMinY() > 1000 && sniper2lives != 0) {
                                        bullete2.setTranslateY(0);
                                        bullete2.relocate(bullete2.getX(), 190);
                                        bulletV2 = false;
                                }
                                if (bullete3.getBoundsInParent().getMinY() > 1000 && sniper3lives != 0) {
                                        bullete3.setTranslateY(0);
                                        bullete3.relocate(bullete3.getX(), 190);
                                        bulletV3 = false;
                                }
                                if (bullete4.getBoundsInParent().getMinY() > 1000 && sniper4lives != 0) {
                                        bullete4.setTranslateY(0);
                                        bullete4.relocate(bullete4.getX(), 190);
                                        bulletV4 = false;
                                }
                                if (bullete5.getBoundsInParent().getMinY() > 1000 && sniper5lives != 0) {
                                        bullete5.setTranslateY(0);
                                        bullete5.relocate(bullete5.getX(), 190);
                                        bulletV5 = false;
                                }
                                if (bullete6.getBoundsInParent().getMinY() > 1000 && sniper6lives != 0) {
                                        bullete6.setTranslateY(0);
                                        bullete6.relocate(bullete6.getX(), 190);
                                        bulletV6 = false;
                                }
                                if (bullete7.getBoundsInParent().getMinY() > 1000 && sniper7lives != 0) {
                                        bullete7.setTranslateY(0);
                                        bullete7.relocate(bullete7.getX(), 190);
                                        bulletV7 = false;
                                }
                                if (bullete8.getBoundsInParent().getMinY() > 1000 && sniper8lives != 0) {
                                        bullete8.setTranslateY(0);
                                        bullete8.relocate(bullete8.getX(), 190);
                                        bulletV8 = false;
                                }
                                if (bullete9.getBoundsInParent().getMinY() > 1000 && sniper9lives != 0) {
                                        bullete9.setTranslateY(0);
                                        bullete9.relocate(bullete9.getX(), 190);
                                        bulletV9 = false;
                                }
                                if (bullete10.getBoundsInParent().getMinY() > 1000 && sniper10lives != 0) {
                                        bullete10.setTranslateY(0);
                                        bullete10.relocate(bullete10.getX(), 190);
                                        bulletV10 = false;
                                }
                                //if the enemy sniper bullets hit the shooter, decrement one player life and then relocate it back to the sniper
                                //then make its corresponding boolean that makes it shoot to false
                                //the following lines are just corres
                                if (shooter.getBoundsInParent().intersects(bullete1.getBoundsInParent())) {
                                        playerLives--;
                                        bullete1.setTranslateY(0);
                                        bullete1.relocate(bullete1.getX(), 190);
                                        bulletV1 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete2.getBoundsInParent())) {
                                        playerLives--;
                                        bullete2.setTranslateY(0);
                                        bullete2.relocate(bullete2.getX(), 190);
                                        bulletV2 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete3.getBoundsInParent())) {
                                        playerLives--;
                                        bullete3.setTranslateY(0);
                                        bullete3.relocate(bullete3.getX(), 190);
                                        bulletV3 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete4.getBoundsInParent())) {
                                        playerLives--;
                                        bullete4.setTranslateY(0);
                                        bullete4.relocate(bullete4.getX(), 190);
                                        bulletV4 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete5.getBoundsInParent())) {
                                        playerLives--;
                                        bullete5.setTranslateY(0);
                                        bullete5.relocate(bullete5.getX(), 190);
                                        bulletV5 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete6.getBoundsInParent())) {
                                        playerLives--;
                                        bullete6.setTranslateY(0);
                                        bullete6.relocate(bullete6.getX(), 190);
                                        bulletV6 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete7.getBoundsInParent())) {
                                        playerLives--;
                                        bullete7.setTranslateY(0);
                                        bullete7.relocate(bullete7.getX(), 190);
                                        bulletV7 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete8.getBoundsInParent())) {
                                        playerLives--;
                                        bullete8.setTranslateY(0);
                                        bullete8.relocate(bullete8.getX(), 190);
                                        bulletV8 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete9.getBoundsInParent())) {
                                        playerLives--;
                                        bullete9.setTranslateY(0);
                                        bullete9.relocate(bullete9.getX(), 190);
                                        bulletV9 = false;
                                }
                                if (shooter.getBoundsInParent().intersects(bullete10.getBoundsInParent())) {
                                        playerLives--;
                                        bullete10.setTranslateY(0);
                                        bullete10.relocate(bullete10.getX(), 190);
                                        bulletV10 = false;
                                }
                                //If the boss bullet hits the player, then decrement the player life and relocate the boss bullet back to boss
                                //set bossshot as true again as when it teleports back, it has to have the same movement logic
                                if (shooter.getBoundsInParent().intersects(bulletb1.getBoundsInParent())) {
                                        playerLives--;
                                        bulletb1.setTranslateY(0);
                                        bulletb1.setTranslateX(0);
                                        bulletb1.relocate(bossFake.getX()-10, 100);
                                        bulletB1 = false;
                                        bossshot = true;
                                }

                                //when the player hits 2 life, remove the life counter so that only two player life remains on screen
                                if (playerLives == 2) {
                                        shooterlive1.relocate(100000, 0);
                                        bulletlive1.relocate(100000, 0);
                                }
                                //when the player hits 1 life, remove the life counter so that only one player life remains on screen
                                if (playerLives == 1) {
                                        shooterlive2.relocate(100000, 0);
                                        bulletlive2.relocate(100000, 0);
                                }
                                if (playerLives == 0) {
                                        //When the player dies, we set the scene as the loss scene
                                        primaryStage.setScene(lose);
                                        //This sets the label, as the amount of tanks that the player killed
                                        tankd.setText("Killed: " + tankskilled);
                                        //sets the font of the label
                                        tankd.setFont(
                                        Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                                        //sets the style of the label, ie. the colour
                                        tankd.setStyle(
                                                        "-fx-text-fill: White");
                                        //relocates the label
                                        tankd.relocate(700, 185);

                                        sniperd.setText("Killed: " + sniperskilled);
                                        sniperd.setFont(
                                        Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40));
                                        sniperd.setStyle(
                                                        "-fx-text-fill: White");
                                        sniperd.relocate(700, 285);

                                        //the following code resets everything that was declared previously at the beginning of the code
                                        //this makes a "resetted" game, this is neccessary as these variables change as the game is played
                                        tankskilled = sniperskilled = 0;
                                        goLeft = goRight = shoot = shoot1 = movingLeft = movingRight =false;
                                        bulletV1 = bulletV2 = bulletV3 = bulletV4 = bulletV5 = bulletV6 = bulletV7 = bulletV8 = bulletV9 = bulletV10 = bulletB1 = false;
                                        bossshot = true;
                                        bulleten1 = 0;
                                        bulleten2 = 1;
                                        bulleten3 = 2;
                                        bulleten4 = 3;
                                        bulleten5 = 4;
                                        bulleten6 = 5;
                                        bulleten7 = 6;
                                        bulleten8 = 7;
                                        bulleten9 = 8;
                                        bulleten10 = 9;
                                        bulletBn1 = 1;
                                        edgeLeft = edgeRight = false;
                                        notshot = true;
                                        notshot1 = true;
                                        bx = 0;
                                        by = 0;
                                        counter = 0;
                                        sniperNumber = 0;
                                        tank1lives = 2;
                                        tank2lives = 2;
                                        tank3lives = 2;
                                        tank4lives = 2;                                                       
                                        tank5lives = 2;
                                        tank6lives = 2;              
                                        tank7lives = 2;
                                        tank8lives = 2;
                                        tank9lives = 2;
                                        tank10lives = 2;          
                                        tank11lives = 2;
                                        sniper1lives = 1;
                                        sniper2lives = 1;
                                        sniper3lives = 1;
                                        sniper4lives = 1;                                     
                                        sniper5lives = 1;
                                        sniper6lives = 1;                
                                        sniper7lives = 1;
                                        sniper8lives = 1;
                                        sniper9lives = 1;
                                        sniper10lives = 1;
                                        bosslives = 10;
                                        playerLives = 3;
                                        
                                        sniper1.relocate(225, 190);
                                        sniper2.relocate(325, 190);
                                        sniper3.relocate(425, 190);
                                        sniper4.relocate(525, 190);
                                        sniper5.relocate(625, 190);
                                        sniper6.relocate(725, 190);
                                        sniper7.relocate(825, 190);
                                        sniper8.relocate(925, 190);
                                        sniper9.relocate(1025, 190);
                                        sniper10.relocate(1125, 190);
                                        tank1.relocate(sniper1.getBoundsInParent().getMinX()-80, 250);
                                        tank2.relocate(tank1.getBoundsInParent().getMinX()+100, 250);
                                        tank3.relocate(tank1.getBoundsInParent().getMinX()+200, 250);
                                        tank4.relocate(tank1.getBoundsInParent().getMinX()+300, 250);
                                        tank5.relocate(tank1.getBoundsInParent().getMinX()+400, 250);
                                        tank6.relocate(tank1.getBoundsInParent().getMinX()+500, 250);
                                        tank7.relocate(tank1.getBoundsInParent().getMinX()+600, 250);
                                        tank8.relocate(tank1.getBoundsInParent().getMinX()+700, 250);
                                        tank9.relocate(tank1.getBoundsInParent().getMinX()+800, 250);
                                        tank10.relocate(tank1.getBoundsInParent().getMinX()+900, 250);
                                        tank11.relocate(tank1.getBoundsInParent().getMinX()+1000, 250);
                                        bullete1.relocate(-1000,-1100);
                                        bullete2.relocate(-1000,-1100);
                                        bullete3.relocate(-1000,-1100);
                                        bullete4.relocate(-1000,-1100);
                                        bullete5.relocate(-1000,-1100);
                                        bullete6.relocate(-1000,-1100);
                                        bullete7.relocate(-1000,-1100);
                                        bullete8.relocate(-1000,-1100);
                                        bullete9.relocate(-1000,-1100);
                                        bullete10.relocate(-1000,-1100);

                                        boss.setTranslateX(0);
                                        boss.setTranslateY(0);
                                        boss.relocate(670, 50);

                                        bulletb1.setTranslateX(0);
                                        bulletb1.setTranslateY(0);
                                        bulletb1.relocate(700,100);

                                        bossFake.setX(710);
                                        bossFake.setY(100);

                                        shooter.setX(688);
                                        shooter.setY(700);

                                        // 1275,1288
                                        shooterlive1.relocate(1275,100);
                                        bulletlive1.relocate(1288,95);

                                        shooterlive2.relocate(1325,100);
                                        bulletlive2.relocate(1338,95);

                                        shooterlive3.relocate(1375,100);
                                        bulletlive3.relocate(1388,95);

                                        bulletp1.setTranslateX(0);
                                        bulletp1.setTranslateY(0);
                                        bulletp2.setTranslateX(0);
                                        bulletp2.setTranslateY(0);
                                        bulletp1.relocate(715,690);
                                        bulletp2.relocate(715,690);

                                        timer.stop();
                                        bulletE1.cancel();
                                        bulletE2.cancel();
                                        bulletE3.cancel();
                                }
                        }
                };

        }

        //this launches the args or the code in the JavaFX application and make it start to run
        public static void main(String[] args) {
                launch(args);
        }
}


