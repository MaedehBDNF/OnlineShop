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

    public Address(String province, String city, String street, String alley, int no) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.alley = alley;
        this.no = no;
    }

    // if address is address of an apartment depending on arguments this constructor will be called
    public Address(String province, String city, String street, String alley, String building, int floor, int unit, int no) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.alley = alley;
        this.building = building;
        this.floor = floor;
        this.unit = unit;
        this.no = no;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean equals(Address address){
        if (this.province.equalsIgnoreCase(address.getProvince())){
            if (this.city.equalsIgnoreCase(address.getCity())){
                if (this.street.equalsIgnoreCase(address.getStreet())){
                    if (this.alley.equalsIgnoreCase(address.getAlley())){
                        if (this.building.equalsIgnoreCase(address.getBuilding())){
                            if (this.floor == address.getFloor()){
                                if (this.unit == address.getUnit()){
                                    return this.no == address.getNo();
                                } else { return false; }
                            } else { return false; }
                        } else { return false; }
                    } else { return false; }
                } else { return false; }
            } else { return false; }
        } else { return false; }
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
