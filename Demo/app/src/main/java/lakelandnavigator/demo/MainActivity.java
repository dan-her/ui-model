package lakelandnavigator.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements MagnetListener{
    Bitmap map;
    ImageView img;
    static ArrayList<navigator.lakelandcc.Node> pixels;
    static int width, height;
    EditText start;
    EditText end;
    Button find;
    TextView test;
    IALocationManager mana;
    IAFloorPlan currentFP;
    IARegion region;
    int counter = 1;
    IAResourceManager resourceManager;
    PointF loc;
    IATask res;
    Point oldPoint = new Point(0,0);
    int oldColor = Color.WHITE;
    navigator.lakelandcc.Node location;
    IALocation ialoc;
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);
        img = findViewById(R.id.building);
        location = new navigator.lakelandcc.Node(false, new Point(0,0));
        map = ((BitmapDrawable)getDrawable(R.drawable.tbuilding3)).getBitmap();
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        find = findViewById(R.id.find);
        resourceManager = IAResourceManager.create(this);
        width = map.getWidth();
        height = map.getHeight();
        map = map.copy(Bitmap.Config.ARGB_8888,true);
        long firstTime = Calendar.getInstance().getTimeInMillis();
        pixels = makeGridS(map);
        find.setText("Load Time: " + Long.toString((firstTime-Calendar.getInstance().getTimeInMillis())*-1));
        img.setImageBitmap(map);
        //replace any reference to an alligator with one to the map
        Bitmap gator = ((BitmapDrawable)getDrawable(R.drawable.tbuilding)).getBitmap();
        Point size = new Point();
        int screenW = size.x;
        int screenH = size.y;
        final int maxXx = ((gator.getWidth() / 2) - (screenW / 2));
        final int maxY = ((gator.getHeight() / 2) - (screenH / 2));
        final int maxLeft = (maxXx * -1);
        final int maxTop = (maxY * -1);
        final ImageView gatre = this.findViewById(R.id.Tbld);
        gatre.setOnTouchListener(new View.OnTouchListener() {
            float downX, downY;
            int totalX, totalY;
            int scrollByX, scrollByY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float curX, curY;
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX();
                        downY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = motionEvent.getX();
                        curY = motionEvent.getY();
                        scrollByX = (int)(downX - curX);
                        scrollByY = (int)(downY - curY);

                        if (curX > downX)
                        {
                            if (totalX == maxLeft)
                            {
                                scrollByX = 0;
                            }
                            if (totalX > maxLeft)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX < maxLeft)
                            {
                                scrollByX = maxLeft - (totalX - scrollByX);
                                totalX = maxLeft;
                            }
                        }


                        if (curX < downX)
                        {
                            if (totalX == maxXx)
                            {
                                scrollByX = 0;
                            }
                            if (totalX < maxXx)
                            {
                                totalX = totalX + scrollByX;
                            }
                            if (totalX > maxXx)
                            {
                                scrollByX = maxXx - (totalX - scrollByX);
                                totalX = maxXx;
                            }
                        }


                        if (curY > downY)
                        {
                            if (totalY == maxTop)
                            {
                                scrollByY = 0;
                            }
                            if (totalY > maxTop)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY < maxTop)
                            {
                                scrollByY = maxTop - (totalY - scrollByY);
                                totalY = maxTop;
                            }
                        }


                        if (curY < downY)
                        {
                            if (totalY == maxY)
                            {
                                scrollByY = 0;
                            }
                            if (totalY < maxY)
                            {
                                totalY = totalY + scrollByY;
                            }
                            if (totalY > maxY)
                            {
                                scrollByY = maxY - (totalY - scrollByY);
                                totalY = maxY;
                            }
                        }

                        gatre.scrollBy(scrollByX, scrollByY);
                        downX = curX;
                        downY = curY;
                        break;

                }


                return true;
            }
        });
    }
}
