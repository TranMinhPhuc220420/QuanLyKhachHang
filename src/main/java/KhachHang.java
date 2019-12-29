
public class KhachHang {

    private String id, name, address, phone;

    public KhachHang(String id, String name, String address, String phone) throws KhachHangException {
        //Kiem tra va ghi nhan du lieu cua khach hang khi hop le
        if (id.equals("")) {
            throw new KhachHangException("Mã khách hàng không thể trống");
        } else {
            if (name.equals("")) {
                throw new KhachHangException("Tên khách hàng không thể trống");
            } else {
                if (address.equals("")) {
                    throw new KhachHangException("Địa chỉ khách hàng không thể thiếu");
                } else {
                    if (phone.equals("")) {
                        throw new KhachHangException("Số điện thoại của khách hàng không thể thiếu");
                    } else {

                        //Nhan thong tin khach hang khi tat ca thong tin da hop le
                        this.id = id;
                        this.name = name;
                        this.address = address;
                        this.phone = phone;
                    }
                }
            }
        }
    }

    /*
    *   GET
    * */
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }


    /*
     *   SET
     * */
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Method
    public void reInformation(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
