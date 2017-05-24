package com.kelvindu.learning.carimakan.presenter.search;

import com.kelvindu.learning.carimakan.model.search.SearchModelImp;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * The presenter interactor for SearchPresenter.
 * all of the methods that communicating with another component defined here.
 *
 * Created by KelvinDu on 5/23/2017.
 */

public interface SearchPresenter {
    Observable<SearchModelImp> getResult(String query);
    void realmTransaction(SearchModelImp res);
}
