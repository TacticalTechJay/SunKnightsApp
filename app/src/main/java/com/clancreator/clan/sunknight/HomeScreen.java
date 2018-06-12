package com.clancreator.clan.sunknight;

import android.content.Intent;
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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DrawerLayout mDrawerLayout;
    private TextView mDisplayName;
    private TextView mEmailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        mDrawerLayout = findViewById(R.id.dl);

        mEmailView = findViewById(R.id.EmailText);
        mDisplayName = findViewById(R.id.Username);

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

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(final FirebaseUser user) {
        if (user != null) {
            mDisplayName.setText(getString(R.string.userstatus, user.getDisplayName()));
            mEmailView.setText(getString(R.string.emailstatus, user.getEmail(), user.isEmailVerified()));
        } else {
            TextView email = findViewById(R.id.EmailText);
            email.setText(R.string.not_signed_in);
            TextView username = findViewById(R.id.Username);
            username.setText(null);
            Toast.makeText(this, "Looks like you are signed out!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.action_signin) {
            Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
            startActivity(intent);
        }
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
