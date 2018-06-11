package com.clancreator.clan.sunknight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        mDrawerLayout = findViewById(R.id.dl);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_at_home);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(FirebaseUser user) {

        if (user == null) {
            Intent beginRegister = new Intent(this, LoginActivity.class);
            Toast.makeText(this, "Register or Login", Toast.LENGTH_SHORT).show();
            TextView Email = findViewById(R.id.EmailText);
            Email.setText(null);
            TextView Username = findViewById(R.id.Username);
            Username.setText(R.string.not_signedIn);
        }

        if (user != null && user.isEmailVerified()) {
            TextView Email = findViewById(R.id.EmailText);
            Email.setText(String.format("Email: %s", user.getEmail()));
            TextView Username = findViewById(R.id.Username);
            Username.setText(String.format("You are Signed in, %s", user.getDisplayName()));
        } else {
            TextView Email = findViewById(R.id.EmailText);
            Email.setText(R.string.Not_Verified_ET);
            TextView Username = findViewById(R.id.Username);
            Username.setText(R.string.Not_Verified_UN);
        }
    }
*/

}
