package com.example.lab4;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainIntentService extends IntentService {

    public MainIntentService(){
        super("MainIntentService");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onHandleIntent(@Nullable Intent intent){
        int number = intent.getIntExtra("number", 0);
        List<Integer> numbers = getListOfNumbers(number);
        int amount = numbers.size();
        StringBuilder stringBuilder = convertNumbers(numbers);

        Intent intentNew = new Intent("MainActivity");
        intentNew.putExtra("amount", amount);
        intentNew.putExtra("prime",stringBuilder.toString() );
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentNew);
    }

    private List<Integer> getListOfNumbers(int number){
        List<Integer> numbers = new ArrayList<>();
        for (int i=0; i<=number; i++){
            if(isPrime(i)){
                numbers.add(i);
            }
        }
        return numbers;
    }

    private StringBuilder convertNumbers(List<Integer> numbers){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<numbers.size();i++){
            stringBuilder.append(numbers.get(i)).append(", ");
        }
        return stringBuilder;
    }

    public boolean isPrime(int n){
        if (n<2){
            return false;
        }
        for(int i=2;i<n;i++){
            if (n%i==0){
                return false;
            }
        }
        return true;
    }
}
