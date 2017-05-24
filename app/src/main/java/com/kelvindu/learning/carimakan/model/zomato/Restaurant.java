
package com.kelvindu.learning.carimakan.model.zomato;

import io.realm.RealmObject;

/**
 * Generated pojo classes from http://www.jsonschema2pojo.org/
 * */
public class Restaurant extends RealmObject {

    private R r;
    private String apikey;
    private String id;
    private String name;
    private String url;
    private Location_ location;
    private Integer switch_to_order_menu;
    private String cuisines;
    private Integer average_cost_for_two;
    private Integer price_range;
    private String currency;
    private String thumb;
    private User_rating user_rating;
    private String photos_url;
    private String menu_url;
    private String featured_image;
    private Integer has_online_delivery;
    private Integer is_delivering_now;
    private String deeplink;
    private Integer has_table_booking;
    private String events_url;

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location_ getLocation() {
        return location;
    }

    public void setLocation(Location_ location) {
        this.location = location;
    }

    public Integer getSwitch_to_order_menu() {
        return switch_to_order_menu;
    }

    public void setSwitch_to_order_menu(Integer switch_to_order_menu) {
        this.switch_to_order_menu = switch_to_order_menu;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public Integer getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public void setAverage_cost_for_two(Integer average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public Integer getPrice_range() {
        return price_range;
    }

    public void setPrice_range(Integer price_range) {
        this.price_range = price_range;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public User_rating getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(User_rating user_rating) {
        this.user_rating = user_rating;
    }

    public String getPhotos_url() {
        return photos_url;
    }

    public void setPhotos_url(String photos_url) {
        this.photos_url = photos_url;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public Integer getHas_online_delivery() {
        return has_online_delivery;
    }

    public void setHas_online_delivery(Integer has_online_delivery) {
        this.has_online_delivery = has_online_delivery;
    }

    public Integer getIs_delivering_now() {
        return is_delivering_now;
    }

    public void setIs_delivering_now(Integer is_delivering_now) {
        this.is_delivering_now = is_delivering_now;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public Integer getHas_table_booking() {
        return has_table_booking;
    }

    public void setHas_table_booking(Integer has_table_booking) {
        this.has_table_booking = has_table_booking;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

}
