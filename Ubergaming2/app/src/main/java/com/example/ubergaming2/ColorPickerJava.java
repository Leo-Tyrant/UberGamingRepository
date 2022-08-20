package com.example.ubergaming2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import yuku.ambilwarna.AmbilWarnaDialog;
import android.widget.LinearLayout;

public class ColorPickerJava extends AppCompatActivity {

    private TextView gfgTextView;

    private Button mSetColorButton, mPickColorButton;

    private View mColorPreview;

    private int mDefaultColor;

    private int variableColorDeFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker_java);

        gfgTextView = findViewById(R.id.heading);

        mPickColorButton = findViewById(R.id.pick_color_button);
        mSetColorButton = findViewById(R.id.set_color_button);

        mColorPreview = findViewById(R.id.preview_selected_color);

        mDefaultColor = 0;

        mPickColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         openColorPickerDialogue();
                    }
                });

             mSetColorButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          gfgTextView.setTextColor(mDefaultColor);
                    }
                });


        // Cambia el fondo de color
        Button fondo = (Button) findViewById(R.id.buttonBackground);
        fondo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LinearLayout li=(LinearLayout)findViewById(R.id.bgcolor);
                // Prueba estatica con color ROJO
                //  li.setBackgroundColor(Color.RED);


                //Asigna el color previamente seleccionado al fondo
                li.setBackgroundColor(mDefaultColor);

                //guarda en variable el color de fondo escogido
                 variableColorDeFondo = mDefaultColor;


            }
        });


        // Se regresa al inicio
        Button buttonInicio = (Button)findViewById(R.id.buttonInicio);

        buttonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ColorPickerJava.this, ListadoConsolas.class));
            }
        });

    }

    public void openColorPickerDialogue() {
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this, mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                       mDefaultColor = color;
                        mColorPreview.setBackgroundColor(mDefaultColor);
                    }
                });
        colorPickerDialogue.show();
    }


}