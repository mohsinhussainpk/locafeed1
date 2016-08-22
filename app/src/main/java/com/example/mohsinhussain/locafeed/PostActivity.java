package com.example.mohsinhussain.locafeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class PostActivity extends AppCompatActivity {

    Spinner  spinner;
    EditText title_text,description_text;
    String title,description,category, category1, location;

    public void setLocationPost(String string){
        location = string;







    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        addItemsToSpinner();
        addListenerToSpinner();

    }

    public void addListenerToSpinner() {

        spinner =(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
               category1 = gettingTheCategory(itemSelectedInSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String gettingTheCategory(String itemSelectedInSpinner) {

        category = itemSelectedInSpinner;
        return category;
    }


    public void addItemsToSpinner(){

        spinner =(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories,android.R.layout.simple_spinner_item);
               spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
         spinner.setAdapter(spinnerAdapter);
    }
    public void userReg(View v)
    {

        title_text = (EditText) findViewById(R.id.titleText);
        description_text = (EditText) findViewById(R.id.descriptionText);

        title = title_text.getText().toString();
        description = description_text.getText().toString();
        String method = "register";

        //com.mysampleapp.BackgroundTask backgroundTask = new com.mysampleapp.BackgroundTask(this);
        com.example.mohsinhussain.locafeed.BackgroundTask backgroundTask = new com.example.mohsinhussain.locafeed.BackgroundTask(this);
      backgroundTask.execute(method,title,description,category1);

        finish();

        //startActivity(new Intent(this,Register.class));

    }

}
