package co.and.juegodelabotella;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imgBottle;
    private MediaPlayer mediaPlayer;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBottle = findViewById(R.id.imgBottle);
        mediaPlayer = MediaPlayer.create(this,R.raw.soundbotella);

    }

    public void spinBottle(View view) {
        if(!spinning){
            int newDir = random.nextInt(3000);
            float pivotX = imgBottle.getWidth() >> 1;
            float pivotY = imgBottle.getHeight() >> 1;

            Animation rotate = new RotateAnimation(lastDir,newDir,pivotX,pivotY);
            rotate.setDuration(5000);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                    playMusic();

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            imgBottle.startAnimation(rotate);
        }
    }

    private void playMusic () {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
}