
import java.io.*;
import java.util.ArrayList;

public class QuanLyKhachHang {

    private ArrayList<KhachHang> listOfKhachHang = new ArrayList<>();
    private String pathFile = "src\\main\\java\\danhsach_khachhang.txt";

    public QuanLyKhachHang() throws QuanLyException {
        readFile();
    }

    public KhachHang find(String ID) {
        for (KhachHang KHItem
                : this.listOfKhachHang) {
            if (KHItem.getId().equals(ID)) {
                return KHItem;
            }
        }

        return null;
    }

    /*
     *   CAC CHUC NANG
     * */
    //Them 1 khach hang vao trong list
    public boolean add(KhachHang KhachHangAdd) throws QuanLyException {
        if (find(KhachHangAdd.getId()) == null) {
            this.listOfKhachHang.add(KhachHangAdd);
            return true;
        } else {
            throw new QuanLyException("Khách hàng bạn muốn thêm đã có mã trùng với mã là " + KhachHangAdd.getId());
        }
    }

    //Sua thong tin 1 khach hang voi ma truyen vao
    public void editByID(String maKhachHang, String name, String address, String phone) throws QuanLyException {
        //Tim khach hang co trong danh sach
        KhachHang kachHangEdit = find(maKhachHang);

        if (kachHangEdit != null) {
            kachHangEdit.reInformation(name, address, phone);
        } else {//Khi khong tim thay khach hang voi ma truyen vao
            throw new QuanLyException("Không tìm thấy khách hàng có mã la " + maKhachHang);
        }
    }

    //Xoa khach hang voi ma khach hang truyen vao
    public void removeByID(String maKhachHang) throws QuanLyException {
        //Tim khach hang co trong danh sach
        KhachHang kachHangEdit = find(maKhachHang);

        if (kachHangEdit != null) {
            listOfKhachHang.remove(kachHangEdit);
        } else {//Khi khong tim thay khach hang voi ma truyen vao
            throw new QuanLyException("Không tìm thấy khách hàng có mã la " + maKhachHang);
        }
    }

    //Tim danh sach khach hang bang co dien thoai truyen vao
    public ArrayList<KhachHang> findListByPhone(String phone) throws QuanLyException {
        ArrayList<KhachHang> listKhachHang = new ArrayList<>();

        for (KhachHang khachHangFind : this.listOfKhachHang) {
            if (khachHangFind.getPhone().equals(phone)) {
                listKhachHang.add(khachHangFind);
            }
        }

        if (listKhachHang.isEmpty()) {
            throw new QuanLyException("Không tìm thấy khách hàng có số điện thoại " + phone);
        } else {
            return listKhachHang;
        }
    }

    //Lay thong tin tat ca khach hang tu file
    public void readFile() throws QuanLyException {
        try {
            File file = new File(this.pathFile);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String lineTemp;
            while ((lineTemp = br.readLine()) != null) {
                String[] arrInformation = lineTemp.split("#");

                if (find(arrInformation[0]) == null) {
                    this.listOfKhachHang.add(new KhachHang(
                            arrInformation[0],
                            arrInformation[1],
                            arrInformation[2],
                            arrInformation[3]
                    ));
                } else {
                    throw new QuanLyException("Mã của khách hàng " + arrInformation[0] + " đã trùng với ai trước đó");
                }
            }
            br.close();

        } catch (IOException IoEx) {
            throw new QuanLyException("Không thể mở file dữ liệu danhsach_khachhang.txt");
        } catch (Exception ex) {
            throw new QuanLyException("Lỗi ngoài ý muốn Quản Lý: " + ex.getMessage());
        }
    }

    //Ghi lai tat ca thong tin cua khach hang vao file
    public void saveFile() throws QuanLyException {
        try {
            File file = new File(this.pathFile);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            String srtFormatToWrite;
            for (KhachHang item : this.listOfKhachHang) {
                srtFormatToWrite = String.format("%s#%s#%s#%s%n", item.getId(), item.getName(), item.getAddress(), item.getPhone());

                bw.write(srtFormatToWrite);
            }

            bw.close();
        } catch (IOException ioEx) {
            throw new QuanLyException("Không thể mở file dữ liệu của khách hàng");
        } catch (Exception ex) {
            throw new QuanLyException("Lỗi ngoại lệ: " + ex.getMessage());
        }
    }

    /*
     *   GET
     * */
    public ArrayList<KhachHang> getListOfKhachHang() {
        return listOfKhachHang;
    }
}
