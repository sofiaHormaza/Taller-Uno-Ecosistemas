package com.example.tallerunocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ComunicacionTCP comm;
    private Button derecha;
    private Button izquierda;
    private Button disparar;
    boolean isRight=true;
    boolean isLeft=true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        derecha=findViewById(R.id.derecha);
        izquierda=findViewById(R.id.izquierda);
        disparar=findViewById(R.id.disparar);

        comm=new ComunicacionTCP(this);
        comm.solicitarConexion();

        derecha.setOnTouchListener(
                (v,event)->{
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            isRight=false;
                            break;
                        case MotionEvent.ACTION_UP:
                            isRight=true;
                            break;
                    }
                return true;
                }
        );

        izquierda.setOnTouchListener(
                (v,event)->{
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            isLeft=false;
                            break;
                        case MotionEvent.ACTION_UP:
                            isLeft=true;
                            break;
                    }
                    return  true;
                }
        );

        disparar.setOnClickListener(
                (v)->{
                    comm.mandarMensaje("DISP\n");
                }
        );

        new Thread(
                ()->{
                    while(true){
                        while(isRight){ }
                        try {
                            Thread.sleep(30);
                            comm.mandarMensaje("RIGHT\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

        ).start();

        new Thread(
                ()->{
                    while(true){
                        while(isLeft){ }
                        try {
                            Thread.sleep(30);
                            comm.mandarMensaje("LEFT\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

        ).start();

    }
}
