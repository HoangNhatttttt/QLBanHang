-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2025 at 05:03 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thietbidientu`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('1', '1', 2, 25000000.00, 50000000.00),
('1', '2', 1, 40000000.00, 40000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `MaCTKM` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `PhanTramGiamGia` decimal(5,2) DEFAULT NULL CHECK (`PhanTramGiamGia` >= 0 and `PhanTramGiamGia` <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`MaCTKM`, `MaSP`, `PhanTramGiamGia`) VALUES
('KM1', '1', 10.00),
('KM1', '2', 15.00),
('KM1', '3', 10.00),
('KM1', '4', 20.00),
('KM1', '5', 15.00),
('KM2', '10', 50.00),
('KM2', '6', 50.00),
('KM2', '7', 50.00),
('KM2', '8', 50.00),
('KM2', '9', 50.00),
('KM3', '11', 30.00),
('KM3', '12', 30.00),
('KM3', '13', 30.00),
('KM4', '14', 25.00),
('KM4', '15', 25.00),
('KM4', '16', 25.00),
('KM4', '17', 25.00),
('KM5', '18', 40.00),
('KM5', '19', 40.00),
('KM5', '20', 40.00),
('KM5', '21', 40.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPN` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
('PN1', '1', 2, 3000.00, 6000.00),
('PN10', '2', 3, 11000000.00, 33000000.00),
('PN2', '1', 1, 25000000.00, 25000000.00),
('PN2', '2', 1, 40000000.00, 40000000.00),
('PN3', '1', 3, 25000000.00, 75000000.00),
('PN3', '2', 1, 40000000.00, 40000000.00),
('PN4', '1', 1, 25000000.00, 25000000.00),
('PN5', '2', 1, 40000000.00, 40000000.00),
('PN6', '1', 1, 25000000.00, 25000000.00),
('PN7', '2', 1, 40000000.00, 40000000.00),
('PN8', '1', 1, 25000000.00, 25000000.00),
('PN9', '1', 1, 25000000.00, 25000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `chtrinhkhuyenmai`
--

CREATE TABLE `chtrinhkhuyenmai` (
  `MaCTKM` varchar(10) NOT NULL,
  `TenKhuyenMai` varchar(255) NOT NULL,
  `NgayBatDau` date DEFAULT NULL,
  `NgayKetThuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chtrinhkhuyenmai`
--

INSERT INTO `chtrinhkhuyenmai` (`MaCTKM`, `TenKhuyenMai`, `NgayBatDau`, `NgayKetThuc`) VALUES
('KM1', 'Giảm giá hè sôi động', '2025-06-01', '2025-06-15'),
('KM2', 'Mua 1 tặng 1', '2025-07-01', '2025-07-07'),
('KM3', 'Flash sale 50%', '2025-08-10', '2025-08-10'),
('KM4', 'Ưu đãi VIP', '2025-09-01', '2025-09-30'),
('KM5', 'Black Friday', '2025-11-25', '2025-11-27');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaKH` varchar(10) DEFAULT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `NgayLapHD` datetime DEFAULT current_timestamp(),
  `TongTien` decimal(15,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `MaNV`, `NgayLapHD`, `TongTien`) VALUES
('1', '1', '1', '2025-04-03 00:00:00', 65000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `Ho`, `Ten`, `DiaChi`, `SDT`) VALUES
('1', 'Nguyễn', 'An', 'Hà Nội', '0987654321');

-- --------------------------------------------------------

--
-- Table structure for table `loaisp`
--

CREATE TABLE `loaisp` (
  `MaLoai` varchar(10) NOT NULL,
  `TenLoai` varchar(100) NOT NULL,
  `MoTa` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loaisp`
--

INSERT INTO `loaisp` (`MaLoai`, `TenLoai`, `MoTa`) VALUES
('1', 'Điện thoại', 'Thiết bị di động'),
('2', 'Laptop', 'Máy tính xách tay'),
('3', 'Tivi', 'Thiết bị hiển thị hình ảnh và âm thanh, dùng trong giải trí gia đình'),
('4', 'Tủ lạnh', 'Thiết bị bảo quản thực phẩm ở nhiệt độ thấp'),
('5', 'Máy giặt', 'Thiết bị gia dụng giúp giặt sạch quần áo tự động'),
('6', 'Loa Bluetooth', 'Thiết bị phát âm thanh không dây, kết nối qua Bluetooth');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(10) NOT NULL,
  `TenNCC` varchar(255) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`) VALUES
('1', 'Công ty A', 'TPHCM', '0912345670'),
('2', 'Công ty TNHH Hữu Thiện', '210/30 Lê Lợi', '0981537266'),
('3', 'Doanh nghiệp Tư nhân Hữu Tiến', '31/2A Lê Duẩn', '0871653987');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `LuongThang` decimal(10,2) NOT NULL,
  `TrangThai` int(10) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `Ho`, `Ten`, `LuongThang`, `TrangThai`) VALUES
('1', 'Trần', 'Bình', 15000000.00, 0),
('2', 'kok', 'kok', 1.00, 0),
('3', 'Nguyen', 'Van Chi', 8000000.00, 0),
('4', 'Hoang', 'Sang', 0.00, 0);

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `MaQuyen` varchar(10) NOT NULL,
  `TenQuyen` varchar(50) NOT NULL,
  `ChiTietQuyen` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
('Q1', 'Quản lí', 'qlNhanVien qlKhachHang, qlHoaDon'),
('Q2', 'Admin', 'qlTaiKhoan qlThongKe qlQuyen'),
('Q3', 'Sản phẩm', 'qlSanPham, qlLoaiSanPham, qlKhuyenMai'),
('Q4', 'nhập hàng', 'qlNhapHang, qlPhieuNhap, qlNhaCungCap'),
('Q5', 'Bán hàng', 'qlBanHang'),
('Q6', 'Kho hàng', 'qlKhoHang');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `MaPN` varchar(10) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `MaNCC` varchar(10) DEFAULT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` decimal(15,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`MaPN`, `MaNV`, `MaNCC`, `NgayNhap`, `GioNhap`, `TongTien`) VALUES
('PN1', '4', '1', '0000-00-00', '00:00:00', 10000.00),
('PN10', '4', '2', '2025-05-15', '15:41:40', 33000000.00),
('PN2', '4', '2', '2025-05-10', '08:40:26', 65000000.00),
('PN3', '4', '3', '2025-05-10', '08:49:45', 115000000.00),
('PN4', '4', '1', '2025-05-10', '09:07:08', 25000000.00),
('PN5', '4', '3', '2025-05-10', '09:09:53', 40000000.00),
('PN6', '4', '2', '2025-05-10', '09:21:43', 25000000.00),
('PN7', '4', '2', '2025-05-10', '09:25:57', 40000000.00),
('PN8', '4', '1', '2025-05-10', '09:31:47', 25000000.00),
('PN9', '4', '2', '2025-05-10', '09:34:09', 25000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `TenSP` varchar(255) NOT NULL,
  `SoLuong` int(11) DEFAULT 0,
  `DonGia` decimal(10,2) NOT NULL,
  `DonVi` varchar(50) DEFAULT NULL,
  `MaLoai` varchar(10) DEFAULT NULL,
  `HinhAnh` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `SoLuong`, `DonGia`, `DonVi`, `MaLoai`, `HinhAnh`) VALUES
('1', 'iPhone 15', 19, 18990000.00, 'Chiếc', '1', 'src\\GUI\\Image\\iphone15.jpg'),
('10', 'Samsung QLED 50 inch', 18, 19000000.00, 'Chiếc', '3', 'src\\GUI\\Image\\samsung-qled-50inch.jpg'),
('11', 'LG Smart TV 43 inch', 25, 12000000.00, 'Chiếc', '3', 'src\\GUI\\Image\\lg-smart-43inch.jpg'),
('12', 'Panasonic Inverter 350L', 10, 15000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\panasonic-350l.jpg'),
('13', 'Sharp 2 cánh 250L', 14, 9500000.00, 'Chiếc', '4', 'src\\GUI\\Image\\sharp-250l.jpg'),
('14', 'LG DoorCooling+ 420L', 8, 17000000.00, 'Chiếc', '4', 'src\\GUI\\Image\\lg-420l.jpg'),
('15', 'Electrolux 9kg Inverter', 11, 11000000.00, 'Chiếc', '5', 'src\\GUI\\Image\\electrolux-9kg.jpg'),
('16', 'LG 8.5kg cửa trước', 13, 10500000.00, 'Chiếc', '5', 'src\\GUI\\Image\\lg-8.5kg.jpg'),
('17', 'Samsung EcoBubble 10kg', 9, 13500000.00, 'Chiếc', '5', 'src\\GUI\\Image\\samsung-10kg.jpg'),
('18', 'JBL Flip 6', 30, 2800000.00, 'Chiếc', '6', 'src\\GUI\\Image\\jbl-flip6.jpg'),
('19', 'Sony SRS-XB43', 18, 3900000.00, 'Chiếc', '6', 'src\\GUI\\Image\\sony-xb43.jpg'),
('2', 'MacBook Pro', 14, 11000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\macbook-pro.jpg'),
('20', 'Anker Soundcore 3', 25, 1800000.00, 'Chiếc', '6', 'src\\GUI\\Image\\anker-3.jpg'),
('21', 'Marshall Emberton', 12, 4200000.00, 'Chiếc', '6', 'src\\GUI\\Image\\marshall.jpg'),
('3', 'iPhone 14 Pro', 20, 25000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\iphone14-pro.jpg'),
('4', 'Samsung Galaxy S23', 30, 21000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\galaxy-s23.jpg'),
('5', 'Xiaomi Redmi Note 12', 50, 7000000.00, 'Chiếc', '1', 'src\\GUI\\Image\\redmi-note12.jpg'),
('6', 'MacBook Air M2', 15, 28000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\macbook-air-m2.jpg'),
('7', 'Dell XPS 13', 10, 26000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\dell-xps-13.jpg'),
('8', 'HP Pavilion 15', 20, 18000000.00, 'Chiếc', '2', 'src\\GUI\\Image\\hp-pavilion15.jpg'),
('9', 'Sony Bravia 55 inch', 12, 22000000.00, 'Chiếc', '3', 'src\\GUI\\Image\\sony-bravia-55inch.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTK` varchar(50) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `MaNV` varchar(10) DEFAULT NULL,
  `MaQuyen` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`TenTK`, `MatKhau`, `MaNV`, `MaQuyen`) VALUES
('admin', 'admin', '1', 'Q2'),
('banhang', 'banhang', '2', 'Q5'),
('khohang', 'khohang', '2', 'Q6'),
('nhaphang', 'nhaphang', '4', 'Q4'),
('quanly', 'quanly', '1', 'Q1'),
('sanpham', 'sanpham', '3', 'Q3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD PRIMARY KEY (`MaCTKM`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MaPN`,`MaSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Indexes for table `chtrinhkhuyenmai`
--
ALTER TABLE `chtrinhkhuyenmai`
  ADD PRIMARY KEY (`MaCTKM`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `loaisp`
--
ALTER TABLE `loaisp`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`MaQuyen`);

--
-- Indexes for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaNCC` (`MaNCC`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTK`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaQuyen` (`MaQuyen`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_1` FOREIGN KEY (`MaCTKM`) REFERENCES `chtrinhkhuyenmai` (`MaCTKM`),
  ADD CONSTRAINT `chitietkhuyenmai_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaPN`) REFERENCES `phieunhaphang` (`MaPN`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `phieunhaphang_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `phieunhaphang_ibfk_2` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaisp` (`MaLoai`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
