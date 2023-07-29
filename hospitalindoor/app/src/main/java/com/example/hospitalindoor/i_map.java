package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class i_map extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spin1,spin2;
    Button view_map;
    //String map[];
    String st_point,end_point;
    ImageView im;

    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_imap);
                        spin1=findViewById(R.id.map1_spinner);
                        spin2=findViewById(R.id.map2_spinner);
                        view_map=findViewById(R.id.viewmap);
                        im=findViewById(R.id.change_map);
                        startpoint();
                        endpoint();
                view_map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String st_point=spin1.getSelectedItem().toString();
                        String end_point=spin2.getSelectedItem().toString();
                     //   Toast.makeText(i_map.this, st_point+""+end_point+" ", Toast.LENGTH_SHORT).show();

                    }
                });
                spin1.setOnItemSelectedListener(this);
                spin2.setOnItemSelectedListener(this);
                    }

                    private void startpoint() {
                  ArrayAdapter<String>  adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.mapvalues));
                  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                  spin1.setAdapter(adapter1);

                    }
                    private void endpoint() {
                        ArrayAdapter<String>  adapter2=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.mapvalues));
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin2.setAdapter(adapter2);
                    }

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String sel1 = "",sel2 = "";
                         sel1=spin1.getSelectedItem().toString();
                         sel2=spin2.getSelectedItem().toString();

                         if(sel1.equals(sel2)){
                             Toast.makeText(i_map.this, "please choose another starting or destination point " ,Toast.LENGTH_SHORT).show();

                         }
                         else if(sel1.equals("OPD") && sel2.equals("EMERGENCY")){
                            Toast.makeText(i_map.this, sel1+sel2, Toast.LENGTH_SHORT).show();

                            im.setImageResource(R.drawable.appointments);
                        }
                        else if(sel1.equals("Labortary") && sel2.equals("X-Ray")){
                            Toast.makeText(i_map.this, sel1+sel2, Toast.LENGTH_SHORT).show();

                            im.setImageResource(R.drawable.appointments);
                        }
                        else if(sel1.equals("OPD") && sel2.equals("Labortary")){
                            Toast.makeText(i_map.this, sel1+sel2, Toast.LENGTH_SHORT).show();

                            im.setImageResource(R.drawable.heartrateico);
                        }
                        else if(sel1.equals("OPD") && sel2.equals("X-Ray")){
                            im.setImageResource(R.drawable.helpline);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }