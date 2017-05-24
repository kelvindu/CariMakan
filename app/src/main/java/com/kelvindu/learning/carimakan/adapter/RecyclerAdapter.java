package com.kelvindu.learning.carimakan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * This recycler view adapter class is an abstract class. so it can be used dynamically.
 * Consider it as a boilerplate code since it covers most uses of the recyclerview adapters.
 *
 * */

public abstract class RecyclerAdapter<T extends RealmModel, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected int mLayout;
    Class<VH> mViewHolderClass;
    Class<T> mModelClass;
    RealmList<T> mData;

    /**
     * Constructor for initialize adapter.
     *
     * @param mLayout: is the id of the layout that will be used to generate item list in the view.
     * @param mViewHolderClass generic class for view holder
     * @param mModelClass the generic model class that is used as the model object
     * @param mData the List / ArrayList of the data objects.
     * */
    public RecyclerAdapter(int mLayout, Class<VH> mViewHolderClass, Class<T> mModelClass, RealmList<T> mData) {
        this.mLayout = mLayout;
        this.mViewHolderClass = mViewHolderClass;
        this.mModelClass = mModelClass;
        this.mData = mData;
    }

    /**
     * the onCreate abstract for normal onCreate recycler view adapters/
     * */
    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(mLayout, parent, false);
        try {
            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * The abstract method that binds the model into the view, users don't access this method directly,
     * they accessed it through abstract class called bindView that is passed down to it's child adapters.
     *
     * @param holder is the view holder which user used to bind the views.
     * @param position is the current index of the model view, can be used as array index.
     * */
    @Override public void onBindViewHolder(VH holder, int position) {
        T model = (T) getItem(position);
        bindView(holder, model, position);
    }

    /**
     * Return total items that has been bind successfully to the view.
     *
     * */
    @Override public int getItemCount() {
        return mData.size();
    }

    /**
     * The abstract method that takes generic classes and can be implemented in the view.
     *
     * @param holder the view of a new layout that will be generated soon.
     * @param model the model / data that is going to be bind to the view
     * @param position the position of the current item in the item holder.
     * */
    abstract protected void bindView(VH holder, T model, int position);

    //This method returns the items value at the given position.
    private RealmModel getItem(int position) {
        return mData.get(position);
    }
}
