package steelhacks.migrainebuddy.com.migraine;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



/*
By Jason N and Alex S
02-20-2016
 */

public class Migraine extends AppCompatActivity {
int count=0;
    private SeekBar seekBar;
    private TextView textView;

    ArrayList<MigraineQuestions> quoteList = new ArrayList<MigraineQuestions>();

    View dialogView;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migraine);

       initializeVariables();

        seekBar = (SeekBar) findViewById(R.id.seekBar1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 0;


            @Override

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                progress = progresValue;
            }
            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getApplicationContext(), ""+progress, Toast.LENGTH_SHORT).cancel();


            }


            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {

                textView.setText("Level/Hours: " + progress + "/" + seekBar.getMax());

                Toast.makeText(getApplicationContext(),""+ progress, Toast.LENGTH_SHORT).cancel();

            }
        });

        seekBar.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);


        RelativeLayout touch = (RelativeLayout) findViewById(R.id.touch);
        final TextView quoteText = (TextView) findViewById(R.id.quote);
        final TextView personText = (TextView) findViewById(R.id.person);



        MigraineQuestions quote0 = new MigraineQuestions("Level of pain in your head?", 0);
        quoteList.add(quote0);

        MigraineQuestions quote1 = new MigraineQuestions("Level of stress?", 0);
        quoteList.add(quote1);

        MigraineQuestions quote2 = new MigraineQuestions("Level of noise where you currently are?", 0);
        quoteList.add(quote2);

        MigraineQuestions quote3 = new MigraineQuestions("Level of brightness where you currently are?", 0);
        quoteList.add(quote3);

        MigraineQuestions quote4 = new MigraineQuestions("Hours of exercise in the last 24 hours?", 0);
        quoteList.add(quote4);

        MigraineQuestions quote5 = new MigraineQuestions("Hours of sleep in the last 24 hours?", 0);
        quoteList.add(quote5);

        MigraineQuestions quote6 = new MigraineQuestions("What day and time did the migraine START?", 0);
        quoteList.add(quote6);

        MigraineQuestions quote7 = new MigraineQuestions("", 0);
        quoteList.add(quote7);

        MigraineQuestions quote8 = new MigraineQuestions("How long was your migraine?", 0);
        quoteList.add(quote8);


        MigraineQuestions quote9 = new MigraineQuestions("Migraine Review:", 0);
        quoteList.add(quote9);


        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (count < quoteList.size()) {

                 MigraineQuestions q = quoteList.get(count);

                    quoteText.setText(q.getQue());



                    count = count + 1;
                    if((count > -1 && count <7) || count==9) {

                        seekBar.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        quoteList.get(count - 1).setAns(seekBar.getProgress());
                    }
                   else {
                     seekBar.setVisibility(View.INVISIBLE);
                        textView.setVisibility(View.INVISIBLE);
                    }
                    if (count == 5) seekBar.setMax(12);
                    else if(count==8 ){

                        dialogView = View.inflate(Migraine.this, R.layout.date_time_picker, null);
                        alertDialog = new AlertDialog.Builder(Migraine.this).create();

                        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                                        datePicker.getMonth(),
                                        datePicker.getDayOfMonth(),
                                        timePicker.getCurrentHour(),
                                        timePicker.getCurrentMinute());


                                long time = calendar.getTimeInMillis();
                                alertDialog.dismiss();
                            }});
                        alertDialog.setView(dialogView);
                        alertDialog.show();
                    }
                    else if(count==9) {
                        seekBar.setMax(96);
                        quoteList.get(count-1).setAns(seekBar.getProgress());


                        //seekBar.setProgress(0);
                    }
                    else if(count ==10){
                        int migraineSumIndicator=0;
                        for(int i=0;i<4;i++) {
                            migraineSumIndicator+=quoteList.get(i).getAns();
                        }
                        if(migraineSumIndicator >= 20 || quoteList.get(8).getAns() >=4 && quoteList.get(8).getAns() <=72) {

                            MigraineQuestions quote11 = new MigraineQuestions("You have a migrane. Take some R&R:", 0);
                            quoteList.add(quote11);
//Has a migraine!
                        }
                        else if(quoteList.get(8).getAns() > 72 || migraineSumIndicator > 20) {
                            //go to the ER!!
                            MigraineQuestions quote11 = new MigraineQuestions("Migrane!!! Seek medical help!!!!", 0);
                            quoteList.add(quote11);
                        }
                        else if(migraineSumIndicator < 20 || quoteList.get(8).getAns() < 4) {
                            //pop an advil or something

                            MigraineQuestions quote11 = new MigraineQuestions("Not a Migrane. But try an advil:( ", 0);
                            quoteList.add(quote11);

                        }


                    }
                    else seekBar.setMax(10);


                   // quoteList.get(count - 1).setAns(seekBar.getProgress());

                    seekBar.setProgress(0);


                    textView.setText("Level/Hours: " + seekBar.getProgress() + "/" + seekBar.getMax());
                }




            }
        });
    }

    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_migraine, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
