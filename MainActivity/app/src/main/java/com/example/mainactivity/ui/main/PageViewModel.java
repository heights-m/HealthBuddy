package com.example.mainactivity.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>(); // viewmodel objects are automatically retained
    // livedata is an observable data holder class that is lifecycle aware, when fragment is destroyed observer automatically unsubscribes from the lifecycle
    /* MutableLiveData vs LiveData is to separate the who livedata values and create independence within the ViewModel class
    *
    * */
    /*private LiveData<String> mText = Transformations.map(mIndex, input -> "Hello world from section: " + input);*/

    public void setIndex(int index) {
        mIndex.setValue(index);
    } //gets current tab/fragment

    /*public LiveData<String> getText() {
        return mText;
    }*/
}