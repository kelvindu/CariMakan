
package com.kelvindu.learning.carimakan.model.zomato;


import io.realm.RealmObject;

/**
 * Generated pojo classes from http://www.jsonschema2pojo.org/
 * */
public class User_rating extends RealmObject{

    private String aggregate_rating;
    private String rating_text;
    private String rating_color;
    private String votes;

    public String getAggregate_rating() {
        return aggregate_rating;
    }

    public void setAggregate_rating(String aggregate_rating) {
        this.aggregate_rating = aggregate_rating;
    }

    public String getRating_text() {
        return rating_text;
    }

    public void setRating_text(String rating_text) {
        this.rating_text = rating_text;
    }

    public String getRating_color() {
        return rating_color;
    }

    public void setRating_color(String rating_color) {
        this.rating_color = rating_color;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

}
