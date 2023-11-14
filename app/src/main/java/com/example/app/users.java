package com.example.app;

public class users {

        String name, contact, apart, street, landmark;

        public users() {
        }

        public users(String name, String contact, String apart, String street, String landmark) {
            this.name = name;
            this.contact = contact;
            this.apart = apart;
            this.street = street;
            this.landmark = landmark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getApart() {
            return apart;
        }

        public void setApart(String apart) {
            this.apart = apart;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }


}
