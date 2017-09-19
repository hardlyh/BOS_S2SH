package lyh.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域
 */

public class Customer implements java.io.Serializable {

    private Integer id;
    private String name;
    private String station;
    private String telephone;
    private String address;
    private Decidedzone decidedzone;
    
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Customer(Integer id, String name, String station, String telephone, String address,
            Decidedzone decidedzone) {
        super();
        this.id = id;
        this.name = name;
        this.station = station;
        this.telephone = telephone;
        this.address = address;
        this.decidedzone = decidedzone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Decidedzone getDecidedzone() {
        return decidedzone;
    }

    public void setDecidedzone(Decidedzone decidedzone) {
        this.decidedzone = decidedzone;
    }

}