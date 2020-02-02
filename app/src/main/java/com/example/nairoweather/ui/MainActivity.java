package com.example.nairoweather.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nairoweather.R;
import com.example.nairoweather.api.Api;
import com.example.nairoweather.fragments.DatePickerFragment;
import com.example.nairoweather.models.Currently;
import com.example.nairoweather.models.GetNairoWeatherInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener{

    public TextView temperatureLabel,locationLabel,humidityValue,precipValue,summaryLabel;
    public ImageView iconImageView,mRefreshImageView;
    public ProgressBar mProgressBar;
    public String forecastUrl = "https://api.darksky.net/forecast/";
    public String apiKey = "b00e0bcd6f9e290d446d392c8adc01cf";
    public String mIcon;
    public Double temp;
    public Button btnWeather;
    public EditText selectWeatherDate;

    public static final String TAG = "Main Activity";

   public double latitude = 1.2921;
   public double longitude = 36.8219;

    // Retrofit
    public Retrofit retrofit;
    public Call<GetNairoWeatherInfo> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        temperatureLabel = findViewById(R.id.temperatureLabel);
        locationLabel = findViewById(R.id.locationLabel);
        humidityValue = findViewById(R.id.humidityValue);
        precipValue = findViewById(R.id.precipValue);
        summaryLabel = findViewById(R.id.summaryLabel);
        iconImageView = findViewById(R.id.iconImageView);
        mRefreshImageView = findViewById(R.id.refreshImageView);
        btnWeather = findViewById(R.id.btnWeather);
        mProgressBar = findViewById(R.id.progressBar);
        selectWeatherDate = findViewById(R.id.selectWeatherDate);
        selectWeatherDate.setInputType(InputType.TYPE_NULL);

        selectWeatherDate.setOnClickListener(v -> {

            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(),"datePicker");
        });

        mProgressBar.setVisibility(View.VISIBLE);

        getCurrentWeather();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = dateFormatter.format(c.getTime());
        selectWeatherDate.setText(str);

    }

    public void getCurrentWeather(){

        if (isNetworkAvailable()){
            toggleRefresh();
            retrofit = new Retrofit.Builder().baseUrl(forecastUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Api service= retrofit.create(Api.class);
            try {
                call = service.getCurrentWeather(apiKey+"/"+latitude+","+longitude);
                call.enqueue(new Callback<GetNairoWeatherInfo>() {
                    @Override
                    public void onResponse(Call<GetNairoWeatherInfo> call, Response<GetNairoWeatherInfo> response) {

                        try {
                            if (response.isSuccessful()){
                                temp = response.body().getCurrently().getTemperature();
                                    int tem = (int) Math.round(temp) ;

                                    temperatureLabel.setText(tem+"");
                                    locationLabel.setText(response.body().getTimezone());
                                    humidityValue.setText(response.body().getCurrently().getHumidity()+"");
                                    precipValue.setText(response.body().getCurrently().getPrecipIntensity()+"");
                                    summaryLabel.setText(response.body().getCurrently().getSummary());
                                    mIcon = response.body().getCurrently().getIcon();

                                    int iconId = R.drawable.clear_day;

                                if (mIcon.equals("clear-day")) {
                                    iconId = R.drawable.clear_day;
                                }
                                else if (mIcon.equals("clear-night")) {
                                    iconId = R.drawable.clear_night;
                                }
                                else if (mIcon.equals("rain")) {
                                    iconId = R.drawable.rain;
                                }
                                else if (mIcon.equals("snow")) {
                                    iconId = R.drawable.snow;
                                }
                                else if (mIcon.equals("sleet")) {
                                    iconId = R.drawable.sleet;
                                }
                                else if (mIcon.equals("wind")) {
                                    iconId = R.drawable.wind;
                                }
                                else if (mIcon.equals("fog")) {
                                    iconId = R.drawable.fog;
                                }
                                else if (mIcon.equals("cloudy")) {
                                    iconId = R.drawable.cloudy;
                                }
                                else if (mIcon.equals("partly-cloudy-day")) {
                                    iconId = R.drawable.partly_cloudy;
                                }
                                else if (mIcon.equals("partly-cloudy-night")) {
                                    iconId = R.drawable.cloudy_night;
                                }

                                       Drawable drawable = getResources().getDrawable(iconId);
                                        iconImageView.setImageDrawable(drawable);




                            } else{

                                alertUserAboutError();
                            }
                        }
                        catch (Exception e) {
                            Log.e(TAG,"Exception error caught: ",e);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetNairoWeatherInfo> call, Throwable t) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toggleRefresh();
                            }
                        });
                        toggleRefresh();
                        alertUserAboutError();


                    }
                });
            }catch (NullPointerException e){
                Log.w(TAG,"Null Pointer exception API");
            }


        }else {
            Toast.makeText(this,"Network not Available",
                    Toast.LENGTH_LONG).show();
        }

    }


    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE){
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");

    }


}
