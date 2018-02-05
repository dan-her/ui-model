package navigator.lakelandnavigatoruimodel;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap gator = ((BitmapDrawable)getDrawable(R.drawable.alligator)).getBitmap();
        //replace any reference to an alligator with one to the map
        Point size = new Point();
        Scroll scr = new Scroll();
        scr.scrolling();
        int screenW = size.x;
        int screenH = size.y;
        final int maxXx = ((gator.getWidth() / 2) - (screenW / 2));
        final int maxY = ((gator.getHeight() / 2) - (screenH / 2));
        final int maxLeft = (maxXx * -1);
        final int maxTop = (maxY * -1);
        final ImageView gatre = this.findViewById(R.id.gatorView);
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

                        // scrolling to right side of image (pic moving to the left)
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

                        // scrolling to top of image (pic moving to the bottom)
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

                        // scrolling to bottom of image (pic moving to the top)
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}