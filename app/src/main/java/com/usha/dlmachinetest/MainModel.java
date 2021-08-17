package com.usha.dlmachinetest;

import java.util.List;

public class MainModel {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Meta{
        public int code;
        public String requestId;
    }

    public class Item{
        public int unreadCount;
    }

    public class Notification{
        public String type;
        public Item item;
    }

    public static class Contact{
        public String phone;
        public String formattedPhone;
        public String twitter;
        public String instagram;
        public String facebook;
        public String facebookUsername;
        public String facebookName;

        public Contact(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFormattedPhone() {
            return formattedPhone;
        }

        public void setFormattedPhone(String formattedPhone) {
            this.formattedPhone = formattedPhone;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getFacebookUsername() {
            return facebookUsername;
        }

        public void setFacebookUsername(String facebookUsername) {
            this.facebookUsername = facebookUsername;
        }

        public String getFacebookName() {
            return facebookName;
        }

        public void setFacebookName(String facebookName) {
            this.facebookName = facebookName;
        }
    }

    public class LabeledLatLng{
        public String label;
        public double lat;
        public double lng;
    }

    public static class Location{
        public String address;
        public String crossStreet;
        public double lat;
        public double lng;
        public List<LabeledLatLng> labeledLatLngs;
        public int distance;
        public String postalCode;
        public String cc;
        public String city;
        public String state;
        public String country;
        public List<String> formattedAddress;
        public String neighborhood;

        public Location(int distance, List<String> formattedAddress) {
            this.distance = distance;
            this.formattedAddress = formattedAddress;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCrossStreet() {
            return crossStreet;
        }

        public void setCrossStreet(String crossStreet) {
            this.crossStreet = crossStreet;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public List<LabeledLatLng> getLabeledLatLngs() {
            return labeledLatLngs;
        }

        public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
            this.labeledLatLngs = labeledLatLngs;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public List<String> getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(List<String> formattedAddress) {
            this.formattedAddress = formattedAddress;
        }
    }

    public class Icon{
        public String prefix;
        public String suffix;
    }

    public class Category{
        public String id;
        public String name;
        public String pluralName;
        public String shortName;
        public Icon icon;
        public boolean primary;
    }

    public class Stats{
        public int tipCount;
        public int usersCount;
        public int checkinsCount;
    }

    public class BeenHere{
        public int lastCheckinExpiredAt;
    }

    public class VenuePage{
        public String id;
    }

    public class HereNow{
        public int count;
        public String summary;
        public List<Object> groups;
    }

    public class VenueChain{
        public String id;
    }

    public class Menu{
        public String type;
        public String label;
        public String anchor;
        public String url;
        public String mobileUrl;
        public String externalUrl;
    }

    public static class Venue{
        public int rowId;
        public int isAddressSaved;
        public String id;
        public String name;
        public Contact contact;
        public Location location;
        public List<Category> categories;
        public boolean verified;
        public Stats stats;
        public BeenHere beenHere;
        public VenuePage venuePage;
        public HereNow hereNow;
        public String referralId;
        public List<VenueChain> venueChains;
        public boolean hasPerk;
        public String url;
        public boolean venueRatingBlacklisted;
        public String storeId;
        public Menu menu;
        public boolean allowMenuUrlEdit;
        public boolean hasMenu;

        public Venue(int rowId, String id, String name,
                     Contact contact, Location location, int isAddressSaved) {
            this.rowId = rowId;
            this.id = id;
            this.name = name;
            this.contact = contact;
            this.location = location;
            this.isAddressSaved = isAddressSaved;
        }

        public int getRowId() {
            return rowId;
        }

        public void setRowId(int rowId) {
            this.rowId = rowId;
        }

        public int getIsAddressSaved() {
            return isAddressSaved;
        }

        public void setAddressSaved(int addressSaved) {
            isAddressSaved = addressSaved;
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

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public BeenHere getBeenHere() {
            return beenHere;
        }

        public void setBeenHere(BeenHere beenHere) {
            this.beenHere = beenHere;
        }

        public VenuePage getVenuePage() {
            return venuePage;
        }

        public void setVenuePage(VenuePage venuePage) {
            this.venuePage = venuePage;
        }

        public HereNow getHereNow() {
            return hereNow;
        }

        public void setHereNow(HereNow hereNow) {
            this.hereNow = hereNow;
        }

        public String getReferralId() {
            return referralId;
        }

        public void setReferralId(String referralId) {
            this.referralId = referralId;
        }

        public List<VenueChain> getVenueChains() {
            return venueChains;
        }

        public void setVenueChains(List<VenueChain> venueChains) {
            this.venueChains = venueChains;
        }

        public boolean isHasPerk() {
            return hasPerk;
        }

        public void setHasPerk(boolean hasPerk) {
            this.hasPerk = hasPerk;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isVenueRatingBlacklisted() {
            return venueRatingBlacklisted;
        }

        public void setVenueRatingBlacklisted(boolean venueRatingBlacklisted) {
            this.venueRatingBlacklisted = venueRatingBlacklisted;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public boolean isAllowMenuUrlEdit() {
            return allowMenuUrlEdit;
        }

        public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit) {
            this.allowMenuUrlEdit = allowMenuUrlEdit;
        }

        public boolean isHasMenu() {
            return hasMenu;
        }

        public void setHasMenu(boolean hasMenu) {
            this.hasMenu = hasMenu;
        }
    }

    public class Response{
        public List<Venue> venues;
        public boolean confident;

        public List<Venue> getVenues() {
            return venues;
        }

        public void setVenues(List<Venue> venues) {
            this.venues = venues;
        }
    }

    public class Root{
        public Meta meta;
        public List<Notification> notifications;
        public Response response;

    }


}
