package co.kit.gfg.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize the Bottom Navigation View */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        /* Nav controller */
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}