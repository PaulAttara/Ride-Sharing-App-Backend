package ca.mcgill.ecse321.passenger;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static String username;
    static String startAddress;
    static String endAddress;


    public static String getUsername() {
        return username;
    }

//testing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Load username
        NavigationView navigationViews = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.navname);
        nav_user.setText(username);

        //Navigate to Route Listings by default
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fMain, new SearchListingsFragment());
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_search_listings);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent RegisterIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(RegisterIntent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_listings) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fMain, new SearchListingsFragment());
            ft.commit();
//        } else if (id == R.id.nav_route_listings) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.fMain, new RouteListingsFragment());
//            ft.commit();
        } else if (id == R.id.nav_my_profile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fMain, new MyProfileFragment());
            ft.commit();
        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //shareIntent.setType("text/plain, image/png");
            shareIntent.setType("text/plain");
            String shareSubject = "Sharefare";
            String shareBody = "Hey, here is one of my ad listings posted on Sharefare, check it out!\n";
            String shareBody2 = "Start address: Montreal" + "\nEnd Address: Toronto" + "\nDate: 2018-12-01";
            //String shareBody2 = "Start address: " + startAddress + "\nEnd Address: " + endAddress;
            shareBody = shareBody + shareBody2;

            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//            shareIntent.putExtra("image_url", R.drawable.ic_menu_gallery);
//            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + C.PROJECT_PATH + "/drawable/" + R.drawable.ic_menu_slideshow);

            startActivity(Intent.createChooser(shareIntent, "Share Using..."));




///////

            String urlToShare = "https://stackoverflow.com/questions/7545254";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
            intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

// See if official Facebook app is found
            boolean facebookAppFound = false;
            List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo info : matches) {
                if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                    intent.setPackage(info.activityInfo.packageName);
                    facebookAppFound = true;
                    break;
                }
            }

// As fallback, launch sharer.php in a browser
            if (!facebookAppFound) {
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
            }

            //startActivity(intent);





        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }}
