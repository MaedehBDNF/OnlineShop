public class Address {
    private String province;
    private String city;
    private String street;
    private String alley;
    private String building;
    private int floor;
    private int unit;
    private int no;

    public Address(String province, String city, String street, int no) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.no = no;
    }

    @Override
    public String toString() {
        if (unit % 10 == 1 ) {
            return "Address:" +
                    unit + "st Unit" +
                    floor + "Floor," +
                    "No." + no + " " +
                    street + "Street, " +
                    alley + "alley, " +
                    city.toUpperCase() + ", " +
                    province.toUpperCase() + ".";
        } else if (unit % 10 == 2) {
            return "Address:" +
                    unit + "nd Unit" +
                    floor + "Floor," +
                    "No." + no + " " +
                    street + "Street, " +
                    alley + "alley, " +
                    city.toUpperCase() + ", " +
                    province.toUpperCase() + ".";
        } else if (unit % 10 == 3) {
            return "Address:" +
                    unit + "rd Unit" +
                    floor + "Floor," +
                    "No." + no + " " +
                    street + "Street, " +
                    alley + "alley, " +
                    city.toUpperCase() + ", " +
                    province.toUpperCase() + ".";
        } else {
            return "Address:" +
                    unit + "th Unit" +
                    floor + "Floor," +
                    "No." + no + " " +
                    street + "Street, " +
                    alley + "alley, " +
                    city.toUpperCase() + ", " +
                    province.toUpperCase() + ".";
        }
    }
}
