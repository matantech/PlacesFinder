
package com.example.matyl.finalproject;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentTransaction;
 import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
 import android.support.v7.widget.Toolbar;
 import android.view.Menu;
 import android.view.MenuItem;

 import com.example.matyl.finalproject.Fragments.FavouritsView;
 import com.example.matyl.finalproject.Fragments.MainView;
 import com.example.matyl.finalproject.Fragments.SettingsView;

 import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    final int mainContainer = R.id.fragmentContainer;
    MainView main = new MainView();
    FavouritsView favourits = new FavouritsView();
    SettingsView settings = new SettingsView();
    Toolbar toolbar;
    FragmentManager manager;
    FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.finalProjectToolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle(R.string.version_number);
        toolbar.inflateMenu(R.menu.menu);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(mainContainer,main);
        transaction.commit();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favourits)
        {

            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(mainContainer,favourits);
            transaction.commit();

        }
        if (id == R.id.action_home)
        {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(mainContainer,main);
            transaction.commit();
        }
        if (id == R.id.action_settings)
        {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.replace(mainContainer,settings);
            transaction.commit();
        }


        return super.onOptionsItemSelected(item);
    }




}
